package com.hzgy.user.modules.rolepermitrelation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzgy.user.modules.rolepermitrelation.base.BaseRolePermitRelationService;

@Service
public class RolePermitRelationService extends BaseRolePermitRelationService implements IRolePermitRelationService{

    private static Logger logger = LoggerFactory.getLogger(RolePermitRelationService.class);

	@Override
	public void init(){

	}
}
