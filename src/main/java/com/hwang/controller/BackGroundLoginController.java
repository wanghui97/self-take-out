package com.hwang.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hwang.annotation.PrintLogs;
import com.hwang.entity.Employee;
import com.hwang.service.EmployeeService;
import com.hwang.utiltools.CodeLibraryUtil;
import com.hwang.utiltools.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 *  外卖后台员工登录处理类
 * @Author 王辉
 * @Create 2023/4/27 16:31
 * @Version 1.0
 */
@RestController
@RequestMapping("/employee")
public class BackGroundLoginController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 后台用户登录接口
     * @param employee
     * @return
     */
    @PostMapping("/login")
    @PrintLogs(BusinessName = "后台用户登录接口")
    public ResponseResult login(@RequestBody Employee employee){
        String passWord = employee.getPassword();
        passWord = DigestUtils.md5DigestAsHex(passWord.getBytes());
        employee.setPassword(passWord);
        //获取当前的http请求对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        return employeeService.queryEmployeeByUserName(employee,request);
    }

    /**
     * 后台用户退出登录接口
     * @return
     */
    @PostMapping("/logout")
    @PrintLogs(BusinessName = "后台用户退出登录接口")
    public ResponseResult logout(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        request.getSession().removeAttribute(CodeLibraryUtil.EMPLOYEE_SESSION);
        return ResponseResult.okResult();
    }
}
