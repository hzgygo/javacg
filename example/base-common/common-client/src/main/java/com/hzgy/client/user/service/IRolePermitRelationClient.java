package com.hzgy.client.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import com.hzgy.client.user.service.base.IBaseRolePermitRelationClient;

@FeignClient(name="${service.prefix}-user-service")
public interface IRolePermitRelationClient extends IBaseRolePermitRelationClient{


}
