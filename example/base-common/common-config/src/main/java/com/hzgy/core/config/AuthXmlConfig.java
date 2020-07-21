package com.hzgy.core.config;

import com.hzgy.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;

@Configuration
public class AuthXmlConfig {

	private static Logger logger = LoggerFactory.getLogger(AuthXmlConfig.class);

	public static final String DEFAULT_PATH = "/config/initialize";
	public static final String CLASS_PATH = "classpath:";

	@Resource
	public Environment environment;
	
	/**
	 * 功能配置文件数组
	 */
	private ArrayList<File> authXmlFile;

	@Value(value="${spring.profiles.active}")
	private String active;

	@PostConstruct
	public void getAuthXmls() throws Exception {
		String  rootPath = System.getProperty("user.dir");
		//开发环境按照类的根存放目录获取路径
		if (!StringUtil.isEmpty(active) && active.equals("dev")){
			File path = new File(ResourceUtils.getURL(CLASS_PATH).getPath());
			rootPath = path.getParentFile().getAbsolutePath();
		}
		String pluginXmlFilePath = rootPath + DEFAULT_PATH;
		logger.info("load config file form directory:" + pluginXmlFilePath);
		File xmlRootFile = new File(pluginXmlFilePath);
		if (xmlRootFile.exists()){
			authXmlFile = new ArrayList<>();
			File files [] = xmlRootFile.listFiles();
			if (files != null && files.length > 0) {
				for (File file:files){
					String fileName = file.getName();
					if (!file.isDirectory() && fileName.contains("-config.xml")){
						logger.info("found config file:" + file.getName());
						authXmlFile.add(file);
					}
				}
			}
			else{
				logger.info("not found config file in directory:" + pluginXmlFilePath);
			}
		}
	}

	public ArrayList<File> getAuthXmlFile() {
		return authXmlFile;
	}

	public void setAuthXmlFile(ArrayList<File> authXmlFile) {
		this.authXmlFile = authXmlFile;
	}
}
