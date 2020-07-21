package com.hzgy.client.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import com.hzgy.client.user.service.base.IBaseRoleClient;

@FeignClient(name="${service.prefix}-user-service")
public interface IRoleClient extends IBaseRoleClient{


}
