package com.hwang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hwang.entity.Setmeal;
import com.hwang.utiltools.ResponseResult;
import com.hwang.vo.SetmealVo;

import java.io.Serializable;


/**
 * 套餐(Setmeal)表服务接口
 *
 * @author hwang
 * @since 2023-05-05 10:51:59
 */
public interface SetmealService extends IService<Setmeal> {

    ResponseResult querySetmealPage(Page setmealPage, String name);

    ResponseResult selectBySetmealId(Serializable id);

    ResponseResult updateSetmeal(SetmealVo setmealVo);

    ResponseResult insertSetmeal(SetmealVo setmealVo);

    ResponseResult selectSetmealByCategoryId(Long categoryId, Integer status);
}
