package com.hwang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hwang.entity.ShoppingCart;
import com.hwang.utiltools.ResponseResult;


/**
 * 购物车(ShoppingCart)表服务接口
 *
 * @author makejava
 * @since 2023-05-06 16:03:32
 */
public interface ShoppingCartService extends IService<ShoppingCart> {

    ResponseResult cleanShopingCart();

    ResponseResult saveAndUpdateShoppingCart(ShoppingCart shoppingCart);

    ResponseResult cartNumberSubtract(ShoppingCart shoppingCart);
}
