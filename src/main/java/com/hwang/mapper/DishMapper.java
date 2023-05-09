package com.hwang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwang.entity.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 菜品管理(Dish)表数据库访问层
 *
 * @author hwang
 * @since 2023-05-04 15:35:23
 */
@Repository
@Mapper
public interface DishMapper extends BaseMapper<Dish> {

}
