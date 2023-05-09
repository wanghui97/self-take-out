package com.hwang.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.experimental.Accessors;

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
public class SetmealDish  {
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
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //创建人
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;
    //修改人
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

}
