package com.hzgy.user.modules.rolepermitrelation.base;

import com.hzgy.user.modules.role.entity.RolePo;
import com.hzgy.user.modules.permission.entity.PermissionPo;

public abstract class BaseRolePermitRelationPo extends BaseRolePermitRelationCriteriaPo {


    /**角色ID-**/
    private Long roleId;

    /**权限ID-系统权限代码，编码为数字，具有一定的规律。格式如下：1，11，111，112，12，121，122，13，131，132……**/
    private Long permitId;

	/**角色信息表**/
	private RolePo role;

	/**权限管理信息存储表**/
	private PermissionPo permission;


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

	public RolePo getRole() {
		return role;
	}

	public void setRole(RolePo role) {
		this.role = role;
	}

	public PermissionPo getPermission() {
		return permission;
	}

	public void setPermission(PermissionPo permission) {
		this.permission = permission;
	}
}
