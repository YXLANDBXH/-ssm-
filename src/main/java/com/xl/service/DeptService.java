package com.xl.service;

import com.xl.domain.Dept;
import com.xl.domain.User;

import java.util.List;

/**
 * @author XLong
 * @create 2021-07-16 22:41
 */
public interface DeptService {
    //查询部门信息
    List<Dept> selectDeptList();
    //根据id查询部门信息
    Dept selectDeptById(String id);
    //修改部门信息
    void updateDept(Dept dept);
    //插入部门信息
    void insertUser(Dept dept);
    //删除
    void deleteDepts(String[] ids);
    //部门模糊查询
    List<Dept> selectDeptLike(Dept dept);
}
