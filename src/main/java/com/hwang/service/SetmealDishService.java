package com.hwang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hwang.entity.SetmealDish;
import com.hwang.utiltools.ResponseResult;


/**
 * 套餐菜品关系(SetmealDish)表服务接口
 *
 * @author hwang
 * @since 2023-05-05 21:29:25
 */
public interface SetmealDishService extends IService<SetmealDish> {

    ResponseResult selectSetmealDishByDishId(Long dishId);
}
