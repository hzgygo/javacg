package com.hzgy.user.modules.organization.base;

import com.hzgy.core.entity.BaseOutput;
import com.hzgy.user.modules.user.entity.UserOutput;
import java.util.List;

public abstract class BaseOrganizationOutput extends BaseOutput {


	/**机构ID-父机构ID：机构树状结构父节点标识**/
	private Long id;

	/**父机构ID-**/
	private Long parentId;

	/**组织机构代码-机构代码**/
	private String orgCode;

	/**机构名称-机构名称：定义记录机构的名称。**/
	private String orgName;

	/**机构类型-1：平台方 2：企业方**/
	private Integer orgType;

	/**邮箱地址-**/
	private String email;

	/**手机号-**/
	private String mobilePhone;

	/**企业域名-**/
	private String domainName;

	/**企业logo-**/
	private String logo;

	/**实名状态-0：否 1：是**/
	private Integer isRealname;

	/**是否禁用-0：否 1：是**/
	private Integer isForbidden;

	/**树形结构机构级别-**/
	private Integer treeLevel;

	/**树形结构描述关系-**/
	private String treePath;

	/**是否为子结点-1：是 0：否**/
	private Integer isChildNode;

	/**区块链地址-**/
	private String blockchainAddress;

	/**删除状态-0：未删除 1：删除**/
	private Integer isDelete;

	/**创建时间-创建时间：机构创建的时间，以备后来跟踪查询**/
	private String createTime;

	/**修改时间-**/
	private String modifyTime;

	/**备注-**/
	private String comments;

	/**用户信息表**/
	private List<UserOutput> listUser;

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

	public List<UserOutput> getListUser() {
		return listUser;
	}

	public void setListUser(List<UserOutput> listUser) {
		this.listUser = listUser;
	}
}
