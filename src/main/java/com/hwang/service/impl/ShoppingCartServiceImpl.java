package com.hwang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hwang.entity.ShoppingCart;
import com.hwang.mapper.ShoppingCartMapper;
import com.hwang.service.ShoppingCartService;
import com.hwang.utiltools.BaseContextUtil;
import com.hwang.utiltools.ResponseResult;
import com.sun.prism.impl.BaseContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

/**
 * 购物车(ShoppingCart)表服务实现类
 *
 * @author hwang
 * @since 2023-05-06 16:03:32
 */
@Service("shoppingCartService")
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
    @Override
    public ResponseResult cleanShopingCart() {
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, BaseContextUtil.getCurrentId());
        this.remove(queryWrapper);
        return ResponseResult.okResult("清空购物车成功");
    }

    @Override
    public ResponseResult saveAndUpdateShoppingCart(ShoppingCart shoppingCart) {
        //设置用户id，指定当前是哪个用户的购物车数据
        Long currentId = BaseContextUtil.getCurrentId();
        shoppingCart.setUserId(currentId);
        Long dishId = shoppingCart.getDishId();
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,currentId);
        if(dishId != null){
            //添加到购物车的是菜品
            queryWrapper.eq(ShoppingCart::getDishId,dishId);
        }else{
            //添加到购物车的是套餐
            queryWrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        }
        //查询当前菜品或者套餐是否在购物车中
        //SQL:select * from shopping_cart where user_id = ? and dish_id/setmeal_id = ?
        ShoppingCart cartServiceOne = this.getOne(queryWrapper);

        if(cartServiceOne != null){
            //如果已经存在，就在原来数量基础上加一
            Integer number = cartServiceOne.getNumber();
            cartServiceOne.setNumber(number + 1);
            this.updateById(cartServiceOne);
        }else{
            //如果不存在，则添加到购物车，数量默认就是一
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(new Date());
            this.save(shoppingCart);
            cartServiceOne = shoppingCart;
        }
        return ResponseResult.okResult(cartServiceOne);
    }

    @Override
    public ResponseResult cartNumberSubtract(ShoppingCart shoppingCart) {
        LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(Objects.nonNull(shoppingCart.getDishId())){
            shoppingCartLambdaQueryWrapper.eq(ShoppingCart::getDishId,shoppingCart.getDishId());
        }else if(Objects.nonNull(shoppingCart.getSetmealId())){
            shoppingCartLambdaQueryWrapper.eq(ShoppingCart::getDishId,shoppingCart.getSetmealId());
        }
        shoppingCart = this.getOne(shoppingCartLambdaQueryWrapper);
        HashMap<String, Integer> map = new HashMap<>();
        if(Objects.nonNull(shoppingCart)&&shoppingCart.getNumber()>1){
            shoppingCart.setNumber(shoppingCart.getNumber()-1);
            map.put("number",shoppingCart.getNumber());
            this.updateById(shoppingCart);
        }else if(Objects.nonNull(shoppingCart)&&shoppingCart.getNumber()==1){
            map.put("number",shoppingCart.getNumber()-1);
            this.removeById(shoppingCart.getId());
        }
        return ResponseResult.okResult(map);
    }
}
