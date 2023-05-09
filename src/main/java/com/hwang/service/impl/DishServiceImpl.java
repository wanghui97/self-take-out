package com.hwang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hwang.entity.Category;
import com.hwang.entity.Dish;
import com.hwang.entity.DishFlavor;
import com.hwang.enums.AppHttpCodeEnum;
import com.hwang.mapper.DishMapper;
import com.hwang.service.CategoryService;
import com.hwang.service.DishFlavorService;
import com.hwang.service.DishService;
import com.hwang.utiltools.BeanCopyUtils;
import com.hwang.utiltools.CodeLibraryUtil;
import com.hwang.utiltools.ResponseResult;
import com.hwang.vo.DishVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 菜品管理(Dish)表服务实现类
 *
 * @author hwang
 * @since 2023-05-04 15:35:23
 */
@Service("dishService")
@Transactional
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DishFlavorService dishFlavorService;

    @Override
    public ResponseResult queryCategoryPage(Page dishPage, String name) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(StringUtils.hasText(name),Dish::getName,name);
        dishPage = dishMapper.selectPage(dishPage, dishLambdaQueryWrapper);
        List<Dish> dishPageList= dishPage.getRecords();
        List<DishVo> dishVoList = BeanCopyUtils.copyBeanList(dishPageList, DishVo.class);
        //获取当前分页中所有去重后的分类id
        List<Long> categoryIds = dishVoList.stream()
                .map(dishVo -> dishVo.getCategoryId())
                .distinct()
                .collect(Collectors.toList());
        //根据分类id获取分类信息
        List<Category> categoryList = categoryService.listByIds(categoryIds);
        List<DishVo> dishVoPageList = dishVoList.stream()
                .map(dishVo -> dishVo.setCategoryName(categoryList.stream()
                .filter(category -> category.getId().equals(dishVo.getCategoryId()))
                .map(category -> category.getName())
                .findFirst().orElse(CodeLibraryUtil.CATEGORY_NAME_UNDEFINED)))
                .collect(Collectors.toList());
        return ResponseResult.okResult(dishPage.setRecords(dishVoPageList));
    }

    @Override
    public ResponseResult selectByDishId(Long id) {
        Dish dish = dishMapper.selectById(id);
        DishVo dishVo = BeanCopyUtils.copyBean(dish, DishVo.class);
        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId,dishVo.getId());
        List<DishFlavor> dishFlavorList = dishFlavorService.list(dishFlavorLambdaQueryWrapper);
        dishVo.setFlavors(dishFlavorList);
        return ResponseResult.okResult(dishVo);
    }

    @Override
    public ResponseResult updateDish(DishVo dishVo) {
        Dish dish = BeanCopyUtils.copyBean(dishVo, Dish.class);
        boolean updateByIdFlag = this.updateById(dish);
        if(!updateByIdFlag)
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);

        //先删除dish_flavor表中的关联数据，再重新插入数据
        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId,dish.getId());
        boolean removeFlag = dishFlavorService.remove(dishFlavorLambdaQueryWrapper);
        if(!removeFlag)
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        boolean saveDishFlavorFlag = dishFlavorService.saveBatch(dishVo.getFlavors());
        if(!saveDishFlavorFlag)
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);

        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult saveDish(DishVo dishVo) {
        Dish dish = BeanCopyUtils.copyBean(dishVo, Dish.class);
        boolean saveFlag = this.save(dish);
        if(!saveFlag)
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        List<DishFlavor> dishFlavorList = dishVo.getFlavors().stream()
                .map(dishFlavor -> dishFlavor.setDishId(dish.getId()))
                .collect(Collectors.toList());
        boolean saveDishFlavorFlag = dishFlavorService.saveBatch(dishFlavorList);
        if(!saveDishFlavorFlag)
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);

        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult selectByCategoryId(Long categoryId, Integer status) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Objects.nonNull(categoryId),Dish::getCategoryId,categoryId);
        dishLambdaQueryWrapper.eq(Objects.nonNull(status),Dish::getStatus,status);
        List<Dish> dishList = this.list(dishLambdaQueryWrapper);
        List<DishVo> dishVoList = BeanCopyUtils.copyBeanList(dishList, DishVo.class);
        dishVoList.stream()
                .map(dishVo -> dishVo.setFlavors(dishFlavorService.queryByDishId(dishVo.getId())))
                .collect(Collectors.toList());
        return ResponseResult.okResult(dishVoList);
    }
}
