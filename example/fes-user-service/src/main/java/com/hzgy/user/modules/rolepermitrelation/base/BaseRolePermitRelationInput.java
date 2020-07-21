package com.hzgy.user.modules.rolepermitrelation.base;

import io.swagger.annotations.ApiModelProperty;
	import com.alibaba.fastjson.annotation.JSONField;
	import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.hzgy.user.modules.role.entity.RoleInput;
import com.hzgy.user.modules.permission.entity.PermissionInput;

public abstract class BaseRolePermitRelationInput extends BaseRolePermitRelationCriteriaInput {


	/**角色ID-**/
    @ApiModelProperty(hidden=true)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long roleId;

	/**权限ID-系统权限代码，编码为数字，具有一定的规律。格式如下：1，11，111，112，12，121，122，13，131，132……**/
    @ApiModelProperty(hidden=true)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long permitId;

	/**角色信息表**/
	@ApiModelProperty(hidden=true)
	private RoleInput role;

	/**权限管理信息存储表**/
	@ApiModelProperty(hidden=true)
	private PermissionInput permission;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getPermitId() {
		return permitId;
	}

	public void setPermitId(Long permitId) {
		this.permitId = permitId;
	}

	public RoleInput getRole() {
		return role;
	}

	public void setRole(RoleInput role) {
		this.role = role;
	}

	public PermissionInput getPermission() {
		return permission;
	}

	public void setPermission(PermissionInput permission) {
		this.permission = permission;
	}
}
