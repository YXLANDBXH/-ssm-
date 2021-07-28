package com.xl.service;

import com.xl.domain.Dept;
import com.xl.domain.DeptExample;
import com.xl.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XLong
 * @create 2021-07-16 22:41
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    /**
     * 查询用户列表信息
     * @return
     */
    @Override
    public List<Dept> selectDeptList() {
        return this.deptMapper.selectByExample(null);
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @Override
    public Dept selectDeptById(String id) {
        return this.deptMapper.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 更新
     * @param dept
     */
    @Override
    public void updateDept(Dept dept) {
        this.deptMapper.updateByPrimaryKeySelective(dept);
    }

    /**
     * 插入
     * @param dept
     */
    @Override
    public void insertUser(Dept dept) {
        this.deptMapper.insertSelective(dept);
    }

    @Override
    public void deleteDepts(String[] ids) {
        for (int i=0;i<ids.length;i++) {
            this.deptMapper.deleteByPrimaryKey(Integer.parseInt(ids[i]));
        }
    }

    /**
     * 根据名称模糊查询
     * @param dept
     * @return
     */
    @Override
    public List<Dept> selectDeptLike(Dept dept) {
        DeptExample deptExample = new DeptExample();
        DeptExample.Criteria criteria = deptExample.createCriteria();
        if (dept!=null) {
            if (dept.getDname()!=null && !dept.getDname().equals("")) {
                criteria.andDnameLike(dept.getDname());
            }
        }
        return this.deptMapper.selectByExample(deptExample);
    }
}
