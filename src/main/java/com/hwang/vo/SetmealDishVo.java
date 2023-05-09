package com.hwang.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 套餐菜品关系(SetmealDish)表实体类
 *
 * @author hwang
 * @since 2023-05-05 21:29:25
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("setmeal_dish")
@Accessors(chain = true)
public class SetmealDishVo {
    //主键@TableId
    private Long id;

    //套餐id 
    private String setmealId;
    //菜品id
    private String dishId;
    //菜品名称 （冗余字段）
    private String name;
    //菜品原价（冗余字段）
    private Double price;
    //份数
    private Integer copies;
    //排序
    private Integer sort;
    //菜品图片
    private String image;
}
