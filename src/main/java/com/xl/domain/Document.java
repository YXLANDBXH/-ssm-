package com.xl.domain;

import java.util.Date;

public class Document {
    private Integer id;

    private String title;

    private String filename;

    private String filetype;

    private String filebytes;

    private String remark;

    private Date creatDate;

    private Integer userId;

    private User user;

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", filename='" + filename + '\'' +
                ", filetype='" + filetype + '\'' +
                ", filebytes='" + filebytes + '\'' +
                ", remark='" + remark + '\'' +
                ", creatDate=" + creatDate +
                ", userId=" + userId +
                ", user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype == null ? null : filetype.trim();
    }

    public String getFilebytes() {
        return filebytes;
    }

    public void setFilebytes(String filebytes) {
        this.filebytes = filebytes == null ? null : filebytes.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}