package com.hzgy.client.user.vo;

import com.hzgy.client.user.vo.base.BaseRolePermitRelation;

public class RolePermitRelationOutput extends BaseRolePermitRelation{


    /**角色信息表**/
    private RoleOutput roleOutput;

    /**权限管理信息存储表**/
    private PermissionOutput permissionOutput;


    public RoleOutput getRoleOutput() {
        return roleOutput;
    }

    public void setRoleOutput(RoleOutput roleOutput) {
        this.roleOutput = roleOutput;
    }

    public PermissionOutput getPermissionOutput() {
        return permissionOutput;
    }

    public void setPermissionOutput(PermissionOutput permissionOutput) {
        this.permissionOutput = permissionOutput;
    }
}
