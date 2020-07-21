package com.hzgy.client.user.vo.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

public abstract class BaseRole extends BaseRoleCriteria{

	
	/**角色ID-**/
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;
	
	/**所属机构Id-**/
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long orgId;
	
	/**角色名称-**/
	private String name;
	
	/**角色代码-**/
	private String code;
	
	/**是否禁用-0：否 1：是**/
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Integer isForbidden;
	
	/**是否删除-角色状态： 0：禁用 1：开启**/
	private Integer isDelete;
	
	/**创建时间-**/
	private String createTime;
	
	/**更新时间-**/
	private String modifyTime;
	
	/**备注-**/
	private String comments;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getIsForbidden() {
		return isForbidden;
	}

	public void setIsForbidden(Integer isForbidden) {
		this.isForbidden = isForbidden;
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
}
