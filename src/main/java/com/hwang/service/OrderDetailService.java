package com.hwang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hwang.entity.OrderDetail;

import java.util.List;


/**
 * 订单明细表(OrderDetail)表服务接口
 *
 * @author makejava
 * @since 2023-05-06 09:15:14
 */
public interface OrderDetailService extends IService<OrderDetail> {

    List<OrderDetail> selectOrderDetailList(Long orderId);
}
