package com.hwang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwang.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 订单表(Orders)表数据库访问层
 *
 * @author hwang
 * @since 2023-05-06 09:15:14
 */
@Repository
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}
