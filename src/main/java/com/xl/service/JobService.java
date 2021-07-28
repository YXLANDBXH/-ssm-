package com.xl.service;

import com.xl.domain.Job;

import java.util.List;

/**
 * @author XLong
 * @create 2021-07-17 11:01
 */
public interface JobService {

    //职位查询
    List<Job> selectAllJoblist();
    //根据id编辑职位信息
    void updateJob(Job job);
    //根据id查询
    Job getJobById(String id);
    //插入数据
    void insertJob(Job job);
    //删除
    void deleteJobs(String[] ids);
    //模糊查询
    List<Job> selectJobsLike(Job job);
}
