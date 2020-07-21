package com.hzgy.client.user.vo.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

public abstract class BaseUserRoleRelation extends BaseUserRoleRelationCriteria{

	
	/**用户ID-用户随机编号：系统生成**/
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long userId;
	
	/**角色ID-**/
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long roleId;


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
}
