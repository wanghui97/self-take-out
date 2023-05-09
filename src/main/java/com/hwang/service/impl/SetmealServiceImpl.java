package com.hwang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hwang.entity.Category;
import com.hwang.entity.Setmeal;
import com.hwang.entity.SetmealDish;
import com.hwang.enums.AppHttpCodeEnum;
import com.hwang.mapper.SetmealMapper;
import com.hwang.service.CategoryService;
import com.hwang.service.SetmealDishService;
import com.hwang.service.SetmealService;
import com.hwang.utiltools.BeanCopyUtils;
import com.hwang.utiltools.CodeLibraryUtil;
import com.hwang.utiltools.ResponseResult;
import com.hwang.vo.SetmealVo;
import lombok.experimental.Accessors;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 套餐(Setmeal)表服务实现类
 *
 * @author hwang
 * @since 2023-05-05 10:51:59
 */
@Service("setmealService")
@Transactional
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SetmealDishService setmealDishService;

    @Override
    public ResponseResult querySetmealPage(Page setmealPage, String name) {
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(StringUtils.hasText(name),Setmeal::getName,name);
        setmealPage = setmealMapper.selectPage(setmealPage,setmealLambdaQueryWrapper);
        List<Setmeal> setmealList = setmealPage.getRecords();
        List<Long> categoryIdList = setmealList.stream()
                .map(setmeal -> setmeal.getCategoryId())
                .distinct()
                .collect(Collectors.toList());
        List<Category> categoryList = categoryService.listByIds(categoryIdList);
        List<SetmealVo> setmealVos = BeanCopyUtils.copyBeanList(setmealList, SetmealVo.class);
        setmealVos = setmealVos.stream()
                .map(setmealVo -> setmealVo.setCategoryName(categoryList.stream()
                        .filter(category -> category.getId().equals(setmealVo.getCategoryId()))
                        .map(category -> category.getName())
                        .findFirst().orElse(CodeLibraryUtil.CATEGORY_NAME_UNDEFINED))
                ).collect(Collectors.toList());
        return ResponseResult.okResult(setmealPage.setRecords(setmealVos));
    }

    @Override
    public ResponseResult selectBySetmealId(Serializable id) {
        Setmeal setmeal = setmealMapper.selectById(id);
        SetmealVo setmealVo = BeanCopyUtils.copyBean(setmeal, SetmealVo.class);
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.eq(SetmealDish::getSetmealId,setmealVo.getId());
        List<SetmealDish> setmealDishList = setmealDishService.list(setmealDishLambdaQueryWrapper);
        setmealVo.setSetmealDishes(setmealDishList);
        Category category = categoryService.getById(setmealVo.getCategoryId());
        setmealVo.setCategoryName(category.getName());
        return ResponseResult.okResult(setmealVo);
    }

    @Override
    public ResponseResult updateSetmeal(SetmealVo setmealVo) {
        Setmeal setmeal = BeanCopyUtils.copyBean(setmealVo, Setmeal.class);
        boolean updateFlag = this.updateById(setmeal);
        if(!updateFlag){
            ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.eq(SetmealDish::getSetmealId,setmealVo.getId());
        boolean removeByIdFlag = setmealDishService.remove(setmealDishLambdaQueryWrapper);
        if(!removeByIdFlag){
            ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        List<SetmealDish> setmealDishes = setmealVo.getSetmealDishes();
        boolean saveBatchFlag = setmealDishService.saveBatch(setmealDishes);
        if(!saveBatchFlag){
            ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult insertSetmeal(SetmealVo setmealVo) {
        Setmeal setmeal = BeanCopyUtils.copyBean(setmealVo, Setmeal.class);
        boolean saveFlag = this.save(setmeal);
        if(!saveFlag){
            ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        List<SetmealDish> setmealDishes = setmealVo.getSetmealDishes().stream()
                .map(setmealDish -> setmealDish.setSetmealId(setmeal.getId().toString()))
                .collect(Collectors.toList());
        boolean saveBatchFlag = setmealDishService.saveBatch(setmealDishes);
        if(!saveBatchFlag){
            ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult selectSetmealByCategoryId(Long categoryId, Integer status) {
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Objects.nonNull(categoryId),Setmeal::getCategoryId,categoryId);
        setmealLambdaQueryWrapper.eq(Objects.nonNull(status),Setmeal::getStatus,status);
        List<Setmeal> setmealList = this.list(setmealLambdaQueryWrapper);
        return ResponseResult.okResult(setmealList);
    }
}
