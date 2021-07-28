package com.xl.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xl.domain.Notice;
import com.xl.domain.Type;
import com.xl.service.NoticeService;
import com.xl.service.TypeService;
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
public class NoticeController {

    //设置每页显示的条数
    private static final int pageSize = 4;

    @Autowired
    private TypeService typeService;

    @Autowired
    private NoticeService noticeService;

    /**
     * 分页查询
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "noticelist.action")
    public String selectNoticeList(Model model, @RequestParam(name = "pageNum",required = false,defaultValue = "1")int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<Notice> noticeList = this.noticeService.selectAllNoticelist();
        PageInfo<Notice> pageInfo = new PageInfo<Notice>(noticeList);
        model.addAttribute("pageModel",pageInfo);
        return "notice/noticelist";
    }

    /**
     * 跳转到公告添加页面
     * @return
     */
    @RequestMapping(value = "noticeadd.action")
    public String toNoticeAdd(Model model) {
        List<Type> typeList = this.typeService.selectAllTypelist();
        model.addAttribute("types",typeList);
        return "notice/notice_save_update";
    }

    /**
     * 公告修改回显数据
     * @return
     */
    @RequestMapping(value = "viewNotice.action")
    public String toNoticeUpdate(String id,Model model) {
        Notice notice = this.noticeService.getNoticeById(id);
        List<Type> typeList = this.typeService.selectAllTypelist();
        model.addAttribute("types",typeList);
        model.addAttribute("notice",notice);
        return "notice/notice_save_update";
    }

    /**
     * 修改或添加
     * @param notice
     * @return
     */
    @RequestMapping(value = "noticesaveOrUpdate.action")
    public String noticesaveOrUpdate(Notice notice) {
        if (notice.getId()!=null) { //修改
            this.noticeService.updateNotice(notice);
        } else { //添加
            this.noticeService.insertNotice(notice);
        }
        return "redirect:noticelist.action";
    }

    /**
     * 公告删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "noticedel.action")
    public String delNotices(String ids[]) {
        this.noticeService.deleteNotices(ids);
        return "redirect:noticelist.action";
    }

    /**
     * 模糊查询
     * @param notice
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "noticelistlike.action")
    public String selectNoticeListLike(Notice notice,Model model, @RequestParam(name = "pageNum",required = false,defaultValue = "1")int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<Notice> noticeList = this.noticeService.selectNoticesLike(notice);
        PageInfo<Notice> pageInfo = new PageInfo<Notice>(noticeList);
        model.addAttribute("pageModel",pageInfo);
        return "notice/noticelist";
    }
}
