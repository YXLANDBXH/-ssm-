package com.xl.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xl.domain.Dept;
import com.xl.service.DeptService;
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
public class DeptController {

    //设置每页显示的条数
    private static final int pageSize = 4;

    @Autowired
    private DeptService deptService;

    /**
     * 部门分页查询
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "deptlist.action")
    public String toDeptlist(Model model,@RequestParam(name = "pageNum",required = false,defaultValue = "1") int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<Dept> deptList = this.deptService.selectDeptList();
        PageInfo<Dept> pageInfo = new PageInfo<Dept>(deptList);
        model.addAttribute("pageModel",pageInfo);
        return "dept/deptlist";
    }

    /**
     * 部门修改数据回显
     * @return
     */
    @RequestMapping(value = "viewDept.action")
    public String toviewDept(String id,Model model) {
        Dept dept = this.deptService.selectDeptById(id);
        model.addAttribute("dept",dept);
        return "dept/deptedit";
    }


    /**
     * 跳转到部门添加页面
     * @return
     */
    @RequestMapping(value = "deptadd.action")
    public String toDeptAdd(Model model) {
        model.addAttribute("val","add");
        return "dept/deptedit";
    }

    /**
     * 修改用户信息
     * @param dept
     * @return
     */
    @RequestMapping(value = "saveOrUpdate.action")
    public String updateDept(Dept dept) {
        dept.setDstatus(0);
        if (dept.getId()!=null && !dept.getId().equals("")) {  //修改
            this.deptService.updateDept(dept);
        } else {  //添加操作
            this.deptService.insertUser(dept);
        }
        return "redirect:deptlist.action";
    }

    @RequestMapping(value = "deptdel.action")
    public String delDept(String []ids) {
        this.deptService.deleteDepts(ids);
        return "redirect:deptlist.action";
    }

    @RequestMapping(value = "deptlistlike.action")
    public String queryDeptLike(Dept dept,Model model,@RequestParam(name = "pageNum",required = false,defaultValue = "1") int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<Dept> deptList = this.deptService.selectDeptLike(dept);
        PageInfo<Dept> pageInfo = new PageInfo<Dept>(deptList);
        model.addAttribute("pageModel",pageInfo);
        return "dept/deptlist";
    }
}
