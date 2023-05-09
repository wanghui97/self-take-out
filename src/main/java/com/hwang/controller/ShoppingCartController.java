package com.hwang.controller;




import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hwang.annotation.PrintLogs;
import com.hwang.entity.ShoppingCart;
import com.hwang.enums.AppHttpCodeEnum;
import com.hwang.service.ShoppingCartService;
import com.hwang.utiltools.BaseContextUtil;
import com.hwang.utiltools.ResponseResult;
import com.sun.prism.impl.BaseContext;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 购物车(ShoppingCart)表控制层
 *
 * @author makejava
 * @since 2023-05-06 17:41:00
 */
@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController{
    /**
     * 服务对象
     */
    @Resource
    private ShoppingCartService shoppingCartService;

    /**
     * 查询所有数据
     * @return 所有数据
     */
    @GetMapping("/list")
    public ResponseResult selectAll() {
        return ResponseResult.okResult(shoppingCartService.list());
    }

    /**
     * 加入购物车
     * @param shoppingCart
     * @return
     */
    @PostMapping("/add")
    public ResponseResult addShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        return shoppingCartService.saveAndUpdateShoppingCart(shoppingCart);
    }
    /**
     * 清空购物车
     * @return
     */
    @DeleteMapping("/clean")
    @PrintLogs(BusinessName = "清空购物车")
    public ResponseResult clean(){
        return shoppingCartService.cleanShopingCart();
    }
    /**
     * 根据商品id修改购物车商品数量
     * @param shoppingCart
     * @return
     */
    @PostMapping("/sub")
    @PrintLogs(BusinessName = "根据商品id修改购物车商品数量")
    public ResponseResult cartNumberSubtract(@RequestBody ShoppingCart shoppingCart){
        return shoppingCartService.cartNumberSubtract(shoppingCart);
    }
}

