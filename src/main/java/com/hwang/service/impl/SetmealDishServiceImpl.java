package com.hwang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hwang.entity.Setmeal;
import com.hwang.entity.SetmealDish;
import com.hwang.mapper.SetmealDishMapper;
import com.hwang.service.DishService;
import com.hwang.service.SetmealDishService;
import com.hwang.utiltools.BeanCopyUtils;
import com.hwang.utiltools.ResponseResult;
import com.hwang.vo.SetmealDishVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 套餐菜品关系(SetmealDish)表服务实现类
 *
 * @author hwang
 * @since 2023-05-05 21:29:25
 */
@Service("setmealDishService")
@Transactional
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish> implements SetmealDishService {
    @Autowired
    private DishService dishService;

    @Override
    public ResponseResult selectSetmealDishByDishId(Long dishId) {
        LambdaQueryWrapper<SetmealDish> setmealDishServiceLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishServiceLambdaQueryWrapper.eq(SetmealDish::getSetmealId,dishId.toString());
        List<SetmealDish> setmealDishes = this.list(setmealDishServiceLambdaQueryWrapper);
        List<SetmealDishVo> setmealDishVos = BeanCopyUtils.copyBeanList(setmealDishes, SetmealDishVo.class);
        setmealDishVos.stream()
                .map(setmealDishVo ->
                        setmealDishVo.setImage(dishService.getById(setmealDishVo.getDishId()).getImage()))
                .collect(Collectors.toList());
        return ResponseResult.okResult(setmealDishVos);
    }
}
