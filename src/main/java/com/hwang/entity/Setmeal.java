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
 * 套餐(Setmeal)表实体类
 *
 * @author hwang
 * @since 2023-05-05 10:51:59
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("setmeal")
public class Setmeal  {
    //主键@TableId
    private Long id;

    //菜品分类id
    private Long categoryId;
    //套餐名称
    private String name;
    //套餐价格
    private Double price;
    //状态 0:停用 1:启用
    private Integer status;
    //编码
    private String code;
    //描述信息
    private String description;
    //图片
    private String image;
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

    public Setmeal(Long id, int updateStatus) {
        this.id = id;
        this.status = updateStatus;
    }
}
