package com.hwang.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 用户信息(User)表实体类
 *
 * @author hwang
 * @since 2023-05-06 16:03:32
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User  {
    //主键@TableId
    private Long id;

    //姓名
    private String name;
    //手机号
    private String phone;
    //性别
    private String sex;
    //身份证号
    private String idNumber;
    //头像
    private String avatar;
    //状态 0:禁用，1:正常
    private Integer status;
    //删除标志：0:正常,1:删除
    private Integer delFlag;



}
