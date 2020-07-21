package com.hzgy.client.user.vo.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

public abstract class BaseRolePermitRelation extends BaseRolePermitRelationCriteria{

	
	/**角色ID-**/
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long roleId;
	
	/**权限ID-系统权限代码，编码为数字，具有一定的规律。格式如下：1，11，111，112，12，121，122，13，131，132……**/
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long permitId;


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
}
