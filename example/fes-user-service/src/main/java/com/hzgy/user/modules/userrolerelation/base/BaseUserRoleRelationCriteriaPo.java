package com.hzgy.user.modules.userrolerelation.base;

import com.hzgy.core.entity.BasePo;

public abstract class BaseUserRoleRelationCriteriaPo extends BasePo {


    private String userIdOperator;

    private String userIdExpression;

    private String roleIdOperator;

    private String roleIdExpression;


    public String getUserIdOperator() {
        return userIdOperator;
    }

    public void setUserIdOperator(String userIdOperator) {
        this.userIdOperator = userIdOperator;
    }

    public String getUserIdExpression() {
        return userIdExpression;
    }

    public void setUserIdExpression(String userIdExpression) {
        this.userIdExpression = userIdExpression;
    }

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

}
