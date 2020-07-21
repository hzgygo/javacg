package com.hzgy.user.modules.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzgy.user.modules.user.base.BaseUserService;

@Service
public class UserService extends BaseUserService implements IUserService{

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	public void init(){

	}
}
