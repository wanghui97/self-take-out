package com.hwang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hwang.entity.Orders;
import com.hwang.utiltools.ResponseResult;


/**
 * 订单表(Orders)表服务接口
 *
 * @author hwang
 * @since 2023-05-06 09:15:14
 */
public interface OrdersService extends IService<Orders> {

    ResponseResult queryOrdersPage(Page ordersPage, Long number, String beginTime, String endTime);

    ResponseResult queryCurrentOrders(Page ordersPage);

    ResponseResult submitOrders(Orders orders);

    ResponseResult addOrderAgain(Orders orders);
}
