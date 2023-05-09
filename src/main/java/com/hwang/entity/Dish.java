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
 * 菜品管理(Dish)表实体类
 *
 * @author hwang
 * @since 2023-05-04 15:35:23
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("dish")
public class Dish  {
    //主键@TableId
    private Long id;

    //菜品名称
    private String name;
    //菜品分类id
    private Long categoryId;
    //菜品价格
    private Double price;
    //商品码
    private String code;
    //图片
    private String image;
    //描述信息
    private String description;
    //0 停售 1 起售
    private Integer status;
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


    public Dish(Long id, int status) {
        this.id = id;
        this.status = status;
    }
}
