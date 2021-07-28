package com.xl.service;

import com.xl.domain.Employee;
import com.xl.domain.EmployeeExample;
import com.xl.mapper.EmployeeAndDeptAndJobMapper;
import com.xl.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XLong
 * @create 2021-07-17 11:32
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeAndDeptAndJobMapper employeeAndDeptAndJobMapper;

    @Override
    public List<Employee> selectEmpList() {
        return this.employeeAndDeptAndJobMapper.getEmpList();
    }

    @Override
    public void updateEmployee(Employee employee) {
        this.employeeMapper.updateByPrimaryKeySelective(employee);
    }

    @Override
    public Employee getEmployeeById(String id) {
        return this.employeeMapper.selectByPrimaryKey(Integer.parseInt(id));
    }

    @Override
    public void insertEmployee(Employee employee) {
        this.employeeMapper.insertSelective(employee);
    }

    @Override
    public void deleteEmployees(String[] empIds) {
        for (int i = 0;i < empIds.length;i++) {
            this.employeeMapper.deleteByPrimaryKey(Integer.parseInt(empIds[i]));
        }
    }

    @Override
    public List<Employee> selectEmployeeLike(Employee employee) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        if (employee!=null) {
            if (employee.getSex()!=null && !employee.getSex().equals("")) {
                criteria.andSexEqualTo(employee.getSex());
            }
            if (employee.getName()!=null && !employee.getName().equals("")) {
                criteria.andNameLike(employee.getName());
            }
            if (employee.getPhone()!=null && !employee.getPhone().equals("")) {
                criteria.andPhoneLike(employee.getPhone());
            }
            if (employee.getCardId()!=null && !employee.getCardId().equals("")) {
                criteria.andCardIdLike(employee.getCardId());
            }
        }
        return this.employeeMapper.selectByExample(employeeExample);
    }
}
