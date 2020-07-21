package com.hzgy.client.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import com.hzgy.client.user.service.base.IBasePermissionClient;

@FeignClient(name="${service.prefix}-user-service")
public interface IPermissionClient extends IBasePermissionClient{


}
