package com.hzgy.user.modules.userrolerelation.base;

import com.hzgy.core.entity.BaseOutput;
import com.hzgy.user.modules.role.entity.RoleOutput;
import com.hzgy.user.modules.user.entity.UserOutput;

public abstract class BaseUserRoleRelationOutput extends BaseOutput {


	/**用户ID-用户随机编号：系统生成**/
	private Long userId;

	/**角色ID-**/
	private Long roleId;

	/**角色信息表**/
	private RoleOutput role;

	/**用户信息表**/
	private UserOutput user;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public RoleOutput getRole() {
		return role;
	}

	public void setRole(RoleOutput role) {
		this.role = role;
	}

	public UserOutput getUser() {
		return user;
	}

	public void setUser(UserOutput user) {
		this.user = user;
	}
}
