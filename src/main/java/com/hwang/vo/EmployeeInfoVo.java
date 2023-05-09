package com.hwang.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 *
 * @Author 王辉
 * @Create 2023/4/28 21:41
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;
    //主键@TableId
    private Long id;
    //姓名
    private String name;
    //用户名
    private String username;
    //密码
    private String password;
    //手机号
    private String phone;
    //性别
    private String sex;
    //身份证号
    private String idNumber;
    //状态 0:禁用，1:正常
    private Integer status;
}
