package com.hwang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hwang.entity.DishFlavor;

import java.util.List;


/**
 * 菜品口味关系表(DishFlavor)表服务接口
 *
 * @author hwang
 * @since 2023-05-04 22:16:29
 */
public interface DishFlavorService extends IService<DishFlavor> {

    List<DishFlavor> queryByDishId(Long id);
}
