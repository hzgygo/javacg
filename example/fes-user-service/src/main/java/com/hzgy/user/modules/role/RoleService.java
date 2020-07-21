package com.hzgy.user.modules.role;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzgy.user.modules.role.base.BaseRoleService;

@Service
public class RoleService extends BaseRoleService implements IRoleService{

    private static Logger logger = LoggerFactory.getLogger(RoleService.class);

	@Override
	public void init(){

	}
}
