package com.hwang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hwang.entity.*;
import com.hwang.mapper.OrdersMapper;
import com.hwang.service.*;
import com.hwang.utiltools.BaseContextUtil;
import com.hwang.utiltools.BeanCopyUtils;
import com.hwang.utiltools.CodeLibraryUtil;
import com.hwang.utiltools.ResponseResult;
import com.hwang.vo.OrderVo;
import com.sun.prism.impl.BaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 订单表(Orders)表服务实现类
 *
 * @author hwang
 * @since 2023-05-06 09:15:14
 */
@Service("ordersService")
@Transactional
public class OrdersServiceImpl<s> extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressBookService addressBookService;

    @Override
    public ResponseResult queryOrdersPage(Page ordersPage, Long number, String beginTime, String endTime) {
        LambdaQueryWrapper<Orders> ordersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ordersLambdaQueryWrapper.eq(Objects.nonNull(number),Orders::getId,number);
        ordersLambdaQueryWrapper.between(StringUtils.hasText(beginTime)||StringUtils.hasText(endTime),Orders::getOrderTime,beginTime,endTime);
        ordersLambdaQueryWrapper.orderByDesc(Orders::getOrderTime);
        ordersPage = ordersMapper.selectPage(ordersPage, ordersLambdaQueryWrapper);
        return ResponseResult.okResult(ordersPage);
    }

    @Override
    public ResponseResult queryCurrentOrders(Page ordersPage) {
        LambdaQueryWrapper<Orders> ordersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ordersLambdaQueryWrapper.eq(Orders::getUserId,BaseContextUtil.getCurrentId());
        ordersPage = ordersMapper.selectPage(ordersPage, ordersLambdaQueryWrapper);
        List<Orders> ordersPageRecordList = ordersPage.getRecords();
        List<OrderVo> orderVos = BeanCopyUtils.copyBeanList(ordersPageRecordList, OrderVo.class);
        List<OrderVo> orderVoList = orderVos.stream()
                .map(orderVo -> orderVo.setOrderDetails(orderDetailService.selectOrderDetailList(orderVo.getId())))
                .collect(Collectors.toList());
        ordersPage.setRecords(orderVoList);
        return ResponseResult.okResult(ordersPage);
    }

    @Override
    public ResponseResult submitOrders(Orders orders) {
        //获取订单表中最大的订单id
        LambdaQueryWrapper<Orders> ordersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ordersLambdaQueryWrapper.orderByDesc(Orders::getId);
        List<Orders> list = this.list(ordersLambdaQueryWrapper);
        Long orderId = null;//订单号
        if(!CollectionUtils.isEmpty(list)){
            orderId = list.get(0).getId()+1;

        }
        //获得当前用户id
        Long userId = BaseContextUtil.getCurrentId();
        //查询当前用户的购物车数据
        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserId,userId);
        List<ShoppingCart> shoppingCarts = shoppingCartService.list(wrapper);

        if(shoppingCarts == null || shoppingCarts.size() == 0){
            throw new RuntimeException("购物车为空，不能下单");
        }
        //查询用户数据
        User user = userService.getById(userId);
        //查询地址数据
        Long addressBookId = orders.getAddressBookId();
        AddressBook addressBook = addressBookService.getById(addressBookId);
        if(addressBook == null){
            throw new RuntimeException("用户地址信息有误，不能下单");
        }

        AtomicInteger amount = new AtomicInteger(0);
        Long finalOrderId = orderId;
        List<OrderDetail> orderDetails = shoppingCarts.stream().map((item) -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(finalOrderId);
            orderDetail.setNumber(item.getNumber());
            orderDetail.setDishFlavor(item.getDishFlavor());
            orderDetail.setDishId(item.getDishId());
            orderDetail.setSetmealId(item.getSetmealId());
            orderDetail.setName(item.getName());
            orderDetail.setImage(item.getImage());
            orderDetail.setAmount(item.getAmount());
            amount.addAndGet(item.getAmount().multiply(new BigDecimal(item.getNumber())).intValue());
            return orderDetail;
        }).collect(Collectors.toList());
        //保存订单信息
        orders.setId(orderId);
        orders.setOrderTime(new Date());
        orders.setCheckoutTime(new Date());
        orders.setStatus(2);
        orders.setAmount(new BigDecimal(amount.get()));//总金额
        orders.setUserId(userId);
        orders.setNumber(String.valueOf(orderId));
        orders.setUserName(user.getName());
        orders.setConsignee(addressBook.getConsignee());
        orders.setPhone(addressBook.getPhone());
        orders.setAddress((addressBook.getProvinceName() == null ? "" : addressBook.getProvinceName())
                + (addressBook.getCityName() == null ? "" : addressBook.getCityName())
                + (addressBook.getDistrictName() == null ? "" : addressBook.getDistrictName())
                + (addressBook.getDetail() == null ? "" : addressBook.getDetail()));
        //向订单表插入数据，一条数据
        this.save(orders);

        //向订单明细表插入数据，多条数据
        orderDetailService.saveBatch(orderDetails);
        //清空购物车数据
        shoppingCartService.remove(wrapper);
        return ResponseResult.okResult(CodeLibraryUtil.ORDER_SUCCESS);
    }

    @Override
    public ResponseResult addOrderAgain(Orders orders) {
        //复制订单
        orders = this.getById(orders.getId());
        if(Objects.nonNull(orders)){
            orders.setId(null);
            orders.setStatus(CodeLibraryUtil.WAIT_PAY);
        }
        this.save(orders);
        //复制订单明细
        LambdaQueryWrapper<OrderDetail> orderDetailLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderDetailLambdaQueryWrapper.eq(OrderDetail::getOrderId,orders.getId());
        List<OrderDetail> orderDetailList = orderDetailService.list(orderDetailLambdaQueryWrapper);
        List<OrderDetail> orderDetailsList = orderDetailList.stream()
                .map(orderDetail -> orderDetail.setId(null))
                .collect(Collectors.toList());
        orderDetailService.saveBatch(orderDetailsList);
        return ResponseResult.okResult();
    }
}
