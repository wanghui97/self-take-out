package com.hwang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hwang.entity.DishFlavor;
import com.hwang.mapper.DishFlavorMapper;
import com.hwang.service.DishFlavorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜品口味关系表(DishFlavor)表服务实现类
 *
 * @author hwang
 * @since 2023-05-04 22:16:30
 */
@Service("dishFlavorService")
@Transactional
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
    @Override
    public List<DishFlavor> queryByDishId(Long dishId) {
        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId,dishId);
        List<DishFlavor> dishFlavorList = this.list(dishFlavorLambdaQueryWrapper);
        return dishFlavorList;
    }
}
