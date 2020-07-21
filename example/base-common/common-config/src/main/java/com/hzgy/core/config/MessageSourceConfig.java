package com.hzgy.core.config;

import com.hzgy.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.annotation.Resource;
import javax.validation.Validator;
import java.util.ArrayList;

@Configuration
public class MessageSourceConfig {

	private static Logger logger = LoggerFactory.getLogger(MessageSourceConfig.class);

	private static final String PROPERTIES_SUFFIX = ".properties";
	private static final String CLASS = "classes";
	private static final String PART_FILE_NAME = "Messages";

	@Resource
	private Environment environment;

	/**
	 * spring 资源文件路径匹配处理类
	 */
	private ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

	/**
	 * 加载所有国际化资源文件
	 * @return 返回 ResourceBundleMessageSource
	 * @throws Exception 错误异常
	 */
	@Bean(name="messageSource")
	public ResourceBundleMessageSource getMessageSource() throws Exception {
		ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();
		rbms.setDefaultEncoding("UTF-8");
		ArrayList<String> resourceList = new ArrayList<>();
		//加载默认通用的全局资源文件
		resourceList.add("i18n/messages/messages");
		//国家化文件，存放的扫描路径
		String basenames = environment.getProperty("messages.basenames");
		if (!StringUtil.isEmpty(basenames)){
			//扫描指定路径下的资源文件
			org.springframework.core.io.Resource[] resources = resolver.getResources(basenames + "*" + PROPERTIES_SUFFIX);
			for (org.springframework.core.io.Resource resource : resources) {
				String sourcePath = resource.getURI().toString();
				int startIndex = sourcePath.indexOf(CLASS);
				int endIndex = sourcePath.indexOf(PART_FILE_NAME);
				if (startIndex >= 0 && endIndex >= 0) {
					sourcePath = sourcePath.substring(startIndex + CLASS.length() + 1, endIndex + PART_FILE_NAME.length());
					resourceList.add(sourcePath);
				}
			}
		}
		else{
			logger.info("not found resource messagees config path[messages.basenames]");
		}
		rbms.setBasenames(resourceList.toArray(new String[resourceList.size()]));
		return rbms;
	}

	@Bean
	public Validator getValidator() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		ResourceBundleMessageSource resourceBundleMessageSource = this.getMessageSource();
		validator.setValidationMessageSource(resourceBundleMessageSource);
		return validator;
	}
}
