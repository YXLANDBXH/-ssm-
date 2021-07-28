package com.xl.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.glassfish.gmbal.ParameterNames;
import com.xl.domain.Job;
import com.xl.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import java.util.List;

/**
 * @author XLong
 * @create 2021-07-16 19:00
 */
@Controller
public class JobController {

    //设置每页显示的条数
    private static final int pageSize = 4;

    @Autowired
    private JobService jobService;

    /**
     * 职位分页查询
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "joblist.action")
    public String toJobList(Model model,@RequestParam(name = "pageNum",required = false,defaultValue = "1") int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<Job> jobList = this.jobService.selectAllJoblist();
        PageInfo<Job> pageInfo = new PageInfo<Job>(jobList);
        model.addAttribute("pageModel",pageInfo);
        return "job/joblist";
    }

    /**
     * 修改数据回显
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "viewJob.action")
    public String toUpdateJobView(String id, Model model) {
        Job job = this.jobService.getJobById(id);
        model.addAttribute("job",job);
        return "job/jobedit";
    }

    /**
     * 跳转到添加页面
     * @param model
     * @return
     */
    @RequestMapping(value = "jobadd.action")
    public String toAddJobView(Model model) {
        model.addAttribute("val","add");
        return "job/jobedit";
    }

    @RequestMapping(value = "jobdel.action")
    public String deltJobs(String ids[]) {
        this.jobService.deleteJobs(ids);
        return "redirect:joblist.action";
    }

    @RequestMapping(value = "joblistlike.action")
    public String selectJobsLike(Job job,Model model,@RequestParam(name = "pageNum",required = false,defaultValue = "1") int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<Job> jobList = this.jobService.selectJobsLike(job);
        PageInfo<Job> pageInfo = new PageInfo<Job>(jobList);
        model.addAttribute("pageModel",pageInfo);
        return "job/joblist";
    }
}
