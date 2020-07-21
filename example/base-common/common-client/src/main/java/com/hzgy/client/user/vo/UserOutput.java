package com.hzgy.client.user.vo;

import com.hzgy.client.user.vo.base.BaseUser;
import java.util.List;

public class UserOutput extends BaseUser{

    /**用户角色关系映射表**/
    private List<UserRoleRelationOutput> listUserRoleRelationOutput;


    /**机构信息表**/
    private OrganizationOutput organizationOutput;

    public List<UserRoleRelationOutput> getListUserRoleRelationOutput() {
        return listUserRoleRelationOutput;
    }

    public void setListUserRoleRelationOutput(List<UserRoleRelationOutput> listUserRoleRelationOutput) {
        this.listUserRoleRelationOutput = listUserRoleRelationOutput;
    }

    public OrganizationOutput getOrganizationOutput() {
        return organizationOutput;
    }

    public void setOrganizationOutput(OrganizationOutput organizationOutput) {
        this.organizationOutput = organizationOutput;
    }
}
