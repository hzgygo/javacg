package com.hzgy.client.user.vo;

import com.hzgy.client.user.vo.base.BaseRole;
import java.util.List;

public class RoleOutput extends BaseRole{

    /**用户角色关系映射表**/
    private List<RolePermitRelationOutput> listRolePermitRelationOutput;

    /**用户角色关系映射表**/
    private List<UserRoleRelationOutput> listUserRoleRelationOutput;


    public List<RolePermitRelationOutput> getListRolePermitRelationOutput() {
        return listRolePermitRelationOutput;
    }

    public void setListRolePermitRelationOutput(List<RolePermitRelationOutput> listRolePermitRelationOutput) {
        this.listRolePermitRelationOutput = listRolePermitRelationOutput;
    }
    public List<UserRoleRelationOutput> getListUserRoleRelationOutput() {
        return listUserRoleRelationOutput;
    }

    public void setListUserRoleRelationOutput(List<UserRoleRelationOutput> listUserRoleRelationOutput) {
        this.listUserRoleRelationOutput = listUserRoleRelationOutput;
    }
}
