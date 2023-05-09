package com.hwang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hwang.entity.OrderDetail;
import com.hwang.mapper.OrderDetailMapper;
import com.hwang.service.OrderDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单明细表(OrderDetail)表服务实现类
 *
 * @author hwang
 * @since 2023-05-06 09:15:14
 */
@Service("orderDetailService")
@Transactional
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
    @Override
    public List<OrderDetail> selectOrderDetailList(Long orderId) {
        LambdaQueryWrapper<OrderDetail> orderDetailLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderDetailLambdaQueryWrapper.eq(OrderDetail::getOrderId,orderId);
        List<OrderDetail> orderDetailList = this.list(orderDetailLambdaQueryWrapper);
        return orderDetailList;
    }
}
