package com.hwang.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hwang.annotation.PrintLogs;
import com.hwang.entity.AddressBook;
import com.hwang.entity.Orders;
import com.hwang.enums.AppHttpCodeEnum;
import com.hwang.service.OrdersService;
import com.hwang.utiltools.ResponseResult;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 订单表(Orders)表控制层
 *
 * @author hwang
 * @since 2023-05-06 09:15:14
 */
@RestController
@RequestMapping("order")
public class OrdersController{
    /**
     * 服务对象
     */
    @Resource
    private OrdersService ordersService;

    /**
     * 分页查询所有数据
     *
     * @param page 当前页码
     * @param pageSize 页面大小
     * @param beginTime 开始日期
     * @param endTime 结束日期
     * @return 所有数据
     */
    @GetMapping("/page")
    @PrintLogs(BusinessName = "分页查询订单数据")
    public ResponseResult selectAll(Long page, Long pageSize,Long number,String beginTime, String endTime) {
        //由于前端参数中的字段名和page对象中的属性名不一致，无法映射，所以手动包装
        Page ordersPage = new Page();
        ordersPage.setCurrent(page);
        ordersPage.setSize(pageSize);
        return ordersService.queryOrdersPage(ordersPage,number,beginTime,endTime);
    }

    /**
     * 查询当前客户最新订单
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/userPage")
    @PrintLogs(BusinessName = "查询当前客户最新订单数据")
    public ResponseResult selectCurrentUserOrder(Long page, Long pageSize) {
        //由于前端参数中的字段名和page对象中的属性名不一致，无法映射，所以手动包装
        Page ordersPage = new Page();
        ordersPage.setCurrent(page);
        ordersPage.setSize(pageSize);
        return ordersService.queryCurrentOrders(ordersPage);
    }

    /**
     * 再来一单
     * @param orders
     * @return 新增结果
     */
    @PostMapping("/again")
    @PrintLogs(BusinessName = "再来一单")
    public ResponseResult addOrderAgain(@RequestBody Orders orders) {
       return ordersService.addOrderAgain(orders);
    }

    /**
     * 提交订单
     * @param orders 订单对象
     * @return 提交订单
     */
    @PostMapping("/submit")
    @PrintLogs(BusinessName = "提交订单")
    public ResponseResult submitOrders(@RequestBody Orders orders) {
        return ordersService.submitOrders(orders);
    }
}

