package com.hzgy.core.initialize;


import com.hzgy.core.service.BaseInitService;
import com.hzgy.core.spring.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *  系统数据初始化
 */

@Component
public class DataInitProcessor implements ApplicationListener<ContextRefreshedEvent> {

    private static Logger logger = LoggerFactory.getLogger(DataInitProcessor.class);

    @Resource
    private AutoDataProcessor autoDataProcessor;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("being init system data.....");
        try {
            BaseInitService initService = SpringContextUtil.getBean("authInitService");
            if (initService != null) {
                initService.initSecurityAuths();
            }
        }
        catch(Exception e){
            logger.info("not found authInitService!");
        }
        autoDataProcessor.execute();
        logger.info("init system data finish!");
    }
}
