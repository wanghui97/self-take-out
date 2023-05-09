package com.hwang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hwang.entity.Category;
import com.hwang.utiltools.ResponseResult;


/**
 * 菜品及套餐分类(Category)表服务接口
 *
 * @author hwang
 * @since 2023-05-04 10:44:05
 */
public interface CategoryService extends IService<Category> {

    ResponseResult queryCategoryPage(Page categoryPage);

    ResponseResult selectCategoryByType(Integer type);
}
