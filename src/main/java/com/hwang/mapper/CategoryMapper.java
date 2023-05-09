package com.hwang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwang.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 菜品及套餐分类(Category)表数据库访问层
 *
 * @author hwang
 * @since 2023-05-04 10:44:04
 */
@Repository
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
