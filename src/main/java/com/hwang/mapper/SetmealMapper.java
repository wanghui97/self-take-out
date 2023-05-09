package com.hwang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwang.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 套餐(Setmeal)表数据库访问层
 *
 * @author hwang
 * @since 2023-05-05 10:51:59
 */
@Repository
@Mapper
public interface SetmealMapper extends BaseMapper<Setmeal> {

}
