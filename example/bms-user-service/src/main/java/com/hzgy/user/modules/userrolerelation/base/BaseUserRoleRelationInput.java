package com.hzgy.user.modules.userrolerelation.base;

import io.swagger.annotations.ApiModelProperty;
	import com.alibaba.fastjson.annotation.JSONField;
	import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.hzgy.user.modules.role.entity.RoleInput;
import com.hzgy.user.modules.user.entity.UserInput;

public abstract class BaseUserRoleRelationInput extends BaseUserRoleRelationCriteriaInput {


	/**用户ID-用户随机编号：系统生成**/
    @ApiModelProperty(hidden=true)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long userId;

	/**角色ID-**/
    @ApiModelProperty(hidden=true)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long roleId;

	/**角色信息表**/
	@ApiModelProperty(hidden=true)
	private RoleInput role;

	/**用户信息表**/
	@ApiModelProperty(hidden=true)
	private UserInput user;

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

	public RoleInput getRole() {
		return role;
	}

	public void setRole(RoleInput role) {
		this.role = role;
	}

	public UserInput getUser() {
		return user;
	}

	public void setUser(UserInput user) {
		this.user = user;
	}
}
