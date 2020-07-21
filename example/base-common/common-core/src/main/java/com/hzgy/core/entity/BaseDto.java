package com.hzgy.core.entity;


/**
 * 业务传输层基类实体bean
 */
public class BaseDto implements java.io.Serializable {

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页行数
     */
    private Integer rows;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
