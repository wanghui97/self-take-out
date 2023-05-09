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
 * 菜品口味关系表(DishFlavor)表实体类
 *
 * @author hwang
 * @since 2023-05-04 22:16:29
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("dish_flavor")
@Accessors(chain = true)
public class DishFlavor  {
    //主键@TableId
    private Long id;

    //菜品
    private Long dishId;
    //口味名称
    private String name;
    //口味数据list
    private String value;
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
