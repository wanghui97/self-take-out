package com.hwang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hwang.entity.Dish;
import com.hwang.utiltools.ResponseResult;
import com.hwang.vo.DishVo;


/**
 * 菜品管理(Dish)表服务接口
 *
 * @author hwang
 * @since 2023-05-04 15:35:23
 */
public interface DishService extends IService<Dish> {

    ResponseResult queryCategoryPage(Page dishPage, String name);

    ResponseResult selectByDishId(Long id);

    ResponseResult updateDish(DishVo dishVo);

    ResponseResult saveDish(DishVo dishVo);

    ResponseResult selectByCategoryId(Long categoryId, Integer status);
}
