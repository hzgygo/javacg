package com.hzgy.user.modules.rolepermitrelation.base;

import com.hzgy.core.entity.BasePo;

public abstract class BaseRolePermitRelationCriteriaPo extends BasePo {


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
