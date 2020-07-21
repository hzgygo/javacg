package com.hzgy.client.user.vo.base;

import com.hzgy.core.entity.BaseClientVo;

public abstract class BaseRolePermitRelationCriteria extends BaseClientVo{


    private String roleIdOperator;

    private String roleIdExpression;

    private String permitIdOperator;

    private String permitIdExpression;


    public String getRoleIdOperator() {
        return roleIdOperator;
    }

    public void setRoleIdOperator(String roleIdOperator) {
        this.roleIdOperator = roleIdOperator;
    }

    public String getRoleIdExpression() {
        return roleIdExpression;
    }

    public void setRoleIdExpression(String roleIdExpression) {
        this.roleIdExpression = roleIdExpression;
    }

    public String getPermitIdOperator() {
        return permitIdOperator;
    }

    public void setPermitIdOperator(String permitIdOperator) {
        this.permitIdOperator = permitIdOperator;
    }

    public String getPermitIdExpression() {
        return permitIdExpression;
    }

    public void setPermitIdExpression(String permitIdExpression) {
        this.permitIdExpression = permitIdExpression;
    }

}
