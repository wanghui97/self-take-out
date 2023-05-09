package com.hwang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hwang.entity.Employee;
import com.hwang.utiltools.ResponseResult;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;


/**
 * 员工信息(Employee)表服务接口
 *
 * @author hwang
 * @since 2023-04-27 23:15:43
 */
public interface EmployeeService extends IService<Employee> {

    ResponseResult queryEmployeeByUserName(Employee employee, HttpServletRequest request);

    ResponseResult queryEmployeePage(Page page, String name);

    boolean queryIsExistsUsername(String username);
}
