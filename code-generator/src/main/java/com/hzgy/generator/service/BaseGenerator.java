package com.hzgy.generator.service;

import com.hzgy.generator.entity.Path;
import com.hzgy.generator.entity.Pdm;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseGenerator {

	//数据库设计pdm对象
	private Pdm serviecPdm;

	/**
     * 生成数据库
	 * @param config
	 */
	public Pdm databaseCreate(Map<String,String> config) {
		try {
			System.out.println("config:" + config);
			String databaseName =  config.get("databaseName");
			String pdmFileName =  config.get("pdmFileName");
			String generatorRootPath =  config.get("generatorRootPath");
			String resourcesPath =  config.get("resourcesPath");
			String databaseRootPath =  config.get("databaseRootPath");
			// 通用业务员数据表结构设计(下面生成逻辑不允许修改，否则导致表验证错误)
			//生成器通用表数据库设计存放目录
			String generatorPdmPath = generatorRootPath
					+ File.separator + resourcesPath
					+ File.separator + "config"
					+ File.separator + "database";
			Pdm generatorPdm = new ParserPdmService().pdmParser(generatorPdmPath, "mysql-generator.pdm",false);
			GeneratorFactory.getCreateDatabaseInstance(databaseName).createDatebase(generatorRootPath,generatorPdm,false,false);
			//各个服务生成的配置
			String servicePdmPath = databaseRootPath;
//			String servicePdmPath = generatorRootPath
//					+ File.separator + resourcesPath
//					+ File.separator + "database";
			serviecPdm = new ParserPdmService().pdmParser(servicePdmPath, pdmFileName,false);
			GeneratorFactory.getCreateDatabaseInstance(databaseName).createDatebase(generatorRootPath,serviecPdm,true,true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return serviecPdm;
	}

	/**
	 * 创建服务
	 * @param config          配置对象
	 */
	public void  serviceBmsCreate(Map<String,String> config) {
		try {
			System.out.println("config:" + config);
			String serviceSimpleName =  config.get("serviceSimpleName");
			String relativePath =  config.get("relativePath");
			String dependPackage =  config.get("dependPackage");
			String serviceRootPath =  config.get("serviceBmsRootPath");
			String apiPerfix =  config.get("apiPerfix");
			String generatorRootPath =  config.get("generatorRootPath");
			String resourcesPath =  config.get("resourcesPath");
			String javaPath =  config.get("javaPath");
			String ignoreTables =  config.get("ignoreTables");
			// 忽略生成相关文件的实体表名称
			Map<String, String> ignoreTableMap = new HashMap<>();
			if (ignoreTables != null) {
				String itableArr[] = ignoreTables.split(",");
				for (String table : itableArr) {
					ignoreTableMap.put(table, table);
				}
			}
			// 生成service工程服务信息
			Path servicePath = new Path();
			servicePath.setServiceSimpleName(serviceSimpleName);
			servicePath.setApiPerfix(apiPerfix);
			servicePath.setGeneratorRootPath(generatorRootPath);
			servicePath.setServiceRootPath(serviceRootPath);
			//转成文件路径
			servicePath.setRelativePath(relativePath.replaceAll("\\.","/"));
			servicePath.setResourcesPath(resourcesPath);
			servicePath.setDependPackage(dependPackage);
			servicePath.setJavaPath(javaPath);
			GeneratorFactory.getCreaterClassInstance().createService(serviecPdm, servicePath, ignoreTableMap);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建服务
	 * @param config          配置对象
	 */
	public void  serviceFmsCreate(Map<String,String> config) {
		try {
			System.out.println("config:" + config);
			String serviceSimpleName =  config.get("serviceSimpleName");
			String relativePath =  config.get("relativePath");
			String dependPackage =  config.get("dependPackage");
			String serviceRootPath =  config.get("serviceFesRootPath");
			String apiPerfix =  config.get("apiPerfix");
			String generatorRootPath =  config.get("generatorRootPath");
			String resourcesPath =  config.get("resourcesPath");
			String javaPath =  config.get("javaPath");
			String ignoreTables =  config.get("ignoreTables");
			// 忽略生成相关文件的实体表名称
			Map<String, String> ignoreTableMap = new HashMap<>();
			if (ignoreTables != null) {
				String itableArr[] = ignoreTables.split(",");
				for (String table : itableArr) {
					ignoreTableMap.put(table, table);
				}
			}
			// 生成service工程服务信息
			Path servicePath = new Path();
			servicePath.setApiPerfix(apiPerfix);
			servicePath.setGeneratorRootPath(generatorRootPath);
			servicePath.setServiceSimpleName(serviceSimpleName);
			servicePath.setServiceRootPath(serviceRootPath);
			//转成文件路径
			servicePath.setRelativePath(relativePath.replaceAll("\\.","/"));
			servicePath.setRelativePath(relativePath);
			servicePath.setResourcesPath(resourcesPath);
			servicePath.setDependPackage(dependPackage);
			servicePath.setJavaPath(javaPath);
			GeneratorFactory.getCreaterClassInstance().createService(serviecPdm, servicePath, ignoreTableMap);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
     * 生成fegin客户端
	 * @param config
	 */
	public void fiegnClientCreate(Map<String,String> config){
		try {
			//生成feign端调用接口或实体bean时使用
			String serviceSimpleName =  config.get("serviceSimpleName");
			//生成feign 客户端时候使用
			String serviceName =  config.get("serviceName");
			String pdmFileName =  config.get("pdmFileName");
			String relativePath =  config.get("relativePath");
			String projectName =  config.get("projectName");
			String dependPackage =  config.get("dependPackage");
			String ignoreTables =  config.get("ignoreTables");
			String generatorRootPath = config.get("generatorRootPath");
			String resourcesPath = config.get("resourcesPath");
			String feignClientrootPath = config.get("feignClientrootPath");
			String javaPath = config.get("javaPath");
			// 通用业务员数据表结构设计(下面生成逻辑不允许修改，否则导致表验证错误)
			// 忽略生成相关文件的实体表名称
			Map<String, String> ignoreTableMap = new HashMap<String, String>();
			if (ignoreTables != null) {
				String itableArr[] = ignoreTables.split(",");
				for (String table : itableArr) {
					ignoreTableMap.put(table, table);
				}
			}
			// 生成service工程服务信息
			//服务工程目录
			String serviceProjectPath = feignClientrootPath + File.separator + projectName;
			Path servicePath = new Path();
			servicePath.setServiceName(serviceName);
			servicePath.setServiceSimpleName(serviceSimpleName);
			servicePath.setGeneratorRootPath(generatorRootPath);
			servicePath.setServiceRootPath(serviceProjectPath);
			servicePath.setRelativePath(relativePath);
			//转成文件路径
			servicePath.setRelativePath(relativePath.replaceAll("\\.","/"));
			servicePath.setResourcesPath(resourcesPath);
			servicePath.setDependPackage(dependPackage);
			servicePath.setJavaPath(javaPath);
			GeneratorFactory.getCreaterClassInstance().createFeignClientApi(serviecPdm, servicePath, ignoreTableMap);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
     * 生成web客户端
	 * @param config
	 */
	public void webClientCreate(Map<String,String> config){
		try {
			//生成web端调用接口或实体bean时使用
			String serviceSimpleName =  config.get("serviceSimpleName");
			//生成web客户端时候使用
			String serviceName =  config.get("serviceName");
			String pdmFileName =  config.get("pdmFileName");
			String relativePath =  config.get("relativePath");
			String projectName =  config.get("projectName");
			String dependPackage =  config.get("dependPackage");
			String ignoreTables =  config.get("ignoreTables");
			String generatorRootPath =  config.get("generatorRootPath");
			String resourcesPath =  config.get("resourcesPath");
			String webClientRootPath =  config.get("webClientRootPath");
			String apiPerfix =  config.get("apiPerfix");
			String javaPath =  config.get("javaPath");
			// 通用业务数据表结构设计(下面生成逻辑不允许修改，否则导致表验证错误)
			// 忽略生成相关文件的实体表名称
			Map<String, String> ignoreTableMap = new HashMap<String, String>();
			if (ignoreTables != null) {
				String itableArr[] = ignoreTables.split(",");
				for (String table : itableArr) {
					ignoreTableMap.put(table, table);
				}
			}
			// 生成web客户端调用信息
			//web工程目录
			String serviceProjectPath = webClientRootPath + File.separator + projectName;
			Path servicePath = new Path();
			servicePath.setApiPerfix(apiPerfix);
			servicePath.setServiceSimpleName(serviceSimpleName);
			servicePath.setServiceName(serviceName);
			servicePath.setGeneratorRootPath(generatorRootPath);
			servicePath.setServiceRootPath(serviceProjectPath);
			servicePath.setRelativePath(relativePath);
			servicePath.setResourcesPath(resourcesPath);
			servicePath.setDependPackage(dependPackage);
			servicePath.setJavaPath(javaPath);
			GeneratorFactory.getCreaterClassInstance().createWebClientAPI(serviecPdm, servicePath,false, ignoreTableMap);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
