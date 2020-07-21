package com.hzgy.user.modules.organization.base;

import io.swagger.annotations.ApiModelProperty;
	import com.alibaba.fastjson.annotation.JSONField;
	import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.hzgy.user.modules.user.entity.UserInput;
import java.util.List;

public abstract class BaseOrganizationInput extends BaseOrganizationCriteriaInput {


	/**机构ID-父机构ID：机构树状结构父节点标识**/
    @ApiModelProperty(hidden=true)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	/**父机构ID-**/
    @ApiModelProperty(hidden=true)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long parentId;

	/**组织机构代码-机构代码**/
    @ApiModelProperty(hidden=true)
	private String orgCode;

	/**机构名称-机构名称：定义记录机构的名称。**/
    @ApiModelProperty(hidden=true)
	private String orgName;

	/**机构类型-1：平台方 2：企业方**/
    @ApiModelProperty(hidden=true)
	private Integer orgType;

	/**邮箱地址-**/
    @ApiModelProperty(hidden=true)
	private String email;

	/**手机号-**/
    @ApiModelProperty(hidden=true)
	private String mobilePhone;

	/**企业域名-**/
    @ApiModelProperty(hidden=true)
	private String domainName;

	/**企业logo-**/
    @ApiModelProperty(hidden=true)
	private String logo;

	/**实名状态-0：否 1：是**/
    @ApiModelProperty(hidden=true)
	private Integer isRealname;

	/**是否禁用-0：否 1：是**/
    @ApiModelProperty(hidden=true)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Integer isForbidden;

	/**树形结构机构级别-**/
    @ApiModelProperty(hidden=true)
	private Integer treeLevel;

	/**树形结构描述关系-**/
    @ApiModelProperty(hidden=true)
	private String treePath;

	/**是否为子结点-1：是 0：否**/
    @ApiModelProperty(hidden=true)
	private Integer isChildNode;

	/**区块链地址-**/
    @ApiModelProperty(hidden=true)
	private String blockchainAddress;

	/**删除状态-0：未删除 1：删除**/
    @ApiModelProperty(hidden=true)
	private Integer isDelete;

	/**创建时间-创建时间：机构创建的时间，以备后来跟踪查询**/
    @ApiModelProperty(hidden=true)
	private String createTime;

	/**修改时间-**/
    @ApiModelProperty(hidden=true)
	private String modifyTime;

	/**备注-**/
    @ApiModelProperty(hidden=true)
	private String comments;

	/**用户信息表**/
	@ApiModelProperty(hidden=true)
	private List<UserInput> listUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Integer getOrgType() {
		return orgType;
	}

	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Integer getIsRealname() {
		return isRealname;
	}

	public void setIsRealname(Integer isRealname) {
		this.isRealname = isRealname;
	}
	public Integer getIsForbidden() {
		return isForbidden;
	}

	public void setIsForbidden(Integer isForbidden) {
		this.isForbidden = isForbidden;
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
	public Integer getIsChildNode() {
		return isChildNode;
	}

	public void setIsChildNode(Integer isChildNode) {
		this.isChildNode = isChildNode;
	}
	public String getBlockchainAddress() {
		return blockchainAddress;
	}

	public void setBlockchainAddress(String blockchainAddress) {
		this.blockchainAddress = blockchainAddress;
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

	public List<UserInput> getListUser() {
		return listUser;
	}

	public void setListUser(List<UserInput> listUser) {
		this.listUser = listUser;
	}
}
