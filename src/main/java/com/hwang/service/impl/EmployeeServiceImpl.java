package com.hwang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hwang.entity.Employee;
import com.hwang.enums.AppHttpCodeEnum;
import com.hwang.mapper.EmployeeMapper;
import com.hwang.service.EmployeeService;
import com.hwang.utiltools.BeanCopyUtils;
import com.hwang.utiltools.CodeLibraryUtil;
import com.hwang.utiltools.ResponseResult;
import com.hwang.vo.EmployeeInfoVo;
import com.hwang.vo.EmployeeLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * 员工信息(Employee)表服务实现类
 *
 * @author hwang
 * @since 2023-04-27 23:15:43
 */
@Service("employeeService")
@Transactional
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public ResponseResult queryEmployeeByUserName(Employee employee, HttpServletRequest request) {
        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        employeeLambdaQueryWrapper.eq(Employee::getUsername,employee.getUsername());
        //员工信息状态为1，表示正常
        employeeLambdaQueryWrapper.eq(Employee::getStatus, CodeLibraryUtil.STATUS_NORMAL);
        //数据库查询返回的员工对象
        Employee employeeBack = employeeMapper.selectOne(employeeLambdaQueryWrapper);
        if(Objects.nonNull(employeeBack)){//如果查到了员工信息对比密码信息是否一致
            if(employeeBack.getPassword().equals(employee.getPassword())){//如果前台输入的密码和后台查到的密码一致，返回登录信息
                //将完整的Employee对象转换为EmployeeVo(EmployeeVo是阉割版的Employee，不想暴露过多信息给前端)
                EmployeeLoginVo employeeVo = BeanCopyUtils.copyBean(employeeBack, EmployeeLoginVo.class);
                request.getSession().setAttribute(CodeLibraryUtil.EMPLOYEE_SESSION,employeeVo.getId());
                return ResponseResult.okResult(employeeVo);
            }
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
    }

    @Override
    public ResponseResult queryEmployeePage(Page page, String name) {
        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //employeeLambdaQueryWrapper.eq(Employee::getStatus,CodeLibraryUtil.STATUS_NORMAL);
        //如果name不为空,则添加该条件
        employeeLambdaQueryWrapper.eq(StringUtils.hasText(name),Employee::getName,name);
        page = employeeMapper.selectPage(page, employeeLambdaQueryWrapper);
        //将page对象中的employee对象集合转换为employeeInfoVo对象集合返回
        List<EmployeeInfoVo> employeeInfoVoList = BeanCopyUtils.copyBeanList(page.getRecords(), EmployeeInfoVo.class);
        page.setRecords(employeeInfoVoList);
        return ResponseResult.okResult(page);
    }

    @Override
    public boolean queryIsExistsUsername(String username) {
        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        employeeLambdaQueryWrapper.eq(Employee::getUsername,username);
        Employee employee = employeeMapper.selectOne(employeeLambdaQueryWrapper);
        if(Objects.isNull(employee))//如果用userName查询不到员工信息，返回false
            return false;
        return true;
    }
}
