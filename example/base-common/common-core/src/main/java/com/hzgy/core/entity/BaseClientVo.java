package com.hzgy.core.entity;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.BindingResult;

/**
 * 视图层基类实体bean
 */
public abstract class BaseClientVo extends BaseVo implements java.io.Serializable {

    private String loginUserOrgIds;

    public String getLoginUserOrgIds() {
        return loginUserOrgIds;
    }

    public void setLoginUserOrgIds(String loginUserOrgIds) {
        this.loginUserOrgIds = loginUserOrgIds;
    }
}
