package com.hzgy.generator.dao;

import com.hzgy.generator.entity.PdmDatabase;
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

	private static String resource = "classpath:config/mybatis/mybatis-config.xml";
	private static String properties = "classpath:config/mybatis/jdbc.properties";
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
		}
		logger.info("开始加载  mybatis-config.xml 数据库配置文件.");
		try {
			Resource propertiesRes = resolver.getResource(properties);
			Properties properties = new Properties();
			properties.load(propertiesRes.getInputStream());
			String databases = properties.getProperty("multiple.databases");
			if (databases == null || databases.equals("")) {
				return ;
			}
			String databaseArr[] = databases.split(",");
			for (String databaseName : databaseArr) {
				if (databaseName == null || databaseName.equals("")) {
					continue;
				}
				String driverClassName = properties.getProperty(databaseName + ".driverClassName");
				String url = properties.getProperty(databaseName + ".url");
				String username = properties.getProperty(databaseName + ".username");
				String password = properties.getProperty(databaseName + ".password");
				String createUrl = MessageFormat.format(url, "");
				SqlSessionFactory sqlSessionFactory = createSqlSessionFactory(driverClassName,createUrl,username,password,databaseName);
				createDatabase(sqlSessionFactory,databaseName);
				String newUrl = MessageFormat.format(url, databaseName);
				saveOrCreateSqlSessionFactory(driverClassName,newUrl,username,password,databaseName);
			}
			logger.info("mybatis-config.xml 数据库配置文件加载成功.");
		} catch (IOException e) {
			logger.info("mybatis-config.xml 数据库配置文件加载失败.");
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static void init(String database) {
		if (sqlSessionFactorys == null) {
			sqlSessionFactorys = new HashMap<>();
		}
		logger.info("开始加载  mybatis-config.xml 数据库配置文件.");
		try {
			Resource propertiesRes = resolver.getResource(properties);
			Properties properties = new Properties();
			properties.load(propertiesRes.getInputStream());
			String databases = properties.getProperty("multiple.databases");
			if (databases == null || databases.equals("")) {
				return ;
			}
			String databaseArr[] = databases.split(",");
			for (String databaseName : databaseArr) {
				if (databaseName == null || databaseName.equals("")) {
					continue;
				}
				if  (database != null && database.contains(databaseName)) {
					String driverClassName = properties.getProperty(databaseName + ".driverClassName");
					String url = properties.getProperty(databaseName + ".url");
					String username = properties.getProperty(databaseName + ".username");
					String password = properties.getProperty(databaseName + ".password");
					String createUrl = MessageFormat.format(url, "");
					SqlSessionFactory sqlSessionFactory = createSqlSessionFactory(driverClassName,createUrl,username,password,databaseName);
					createDatabase(sqlSessionFactory,databaseName);
					String newUrl = MessageFormat.format(url, databaseName);
					saveOrCreateSqlSessionFactory(driverClassName,newUrl,username,password,databaseName);
					return ;
				}
			}
			logger.info("mybatis-config.xml 数据库配置文件加载成功.");
		} catch (IOException e) {
			logger.info("mybatis-config.xml 数据库配置文件加载失败.");
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static SqlSessionFactory getSqlSessionFactory(String databaseName) {
		return sqlSessionFactorys.get(databaseName);
	}

	/**
	 * 创建数据库
	 * @param sqlSessionFactory
	 * @param databaseName
	 */
	private static void createDatabase(SqlSessionFactory sqlSessionFactory,String databaseName){
		//查询数据库是否存在
		PdmDatabase pdmDatabase = new PdmDatabase();
		pdmDatabase.setName(databaseName);
		pdmDatabase = sqlSessionFactory.openSession().selectOne("select_database", pdmDatabase);
		Integer count = pdmDatabase.getCount();
		//如果不存在就创建数据库
		if (count == null || count.equals(0)) {
			pdmDatabase.setName(databaseName);
			sqlSessionFactory.openSession().update("create_database", pdmDatabase);
		}
	}


	/**
	 * 创建sqlSessionFactory
	 * @param driverClassName
	 * @param url
	 * @param username
	 * @param password
	 * @param databaseName
	 */
	private static SqlSessionFactory createSqlSessionFactory(String driverClassName,String url,String username,String password,String databaseName){
		Resource resources = resolver.getResource(resource);
		InputStream inputStream = null;
		try {
			inputStream = resources.getInputStream();
			Properties dbProperty = new Properties();
			dbProperty.put("db.driverClassName", driverClassName);
			dbProperty.put("db.url", url);
			dbProperty.put("db.username", username);
			dbProperty.put("db.password", password);
			return new SqlSessionFactoryBuilder().build(inputStream, dbProperty);
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return  null;
	}

	private static void saveOrCreateSqlSessionFactory(String driverClassName,String url,String username,String password,String databaseName){
		SqlSessionFactory sqlSessionFactory = createSqlSessionFactory(driverClassName,url,username,password,databaseName);
		sqlSessionFactorys.put(databaseName, sqlSessionFactory);
	}
}
