package com.hzgy.client.user.vo;

import com.hzgy.client.user.vo.base.BaseOrganization;
import java.util.List;

public class OrganizationOutput extends BaseOrganization{

    /**用户信息表**/
    private List<UserOutput> listUserOutput;


    public List<UserOutput> getListUserOutput() {
        return listUserOutput;
    }

    public void setListUserOutput(List<UserOutput> listUserOutput) {
        this.listUserOutput = listUserOutput;
    }
}
