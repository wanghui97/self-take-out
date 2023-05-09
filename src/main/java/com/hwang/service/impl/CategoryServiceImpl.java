package com.hwang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hwang.entity.Category;
import com.hwang.mapper.CategoryMapper;
import com.hwang.service.CategoryService;
import com.hwang.utiltools.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 菜品及套餐分类(Category)表服务实现类
 *
 * @author hwang
 * @since 2023-05-04 10:44:05
 */
@Service("categoryService")
@Transactional
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public ResponseResult queryCategoryPage(Page categoryPage) {
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryPage = categoryMapper.selectPage(categoryPage, categoryLambdaQueryWrapper);

        return ResponseResult.okResult(categoryPage);
    }

    @Override
    public ResponseResult selectCategoryByType(Integer type) {
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper.eq(Objects.nonNull(type),Category::getType,type);
        categoryLambdaQueryWrapper.orderByAsc(Category::getSort);
        List<Category> categoryList = categoryMapper.selectList(categoryLambdaQueryWrapper);
        return ResponseResult.okResult(categoryList);
    }
}
