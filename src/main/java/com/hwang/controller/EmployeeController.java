package com.hwang.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hwang.annotation.PrintLogs;
import com.hwang.entity.Employee;
import com.hwang.enums.AppHttpCodeEnum;
import com.hwang.service.EmployeeService;
import com.hwang.utiltools.BeanCopyUtils;
import com.hwang.utiltools.ResponseResult;
import com.hwang.vo.EmployeeInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 *
 * @Author 王辉
 * @Create 2023/4/28 15:15
 * @Version 1.0
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 查询分页后员工信息
     * @param page：当前页码
     * @param pageSize：页面大小
     * @return
     */
    @GetMapping("/page")
    @PrintLogs(BusinessName = "查询分页后员工信息")
    public ResponseResult employeePage(Long page, Long pageSize, String name){
        Page employeePage = new Page();
        employeePage.setCurrent(page);
        employeePage.setSize(pageSize);
        return employeeService.queryEmployeePage(employeePage,name);
    }

    /**
     * 新增员工信息
     * @param employee
     * @return
     */
    @PostMapping
    @PrintLogs(BusinessName = "新增员工信息")
    public ResponseResult addEmployee(@RequestBody Employee employee){
        boolean existsUserName = employeeService.queryIsExistsUsername(employee.getUsername());
        if(existsUserName){
            return ResponseResult.errorResult(AppHttpCodeEnum.USERNAME_EXIST);
        }
        //密码加密
        employee.setPassword(DigestUtils.md5DigestAsHex(employee.getPassword().getBytes()));
        boolean saveFlag = employeeService.save(employee);
        if(!saveFlag){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        return ResponseResult.okResult();
    }

    /**
     * 修改员工信息前先查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PrintLogs(BusinessName = "根据员工id查询员工信息")
    public ResponseResult queryEmployeeById(@PathVariable("id") Long id){
        Employee employee = employeeService.getById(id);

        EmployeeInfoVo employeeInfoVo = BeanCopyUtils.copyBean(employee, EmployeeInfoVo.class);
        return ResponseResult.okResult(employeeInfoVo);
    }

    /**
     * 修改员工信息
     * @param employee
     * @return
     */
    @PutMapping
    @PrintLogs(BusinessName = "修改员工信息")
    public ResponseResult updateEmployeeInfo(@RequestBody Employee employee){
        if(StringUtils.hasText(employee.getPassword())){
            employee.setPassword(DigestUtils.md5DigestAsHex(employee.getPassword().getBytes()));
        }
        boolean updateFlag = employeeService.updateById(employee);
        if(!updateFlag){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        return ResponseResult.okResult();
    }
}
