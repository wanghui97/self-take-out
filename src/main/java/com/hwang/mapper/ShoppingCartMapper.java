package com.hwang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwang.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 购物车(ShoppingCart)表数据库访问层
 *
 * @author hwang
 * @since 2023-05-06 16:03:31
 */
@Repository
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

}
