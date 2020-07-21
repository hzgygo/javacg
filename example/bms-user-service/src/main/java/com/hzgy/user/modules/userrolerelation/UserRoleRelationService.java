package com.hzgy.user.modules.userrolerelation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzgy.user.modules.userrolerelation.base.BaseUserRoleRelationService;

@Service
public class UserRoleRelationService extends BaseUserRoleRelationService implements IUserRoleRelationService{

    private static Logger logger = LoggerFactory.getLogger(UserRoleRelationService.class);

	@Override
	public void init(){

	}
}
