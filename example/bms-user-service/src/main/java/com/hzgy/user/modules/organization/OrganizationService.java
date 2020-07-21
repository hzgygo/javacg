package com.hzgy.user.modules.organization;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzgy.user.modules.organization.base.BaseOrganizationService;

@Service
public class OrganizationService extends BaseOrganizationService implements IOrganizationService{

    private static Logger logger = LoggerFactory.getLogger(OrganizationService.class);

	@Override
	public void init(){

	}
}
