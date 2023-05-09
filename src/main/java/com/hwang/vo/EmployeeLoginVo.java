package com.hwang.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description:
 *  返回给前端页面的view对象
 * @Author 王辉
 * @Create 2023/4/28 9:53
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLoginVo implements Serializable {
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
}
