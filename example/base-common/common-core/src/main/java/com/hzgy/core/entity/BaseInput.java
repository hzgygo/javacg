package com.hzgy.core.entity;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

/**
 * 视图层基类实体bean
 */
public abstract class BaseInput extends BaseVo{

    /**
     * 验证方法名称
     */
    public final static String VALID_METHOD_NAME = "_validated";

    /**
     * 自定义验证业务逻辑
     *
     * @param groups 分组名称，可以为空
     * @param args   验证的参数
     * @return 返回 BeanPropertyBindingResult 对象
     */
    public abstract BindingResult _validated(Class<?>[] groups, Object[] args);

    /**
     * 通用主键ID
     */
    @ApiModelProperty(hidden=true)
    private Long id;

    /**
     * 长整形数组参数
     */
    @ApiModelProperty(hidden=true)
    private List<Long> ids;

    /**
     * 排序值数组参数
     */
    @ApiModelProperty(hidden=true)
    private List<Integer> sortVals;

    /**
     * 分页开始位置
     */
    @ApiModelProperty(hidden=true)
    private Integer start;

    @ApiModelProperty(hidden=true)
    private Long nodeid;

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

    /**
     * 当前页
     */
    @ApiModelProperty(hidden=true)
    private Integer page;

    /**
     * 每页行数
     */
    @ApiModelProperty(hidden=true)
    private Integer rows;

    /**
     * 管理后台登录用户机构ID
     */
    @ApiModelProperty(hidden=true)
    private Long loginUserId;

    /**
     * 管理后台登录用户名称
     */
    @ApiModelProperty(hidden=true)
    private String loginUserName;

    /**
     * 管理后台登录用户账户
     */
    @ApiModelProperty(hidden=true)
    private String loginUserAccount;

    /**
     * 管理后台登录用户机构ID
     */
    @ApiModelProperty(hidden=true)
    private Long loginUserOrgId;


    @ApiModelProperty(hidden=true)
    private String loginUserOrgIds;

    /**
     * 登录用户类型
     */
    @ApiModelProperty(hidden=true)
    private Integer loginUserType;

    /**
     * 登录用户所在机构代码
     */
    @ApiModelProperty(hidden=true)
    private String loginUserOrgCode;

    /**
     * 登录用户机构类型
     */
    @ApiModelProperty(hidden=true)
    private Integer loginUserOrgType;

    /**
     * 管理后台登录用户机构名称
     */
    @ApiModelProperty(hidden=true)
    private String loginUserOrgName;

    /**
     * 管理后台登录用户区块链资产账户id
     */
    @ApiModelProperty(hidden=true)
    private Long loginUserAssetAccountId;

    /**
     * 管理后台登录用户区块链资产账户地址
     */
    @ApiModelProperty(hidden=true)
    private String loginUserAssetAccountAddress;

    @ApiModelProperty(hidden=true)
    private Integer loginUserIsAdmin;

    /**
     * 树形列表节点id
     */
    @ApiModelProperty(hidden=true)
    private Integer isTreeRoot;

    @ApiModelProperty(hidden=true)
    private Integer isContainParent;

    /**
     * 动态条件查询
     */
    @ApiModelProperty(hidden=true)
    private String searchCriteria;

    @ApiModelProperty(hidden=true)
    private String invokeId;


    @ApiModelProperty(hidden=true)
    private Integer loginUserDataAuthType;

    @ApiModelProperty(hidden=true)
    private Map<Integer,List<Long>> loginUserDataAuths;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(Long loginUserId) {
        this.loginUserId = loginUserId;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public Integer getIsTreeRoot() {
        return isTreeRoot;
    }

    public void setIsTreeRoot(Integer isTreeRoot) {
        this.isTreeRoot = isTreeRoot;
    }

    public Long getLoginUserOrgId() {
        return loginUserOrgId;
    }

    public void setLoginUserOrgId(Long loginUserOrgId) {
        this.loginUserOrgId = loginUserOrgId;
    }

    public String getLoginUserOrgName() {
        return loginUserOrgName;
    }

    public void setLoginUserOrgName(String loginUserOrgName) {
        this.loginUserOrgName = loginUserOrgName;
    }

    public String getLoginUserAccount() {
        return loginUserAccount;
    }

    public void setLoginUserAccount(String loginUserAccount) {
        this.loginUserAccount = loginUserAccount;
    }

    public Long getLoginUserAssetAccountId() {
        return loginUserAssetAccountId;
    }

    public void setLoginUserAssetAccountId(Long loginUserAssetAccountId) {
        this.loginUserAssetAccountId = loginUserAssetAccountId;
    }

    public String getLoginUserAssetAccountAddress() {
        return loginUserAssetAccountAddress;
    }

    public void setLoginUserAssetAccountAddress(String loginUserAssetAccountAddress) {
        this.loginUserAssetAccountAddress = loginUserAssetAccountAddress;
    }

    public Integer getLoginUserType() {
        return loginUserType;
    }

    public void setLoginUserType(Integer loginUserType) {
        this.loginUserType = loginUserType;
    }

    public String getLoginUserOrgCode() {
        return loginUserOrgCode;
    }

    public void setLoginUserOrgCode(String loginUserOrgCode) {
        this.loginUserOrgCode = loginUserOrgCode;
    }

    public Integer getLoginUserOrgType() {
        return loginUserOrgType;
    }

    public void setLoginUserOrgType(Integer loginUserOrgType) {
        this.loginUserOrgType = loginUserOrgType;
    }

    public Long getNodeid() {
        return nodeid;
    }

    public void setNodeid(Long nodeid) {
        this.nodeid = nodeid;
    }

    public String getLoginUserOrgIds() {
        return loginUserOrgIds;
    }

    public void setLoginUserOrgIds(String loginUserOrgIds) {
        this.loginUserOrgIds = loginUserOrgIds;
    }

    public List<Integer> getSortVals() {
        return sortVals;
    }

    public void setSortVals(List<Integer> sortVals) {
        this.sortVals = sortVals;
    }

    public Integer getLoginUserIsAdmin() {
        return loginUserIsAdmin;
    }

    public void setLoginUserIsAdmin(Integer loginUserIsAdmin) {
        this.loginUserIsAdmin = loginUserIsAdmin;
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

    public Integer getIsContainParent() {
        return isContainParent;
    }

    public void setIsContainParent(Integer isContainParent) {
        this.isContainParent = isContainParent;
    }

    public String getInvokeId() {
        return invokeId;
    }

    public void setInvokeId(String invokeId) {
        this.invokeId = invokeId;
    }

    public Integer getLoginUserDataAuthType() {
        return loginUserDataAuthType;
    }

    public void setLoginUserDataAuthType(Integer loginUserDataAuthType) {
        this.loginUserDataAuthType = loginUserDataAuthType;
    }

    public Map<Integer, List<Long>> getLoginUserDataAuths() {
        return loginUserDataAuths;
    }

    public void setLoginUserDataAuths(Map<Integer, List<Long>> loginUserDataAuths) {
        this.loginUserDataAuths = loginUserDataAuths;
    }
}
