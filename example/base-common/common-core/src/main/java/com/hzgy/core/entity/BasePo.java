package com.hzgy.core.entity;


import java.util.List;

/**
 * 数据库持久层基类实体bean
 */
public class BasePo implements java.io.Serializable {

    /**
     * 通用主键ID
     */
    private Long id;

    /**
     * 数组参数
     */
    private List<Long> ids;

    /**
     * 分页开始位置
     */
    private Integer start;

    /**
     * 分页偏移量
     */
    private Integer offset;

    private Long nodeid;

    /**
     * 排序字段,降序
     */
    private String dorder [];

    /**
     * 排序字段,升序
     */
    private String aorder [];

    /** order by */
    private String sorder;

    /**
     * 分组字段
     */
    private String sgroup [];

    private String searchCriteria;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String[] getDorder() {
        return dorder;
    }

    public void setDorder(String[] dorder) {
        this.dorder = dorder;
    }

    public String[] getAorder() {
        return aorder;
    }

    public void setAorder(String[] aorder) {
        this.aorder = aorder;
    }

    public String[] getSgroup() {
        return sgroup;
    }

    public void setSgroup(String[] sgroup) {
        this.sgroup = sgroup;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNodeid() {
        return nodeid;
    }

    public void setNodeid(Long nodeid) {
        this.nodeid = nodeid;
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public String getSorder() {
        return sorder;
    }

    public void setSorder(String sorder) {
        this.sorder = sorder;
    }
}
