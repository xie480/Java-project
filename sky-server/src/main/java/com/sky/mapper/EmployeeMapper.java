package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     *
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    List<Employee> findAllByPage(EmployeePageQueryDTO employeePageQueryDTO);

    @AutoFill(value = OperationType.INSERT)
    void saveEmp(Employee employee);

    @AutoFill(value = OperationType.UPDATE)
    void updateEmp(Employee employee);

    @Select("select * from employee where id = #{id}")
    Employee findById(Long id);
}
