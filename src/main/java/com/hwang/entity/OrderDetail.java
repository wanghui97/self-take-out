package com.hwang.entity;


import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.experimental.Accessors;

/**
 * 订单明细表(OrderDetail)表实体类
 *
 * @author hwang
 * @since 2023-05-06 09:15:14
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("order_detail")
@Accessors(chain = true)
public class OrderDetail  {
    //主键@TableId
    private Long id;

    //名字
    private String name;
    //图片
    private String image;
    //订单id
    private Long orderId;
    //菜品id
    private Long dishId;
    //套餐id
    private Long setmealId;
    //口味
    private String dishFlavor;
    //数量
    private Integer number;
    //金额
    private BigDecimal amount;
    //删除标志：0:正常,1:删除
    private Integer delFlag;



}
