package com.hwang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwang.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 套餐菜品关系(SetmealDish)表数据库访问层
 *
 * @author hwang
 * @since 2023-05-05 21:29:25
 */
@Repository
@Mapper
public interface SetmealDishMapper extends BaseMapper<SetmealDish> {

}
