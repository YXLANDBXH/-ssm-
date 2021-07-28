package com.xl.mapper;

import com.xl.domain.Employee;

import java.util.List;

/**
 * @author XLong
 * @create 2021-07-26 21:00
 */
public interface EmployeeAndDeptAndJobMapper {
    List<Employee> getEmpList();
}
