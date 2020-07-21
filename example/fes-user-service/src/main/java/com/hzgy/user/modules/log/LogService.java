package com.hzgy.user.modules.log;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzgy.user.modules.log.base.BaseLogService;

@Service
public class LogService extends BaseLogService implements ILogService{

    private static Logger logger = LoggerFactory.getLogger(LogService.class);

	@Override
	public void init(){

	}
}
