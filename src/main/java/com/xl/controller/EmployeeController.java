package com.xl.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xl.domain.Dept;
import com.xl.domain.Employee;
import com.xl.domain.Job;
import com.xl.service.DeptService;
import com.xl.service.EmployeeService;
import com.xl.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author XLong
 * @create 2021-07-16 19:00
 */
@Controller
public class EmployeeController {

//    设置每页显示的条数
    private static final int pageSize = 4;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JobService jobService;

    @Autowired
    private DeptService deptService;

    /**
     * 分页查询
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "emplist.action")
    public String selectEmpList(Employee employee,
                                Model model,
                                @RequestParam(name = "pageNum",required = false,defaultValue = "1")int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<Job> jobList = this.jobService.selectAllJoblist();
        List<Dept> deptList = this.deptService.selectDeptList();
        List<Employee> employeeList = this.employeeService.selectEmpList();
        PageInfo<Employee> pageInfo = new PageInfo(employeeList);
        model.addAttribute("pageModel",pageInfo);
        model.addAttribute("jobList",jobList);
        model.addAttribute("deptList",deptList);
        return "employee/employeelist";
    }

    /**
     * 修改数据回显
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "empedit.action")
    public String toEmpedit(String id,Model model) {
        List<Job> jobList = this.jobService.selectAllJoblist();
        List<Dept> deptList = this.deptService.selectDeptList();
        Employee employee = this.employeeService.getEmployeeById(id);
        List<Employee> employeeList = this.employeeService.selectEmpList();
        model.addAttribute("requestScope",employeeList);
        model.addAttribute("employee",employee);
        model.addAttribute("jobList",jobList);
        model.addAttribute("deptList",deptList);
        return "employee/employeeedit";
    }

    /**
     * 跳转到添加页面
     * @param model
     * @return
     */
    @RequestMapping(value = "empadd.action")
    public String toAddEmp(Model model) {
        List<Job> jobList = this.jobService.selectAllJoblist();
        List<Dept> deptList = this.deptService.selectDeptList();
        model.addAttribute("jobList",jobList);
        model.addAttribute("deptList",deptList);
        model.addAttribute("val","add");
        return "employee/employeeedit";
    }

    /**
     * 添加或修改
     * @param employee
     * @return
     */
    @RequestMapping(value = "emsaveOrUpdate.action")
    public String updateOrAddEmp(Employee employee) {
        if (employee.getId()!=null) {  //修改操作
            this.employeeService.updateEmployee(employee);
        } else {//添加
            this.employeeService.insertEmployee(employee);
        }
        return "redirect:emplist.action";
    }

    /**
     * 模糊查询
     * @param employee
     * @return
     */
    @RequestMapping(value = "emplistlike.action")
    public String selectEmployeeLike(Employee employee,Model model,@RequestParam(name = "pageNum",required = false,defaultValue = "1")int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<Job> jobList = this.jobService.selectAllJoblist();
        List<Dept> deptList = this.deptService.selectDeptList();
        List<Employee> employeeList = this.employeeService.selectEmployeeLike(employee);
        PageInfo<Employee> pageInfo = new PageInfo(employeeList);
        model.addAttribute("pageModel",pageInfo);
        model.addAttribute("jobList",jobList);
        model.addAttribute("deptList",deptList);
        return "employee/employeelist";
    }

    @RequestMapping(value = "empdel.action")
    public String deleteEmps(String[] empIds) {
        this.employeeService.deleteEmployees(empIds);
        return "redirect:emplist.action";
    }
}