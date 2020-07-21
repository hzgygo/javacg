package com.hzgy.user.modules.userrolerelation.base;

import com.hzgy.core.entity.BaseInput;
import io.swagger.annotations.ApiModelProperty;

public abstract class BaseUserRoleRelationCriteriaInput extends BaseInput {


    @ApiModelProperty(hidden=true)
    private String userIdOperator;

    @ApiModelProperty(hidden=true)
    private String userIdExpression;

    @ApiModelProperty(hidden=true)
    private String roleIdOperator;

    @ApiModelProperty(hidden=true)
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
