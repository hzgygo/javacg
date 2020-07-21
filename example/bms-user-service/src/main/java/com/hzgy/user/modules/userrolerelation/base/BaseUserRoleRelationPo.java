package com.hzgy.user.modules.userrolerelation.base;

import com.hzgy.user.modules.role.entity.RolePo;
import com.hzgy.user.modules.user.entity.UserPo;

public abstract class BaseUserRoleRelationPo extends BaseUserRoleRelationCriteriaPo {


    /**用户ID-用户随机编号：系统生成**/
    private Long userId;

    /**角色ID-**/
    private Long roleId;

	/**角色信息表**/
	private RolePo role;

	/**用户信息表**/
	private UserPo user;


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

	public RolePo getRole() {
		return role;
	}

	public void setRole(RolePo role) {
		this.role = role;
	}

	public UserPo getUser() {
		return user;
	}

	public void setUser(UserPo user) {
		this.user = user;
	}
}
