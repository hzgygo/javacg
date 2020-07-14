package com.hzgy.generator.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MySqlSessionFactory {

	private static String resource = "classpath:creator/mybatis/mybatis-config.xml";
	private static String properties = "classpath:creator/mybatis/jdbc.properties";
	private static ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	private static Map<String,SqlSessionFactory> sqlSessionFactorys = null;
	static Logger logger = Logger.getLogger(MySqlSessionFactory.class);
	private MySqlSessionFactory() {
	}

	/**
	 * mybatis sqlSession 工厂类单例模式
	 * @return
	 */
	public static void init() {
		if (sqlSessionFactorys == null) {
			sqlSessionFactorys = new HashMap<>();
			logger.info("开始加载  mybatis-config.xml 数据库配置文件.");
			try {
				Resource propertiesRes = resolver.getResource(properties);
				Properties properties = new Properties();
				properties.load(propertiesRes.getInputStream());
				String prefix = properties.getProperty("database.prefix");
				String databases = properties.getProperty("multiple.databases");
				if (databases != null) {
					String databaseArr[] = databases.split(",");
					for (String databaseName : databaseArr) {
						if (databaseName != null && !databaseName.equals("")) {
							Resource resources = resolver.getResource(resource);
							InputStream inputStream = resources.getInputStream();
							String driverClassName = properties.getProperty(databaseName + ".driverClassName");
							String url = properties.getProperty(databaseName + ".url");
							String username = properties.getProperty(databaseName + ".username");
							String password = properties.getProperty(databaseName + ".password");
							url = MessageFormat.format(url,prefix);
							Properties dbProperty = new Properties();
							dbProperty.put("db.driverClassName", driverClassName);
							dbProperty.put("db.url", url);
							dbProperty.put("db.username", username);
							dbProperty.put("db.password", password);
							SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, dbProperty);
							sqlSessionFactorys.put(prefix + "_" + databaseName, sqlSessionFactory);
						}
					}
				}
				logger.info("mybatis-config.xml 数据库配置文件加载成功.");
			} catch (IOException e) {
				logger.info("mybatis-config.xml 数据库配置文件加载失败.");
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	public static void init(String database) {
		if (sqlSessionFactorys == null) {
			sqlSessionFactorys = new HashMap<>();
			logger.info("开始加载  mybatis-config.xml 数据库配置文件.");
			try {
				Resource propertiesRes = resolver.getResource(properties);
				Properties properties = new Properties();
				properties.load(propertiesRes.getInputStream());
				String prefix = properties.getProperty("database.prefix");
				String databases = properties.getProperty("multiple.databases");
				if (databases != null) {
					String databaseArr[] = databases.split(",");
					for (String databaseName : databaseArr) {
						if (!databaseName.equals("")) {
							if  (database != null && database.contains(databaseName)) {
								Resource resources = resolver.getResource(resource);
								InputStream inputStream = resources.getInputStream();
								String driverClassName = properties.getProperty(databaseName + ".driverClassName");
								String url = properties.getProperty(databaseName + ".url");
								String username = properties.getProperty(databaseName + ".username");
								String password = properties.getProperty(databaseName + ".password");
								url = MessageFormat.format(url, prefix);
								Properties dbProperty = new Properties();
								dbProperty.put("db.driverClassName", driverClassName);
								dbProperty.put("db.url", url);
								dbProperty.put("db.username", username);
								dbProperty.put("db.password", password);
								SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, dbProperty);
								sqlSessionFactorys.put(prefix + "_" + databaseName, sqlSessionFactory);
								return;
							}
						}
					}
				}
				logger.info("mybatis-config.xml 数据库配置文件加载成功.");
			} catch (IOException e) {
				logger.info("mybatis-config.xml 数据库配置文件加载失败.");
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	public static SqlSessionFactory getSqlSessionFactory(String databaseName) {
		return sqlSessionFactorys.get(databaseName);
	}
}
