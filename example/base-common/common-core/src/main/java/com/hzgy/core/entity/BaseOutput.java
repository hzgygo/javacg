package com.hzgy.core.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 视图层返回基类实体bean
 */
public abstract class BaseOutput extends BaseVo {

    /**
     * 树形列表标识
     */
    @ApiModelProperty(hidden=true)
    private Boolean leaf;

    /**
     * 树形列表是否展开标识
     */
    @ApiModelProperty(hidden=true)
    private Boolean expanded;


    /**
     * 树形列表是否异步加载
     */
    @ApiModelProperty(hidden=true)
    private Boolean  loaded;

    /**
     * 数组参数
     */
    @ApiModelProperty(hidden=true)
    private List<Long> ids;

    @ApiModelProperty(hidden=true)
    private String loginUserOrgIds;

    /**
     * 分页开始位置
     */
    @ApiModelProperty(hidden=true)
    private Integer start;

    /**
     * 分页偏移量
     */
    @ApiModelProperty(hidden=true)
    private Integer offset;

    /**
     * 排序字段,降序
     */
    @ApiModelProperty(hidden=true)
    private String dorder [];

    /**
     * 排序字段,升序
     */
    @ApiModelProperty(hidden=true)
    private String aorder [];


    /**
     * 排序字段
     */
    @ApiModelProperty(hidden=true)
    private String sorder;

    /**
     * 分组字段
     */
    @ApiModelProperty(hidden=true)
    private String sgroup [];

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


    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public Boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    public Boolean getLoaded() {
        return loaded;
    }

    public void setLoaded(Boolean loaded) {
        this.loaded = loaded;
    }

    public String getLoginUserOrgIds() {
        return loginUserOrgIds;
    }

    public void setLoginUserOrgIds(String loginUserOrgIds) {
        this.loginUserOrgIds = loginUserOrgIds;
    }

    public String getSorder() {
        return sorder;
    }

    public void setSorder(String sorder) {
        this.sorder = sorder;
    }

}
