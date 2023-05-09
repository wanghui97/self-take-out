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
 * 地址管理(AddressBook)表实体类
 *
 * @author hwang
 * @since 2023-05-06 09:50:39
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("address_book")
public class AddressBook  {
    //主键@TableId
    private Long id;

    //用户id
    private Long userId;
    //收货人
    private String consignee;
    //性别 0 女 1 男
    private Integer sex;
    //手机号
    private String phone;
    //省级区划编号
    private String provinceCode;
    //省级名称
    private String provinceName;
    //市级区划编号
    private String cityCode;
    //市级名称
    private String cityName;
    //区级区划编号
    private String districtCode;
    //区级名称
    private String districtName;
    //详细地址
    private String detail;
    //标签
    private String label;
    //默认 0 否 1是
    private Integer isDefault;
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
