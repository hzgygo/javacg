package com.hzgy.client.user.vo;

import com.hzgy.client.user.vo.base.BaseUserRoleRelation;

public class UserRoleRelationOutput extends BaseUserRoleRelation{


    /**角色信息表**/
    private RoleOutput roleOutput;

    /**用户信息表**/
    private UserOutput userOutput;


    public RoleOutput getRoleOutput() {
        return roleOutput;
    }

    public void setRoleOutput(RoleOutput roleOutput) {
        this.roleOutput = roleOutput;
    }

    public UserOutput getUserOutput() {
        return userOutput;
    }

    public void setUserOutput(UserOutput userOutput) {
        this.userOutput = userOutput;
    }
}
