package com.hzgy.client.user.vo;

import com.hzgy.client.user.vo.base.BasePermission;
import java.util.List;

public class PermissionOutput extends BasePermission{

    /**用户角色关系映射表**/
    private List<RolePermitRelationOutput> listRolePermitRelationOutput;


    public List<RolePermitRelationOutput> getListRolePermitRelationOutput() {
        return listRolePermitRelationOutput;
    }

    public void setListRolePermitRelationOutput(List<RolePermitRelationOutput> listRolePermitRelationOutput) {
        this.listRolePermitRelationOutput = listRolePermitRelationOutput;
    }
}
