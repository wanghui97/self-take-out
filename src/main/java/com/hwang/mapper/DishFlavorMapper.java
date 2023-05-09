package com.hwang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwang.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 菜品口味关系表(DishFlavor)表数据库访问层
 *
 * @author hwang
 * @since 2023-05-04 22:16:29
 */
@Repository
@Mapper
public interface DishFlavorMapper extends BaseMapper<DishFlavor> {

}
