package com.hzgy.user.modules.role.base;

import io.swagger.annotations.ApiModelProperty;
	import com.alibaba.fastjson.annotation.JSONField;
	import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.hzgy.user.modules.rolepermitrelation.entity.RolePermitRelationInput;
import com.hzgy.user.modules.userrolerelation.entity.UserRoleRelationInput;
import java.util.List;

public abstract class BaseRoleInput extends BaseRoleCriteriaInput {


	/**角色ID-**/
    @ApiModelProperty(hidden=true)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	/**所属机构Id-**/
    @ApiModelProperty(hidden=true)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long orgId;

	/**角色名称-**/
    @ApiModelProperty(hidden=true)
	private String name;

	/**角色代码-**/
    @ApiModelProperty(hidden=true)
	private String code;

	/**是否禁用-0：否 1：是**/
    @ApiModelProperty(hidden=true)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Integer isForbidden;

	/**是否删除-角色状态： 0：禁用 1：开启**/
    @ApiModelProperty(hidden=true)
	private Integer isDelete;

	/**创建时间-**/
    @ApiModelProperty(hidden=true)
	private String createTime;

	/**更新时间-**/
    @ApiModelProperty(hidden=true)
	private String modifyTime;

	/**备注-**/
    @ApiModelProperty(hidden=true)
	private String comments;

	/**用户角色关系映射表**/
	@ApiModelProperty(hidden=true)
	private List<RolePermitRelationInput> listRolePermitRelation;

	/**用户角色关系映射表**/
	@ApiModelProperty(hidden=true)
	private List<UserRoleRelationInput> listUserRoleRelation;

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

	public List<RolePermitRelationInput> getListRolePermitRelation() {
		return listRolePermitRelation;
	}

	public void setListRolePermitRelation(List<RolePermitRelationInput> listRolePermitRelation) {
		this.listRolePermitRelation = listRolePermitRelation;
	}

	public List<UserRoleRelationInput> getListUserRoleRelation() {
		return listUserRoleRelation;
	}

	public void setListUserRoleRelation(List<UserRoleRelationInput> listUserRoleRelation) {
		this.listUserRoleRelation = listUserRoleRelation;
	}
}
