package com.hzgy.user.modules.permission;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzgy.user.modules.permission.base.BasePermissionService;

@Service
public class PermissionService extends BasePermissionService implements IPermissionService{

    private static Logger logger = LoggerFactory.getLogger(PermissionService.class);

	@Override
	public void init(){

	}
}
