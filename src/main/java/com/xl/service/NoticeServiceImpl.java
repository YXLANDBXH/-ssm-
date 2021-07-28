package com.xl.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.xl.domain.Notice;
import com.xl.domain.NoticeExample;
import com.xl.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XLong
 * @create 2021-07-17 16:12
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> selectAllNoticelist() {
        return this.noticeMapper.selectByExample(null);
    }

    @Override
    public void updateNotice(Notice notice) {
        this.noticeMapper.updateByPrimaryKeySelective(notice);
    }

    @Override
    public Notice getNoticeById(String id) {
        return this.noticeMapper.selectByPrimaryKey(Integer.parseInt(id));
    }

    @Override
    public void insertNotice(Notice notice) {
        this.noticeMapper.insertSelective(notice);
    }

    @Override
    public List<Notice> selectNoticesLike(Notice notice) {
        NoticeExample noticeExample = new NoticeExample();
        NoticeExample.Criteria criteria = noticeExample.createCriteria();
        if (notice!=null) {
            if (notice.getName()!=null && !notice.equals("")) {
                criteria.andNameLike(notice.getName());
            }
        }
        return this.noticeMapper.selectByExample(noticeExample);
    }

    @Override
    public void deleteNotices(String[] ids) {
        for (int i = 0;i < ids.length;i++) {
            this.noticeMapper.deleteByPrimaryKey(Integer.parseInt(ids[i]));
        }
    }
}
