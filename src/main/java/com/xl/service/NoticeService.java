package com.xl.service;

import com.xl.domain.Notice;
import com.xl.domain.Type;

import java.util.List;

/**
 * @author XLong
 * @create 2021-07-17 16:11
 */
public interface NoticeService {
    //职位查询
    List<Notice> selectAllNoticelist();
    //根据id编辑职位信息
    void updateNotice(Notice notice);
    //根据id查询
    Notice getNoticeById(String id);
    //插入数据
    void insertNotice(Notice notice);
    //模糊查询
    List<Notice> selectNoticesLike(Notice notice);
    //删除
    void deleteNotices(String[] ids);
}
