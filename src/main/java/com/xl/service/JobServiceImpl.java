package com.xl.service;

import com.xl.domain.Job;
import com.xl.domain.JobExample;
import com.xl.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XLong
 * @create 2021-07-17 11:05
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobMapper jobMapper;

    @Override
    public List<Job> selectAllJoblist() {
        return this.jobMapper.selectByExample(null);
    }

    @Override
    public void updateJob(Job job) {
        this.jobMapper.updateByPrimaryKeySelective(job);
    }

    @Override
    public Job getJobById(String id) {
        return this.jobMapper.selectByPrimaryKey(Integer.parseInt(id));
    }

    @Override
    public void insertJob(Job job) {
        this.jobMapper.insertSelective(job);
    }

    @Override
    public void deleteJobs(String[] ids) {
        for (int i=0;i<ids.length;i++) {
            this.jobMapper.deleteByPrimaryKey(Integer.parseInt(ids[i]));
        }
    }

    @Override
    public List<Job> selectJobsLike(Job job) {
        JobExample jobExample = new JobExample();
        JobExample.Criteria criteria = jobExample.createCriteria();
        if (job!=null) {
            if (job.getJname()!=null && !job.getJname().equals("")) {
                criteria.andJnameLike(job.getJname());
            }
        }
        return this.jobMapper.selectByExample(jobExample);
    }
}
