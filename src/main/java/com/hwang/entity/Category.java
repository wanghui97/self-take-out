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
/**
 * 菜品及套餐分类(Category)表实体类
 *
 * @author hwang
 * @since 2023-05-04 10:44:04
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("category")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    //主键
    @TableId
    private Long id;

    //类型   1 菜品分类 2 套餐分类
    private Integer type;
    //分类名称
    private String name;
    //顺序
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
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;

}
