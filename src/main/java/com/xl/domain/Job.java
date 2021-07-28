package com.xl.domain;

public class Job {
    private Integer id;

    private String jname;

    private String jremark;

    private Integer jstate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname == null ? null : jname.trim();
    }

    public String getJremark() {
        return jremark;
    }

    public void setJremark(String jremark) {
        this.jremark = jremark == null ? null : jremark.trim();
    }

    public Integer getJstate() {
        return jstate;
    }

    public void setJstate(Integer jstate) {
        this.jstate = jstate;
    }
}