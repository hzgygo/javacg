package com.hzgy.user.modules.rolepermitrelation.base;

import com.hzgy.core.entity.BaseOutput;
import com.hzgy.user.modules.role.entity.RoleOutput;
import com.hzgy.user.modules.permission.entity.PermissionOutput;

public abstract class BaseRolePermitRelationOutput extends BaseOutput {


	/**角色ID-**/
	private Long roleId;

	/**权限ID-系统权限代码，编码为数字，具有一定的规律。格式如下：1，11，111，112，12，121，122，13，131，132……**/
	private Long permitId;

	/**角色信息表**/
	private RoleOutput role;

	/**权限管理信息存储表**/
	private PermissionOutput permission;

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

	public RoleOutput getRole() {
		return role;
	}

	public void setRole(RoleOutput role) {
		this.role = role;
	}

	public PermissionOutput getPermission() {
		return permission;
	}

	public void setPermission(PermissionOutput permission) {
		this.permission = permission;
	}
}
