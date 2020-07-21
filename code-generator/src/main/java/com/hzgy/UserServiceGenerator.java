package com.hzgy;

import com.hzgy.generator.service.BaseGenerator;

import java.util.HashMap;
import java.util.Map;

public class UserServiceGenerator extends BaseGenerator {

	/**
	 * datahub服务-生成器主函数
	 * @param args main 参数
	 */

	public static void main(String[] args) {

		//**************************************************************************************
		//******************生成器配置信息，为生成服务配置下列信息**********************************
		//**************************************************************************************
		//实例生成服务类对象
		UserServiceGenerator userServiceGenerator = new UserServiceGenerator();
		//生成所有服务工程根目录，建议所有生成微服务组都放在此目录下
		String rootPath = "D:\\project\\java-workspace\\hzgy-project\\javacg";
		//数据库设计pmd文件存放目录，建议存放在上面rootPath目录下
		String databaseRootPath = rootPath + "\\design\\database";
		//生成器所在目录，建议生成器存放到rootPath目录下
		String generatorRootPath = rootPath + "\\code-generator";
		//公司项目目录名称,设置项目生成的目录名称，例如：com.xxx 或 cn.xxx
		String companyProjectName = "com.hzgy";
		//工程名称简写，生成工程目录名称简写，同时此名称也是java类路径，com.xxx.工程名称，如：com.xxx.user
		String ssn = "user";
		//说明：生成器针对后台管理系统和前端系统进行了生成服务拆分，方便服务间解耦，前后端服务互不调用
		//管理后台所有微服务名称,bms=background manage system
		String serviceBmsRootPath = rootPath + "\\example\\bms-" + ssn + "-service";
		//前端系统所有微服务名称,fes=front-end system
		String serviceFesRootPath = rootPath + "\\example\\fes-" + ssn + "-service";
		//通用base服务，系统微服务通用类封装和工具类库存放路径
		//生成器生成通用fegin-client代码也在此目录下名为：common-client
		String feignClientrootPath = rootPath + "\\example\\base-common";
		//数据库设计pmd文件名
		String databaseFile = "mysql-user-1.0.0.pdm";
		//数据库名称
		String databaseName = "hzgy_user_service";
		//项目相对路径，生成代码的主要存放路径
		String relativePath = companyProjectName + "." + ssn + ".modules";
		//依赖程序包路径,参考例子:com.hzgy.core,com.hzgy.interceptor,com.hzgy.db
		String dependPackage = companyProjectName + ".core," +
				companyProjectName + ".interceptor," +
				companyProjectName + ".db";
		//**************************************************************************************
		//******************设置信息到此结束，以下内容无需在配置************************************
		//**************************************************************************************




		//设置配置信息到Map对象中
		Map<String, String> configService = new HashMap<>();
		//数据库设计存放根目录
		configService.put("databaseRootPath",databaseRootPath);
		//生成器根目录
		configService.put("generatorRootPath",generatorRootPath);
		//后台服务工程根目录
		configService.put("serviceBmsRootPath",serviceBmsRootPath);
		//前台服务工程根目录
		configService.put("serviceFesRootPath",serviceFesRootPath);
		//通用Base根目录
		configService.put("feignClientrootPath",feignClientrootPath);
		//服务接口前缀
		configService.put("apiPerfix","api");
		//资源文件相对路径
		configService.put("resourcesPath","src/main/resources");
		//java类文件相对路径
		configService.put("javaPath","src/main/java");
		//服务名称简写
		configService.put("serviceSimpleName",ssn);
		//数据库名称
		configService.put("databaseName",databaseName);
		//pdm文件名称
		configService.put("pdmFileName",databaseFile);
		//项目相对路径
		configService.put("relativePath",relativePath);
		//项目依赖的基础程序包
		configService.put("dependPackage",dependPackage);
		//忽略生成的数据表
		configService.put("ignoreTables","");
		//**************************************************************************************
		//**************************************************************************************
		//创建数据库
		userServiceGenerator.databaseCreate(configService);
		//创建管理后台服务
		userServiceGenerator.serviceBmsCreate(configService);
		//创建前台服务
		userServiceGenerator.serviceFmsCreate(configService);
		//**************************************************************************************
		//生成web管理端客户端程序
		fiegnClientCreate(userServiceGenerator,configService,ssn);
	}

	/** 生成web管理端客户端程序 */
	private static void fiegnClientCreate(UserServiceGenerator userServiceGenerator, Map<String, String> configService, String ssn){
		//**************************************************************************************
		//服务名称
		configService.put("serviceName",ssn);
		//项目相对路径
		configService.put("relativePath","com/hzgy/client");
		//客户端工程名称
		configService.put("projectName","common-client");
		//依赖包
		configService.put("dependPackage","com.hzgy.core");
		//忽略的数据表
		configService.put("ignoreTables","");
		//**************************************************************************************
		//**************************************************************************************
		//生成fegin客户端程序
		userServiceGenerator.fiegnClientCreate(configService);
		//**************************************************************************************
		//**************************************************************************************
		//生成web管理端客户端程序
	}
}
