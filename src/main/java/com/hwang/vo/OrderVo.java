package com.hwang.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hwang.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单表(Orders)表实体类
 *
 * @author hwang
 * @since 2023-05-06 09:15:14
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("orders")
@Accessors(chain = true)
public class OrderVo {
    //主键@TableId
    private Long id;

    //订单号
    private String number;
    //订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
    private Integer status;
    //下单用户
    private Long userId;
    //地址id
    private Long addressBookId;
    //下单时间
    private Date orderTime;
    //结账时间
    private Date checkoutTime;
    //支付方式 1微信,2支付宝
    private Integer payMethod;
    //实收金额
    private BigDecimal amount;
    //备注
    private String remark;
    
    private String phone;
    
    private String address;
    
    private String userName;
    
    private String consignee;

    private List<OrderDetail> orderDetails;
}
