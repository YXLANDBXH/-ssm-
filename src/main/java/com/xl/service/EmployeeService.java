package com.xl.service;

import com.xl.domain.Employee;

import java.util.List;

/**
 * @author XLong
 * @create 2021-07-17 11:32
 */
public interface EmployeeService {
    //员工查询
    List<Employee> selectEmpList();
    //根据id编辑员工信息
    void updateEmployee(Employee employee);
    //根据id查询
    Employee getEmployeeById(String id);
    //插入数据
    void insertEmployee(Employee employee);
    //删除
    void deleteEmployees(String[] empIds);
    //部门模糊查询
    List<Employee> selectEmployeeLike(Employee employee);
}
