package com.hzgy.user.modules.permission.base;

import com.hzgy.user.modules.rolepermitrelation.entity.RolePermitRelationPo;
import java.util.List;

public abstract class BasePermissionPo extends BasePermissionCriteriaPo {


    /**权限ID-**/
    private Long id;

    /**权限代码-**/
    private Long code;

    /**父权限代码-父权限代码，编码也为数字，格式同上（code）。**/
    private Long parentCode;

    /**权限名称-权限名称，用2-6个汉字进行规整描述，以方便在页面显示。**/
    private String name;

    /**父权限名称-权限名称，用2-6个汉字进行规整描述，以方便在页面显示。**/
    private String parentName;

    /**服务代码-**/
    private String serviceCode;

    /**访问路径-**/
    private String urlPath;

    /**通配符路径-树形结构描述关系**/
    private String wildcardPath;

    /**模型名称-**/
    private String modelName;

    /**实体bean名称-**/
    private String entityName;

    /**方法名称-**/
    private String methodName;

    /**视图名称-**/
    private String viewName;

    /**权限代码识别标识-**/
    private String codeKey;

    /**树形结构权限级别-此权限是否有效。1：有效 0：无效**/
    private Integer treeLevel;

    /**树形结构描述关系-树形结构描述关系**/
    private String treePath;

    /**权限类型-0：授权权限 1：非授权权限**/
    private Integer authType;

    /**是否显示-0：否 1：是**/
    private Integer isShow;

    /**是否子节点-0：否 1：是**/
    private Integer isChildNode;

    /**是否删除-1：是 0：否**/
    private Integer isDelete;

    /**创建时间-创建时间：角色的创建时间，为以后进行跟踪查询。**/
    private String createTime;

    /**修改时间-**/
    private String modifyTime;

    /**备注-**/
    private String comments;

	/**用户角色关系映射表**/
	private List<RolePermitRelationPo> listRolePermitRelation;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Long getParentCode() {
		return parentCode;
	}

	public void setParentCode(Long parentCode) {
		this.parentCode = parentCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public String getWildcardPath() {
		return wildcardPath;
	}

	public void setWildcardPath(String wildcardPath) {
		this.wildcardPath = wildcardPath;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getCodeKey() {
		return codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	public Integer getTreeLevel() {
		return treeLevel;
	}

	public void setTreeLevel(Integer treeLevel) {
		this.treeLevel = treeLevel;
	}

	public String getTreePath() {
		return treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	public Integer getAuthType() {
		return authType;
	}

	public void setAuthType(Integer authType) {
		this.authType = authType;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Integer getIsChildNode() {
		return isChildNode;
	}

	public void setIsChildNode(Integer isChildNode) {
		this.isChildNode = isChildNode;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<RolePermitRelationPo> getListRolePermitRelation() {
		return listRolePermitRelation;
	}

	public void setListRolePermitRelation(List<RolePermitRelationPo> listRolePermitRelation) {
		this.listRolePermitRelation = listRolePermitRelation;
	}
}
