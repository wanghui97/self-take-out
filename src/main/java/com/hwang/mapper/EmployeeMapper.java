package com.hwang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwang.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 员工信息(Employee)表数据库访问层
 *
 * @author hwang
 * @since 2023-04-27 23:15:39
 */
@Repository
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
