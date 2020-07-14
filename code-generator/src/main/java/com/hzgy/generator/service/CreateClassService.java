package com.hzgy.generator.service;

import com.hzgy.generator.entity.Path;
import com.hzgy.generator.entity.Pdm;
import com.hzgy.generator.entity.PdmReference;
import com.hzgy.generator.entity.PdmTable;
import com.hzgy.generator.utils.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"ALL", "ResultOfMethodCallIgnored"})
public class CreateClassService extends BaseCreateService {

	private static Logger logger = Logger.getLogger(CreateClassService.class);

	/**
	 * 生成 service 相关类和资源文件
	 * @param pdm pdm解析对象  pdm解析对形象
	 * @param path 工程路径对象  工程路径对象
	 * @param ignoreTables 忽略生成的模型表
	 */
	public void createService(Pdm pdm,Path path,Map<String,String> ignoreTables) throws Exception {
		logger.info("开始进行系统开发文件生成...");
		if (pdm == null || path == null) {
			logger.info("Pdm或Path参数对象不能为空！");
			return ;
		}
		// 生成器根目录
		String creatorRootPath = path.getCreatorRootPath();
		// 服务根目录
		String serviceRootPath = path.getServiceRootPath();
		// 资源文件根目录
		String recsourcesPath = path.getResourcesPath();
		// java类根目录
		String javaPath = path.getJavaPath();
		// core目录
		String dependPackage = path.getDependPackage();
		//路径处理程序
		PathHandler pathHandler = new PathHandler();
		// 生成模板文件存放目录
		String creatorTemplatePath = null;
		// 目标项目类路径
		String targetProjectClassPath = null;
		// 目标项目资源文件路径
		String targetProjectResourcesPath = null;
		//工程相对目录
		String relativeProjectPath = path.getRelativePath();
		boolean booRel = relativeProjectPath.startsWith("/");
		if (booRel) {
			relativeProjectPath = relativeProjectPath.substring(1, relativeProjectPath.length());
		}
		//生成类，资源文件，mybatis 配置文件
		creatorTemplatePath = creatorRootPath + File.separator
				+ recsourcesPath + File.separator
				+ "creator" + File.separator
				+ "template" + File.separator
				+ "class";
		targetProjectClassPath = serviceRootPath + File.separator + javaPath;
		if (!relativeProjectPath.equals("")) {
			targetProjectClassPath += File.separator + relativeProjectPath;
		}
		targetProjectResourcesPath = serviceRootPath + File.separator + recsourcesPath;
		boolean isBms = false;
		if (serviceRootPath != null && serviceRootPath.contains("bms-")){
			isBms = true;
		}
		//设置核心包路径
		pathHandler.setDependPackage(dependPackage);
		//设置生成器模板路径
		pathHandler.setCreatorTemplatePath(creatorTemplatePath);
		//设置项目资源文件路径
		pathHandler.setTargetProjectResourcesPath(targetProjectResourcesPath);
		//设置项目类路径
		pathHandler.setTargetProjectClassPath(targetProjectClassPath);
		//设置项目相对路径
		pathHandler.setRelativeProjectPath(relativeProjectPath);
		//设置接口前缀
		pathHandler.setApiPerfix(path.getApiPerfix());
		//忽略的表过滤掉
		ArrayList<PdmTable> tables = pdm.getTables();
		ArrayList<PdmTable> genTables = new ArrayList<PdmTable>();
		if (ignoreTables != null && ignoreTables.size() > 0) {
    		for(PdmTable table:tables){
    			//判断表是否忽略生成
    			String originalCode = table.getOriginalCode() == null?"":table.getOriginalCode();
    			String ignoreTableCode = ignoreTables.get(originalCode);
    			if (ignoreTableCode != null) {
    				logger.info("数据表["+originalCode+"]不进行开发文件的生成.");
    				continue;
    			}
    			genTables.add(table);
    		}
    		// 生成主配置文件
    		this.createMybatisConfig(pathHandler,genTables);
		}
		else {
			// 生成主配置文件
			this.createMybatisConfig(pathHandler,tables);
		}
		//转换成类型路径
		relativeProjectPath = relativeProjectPath.replaceAll("/", ".");
		//设置项目相对路径，间路径中的斜杠（/）转换成类包路径（.）
		pathHandler.setRelativeProjectPath(relativeProjectPath);
		// 表关系
		ArrayList<PdmReference> references = pdm.getReferences();
		// 生成表相关的配置文件
		int index = 0;
		for(PdmTable table:tables){
			//freemarker模型
			Map<String, Object> model = new HashMap<String, Object>();
			//判断表是否忽略生成
			String originalCode = table.getOriginalCode() == null?"":table.getOriginalCode();
			if (ignoreTables == null){
				continue;
			}
			String ignoreTableCode = ignoreTables.get(originalCode);
			if (ignoreTableCode != null) {
				continue;
			}
			if (table == null || table.getCode() == null){
				continue;
			}
			index += 1;
			//表名称去掉下滑线后的名称
			String tableCode = table.getCode();
			// 生成基类实体bean
			pathHandler.setClassServiceType("service-base-po-entity");
			pathHandler.setForce(true);
			pathHandler.setTemplateName("base-po-entity.ftl");
			String fileName = BASE_CLASS_PREFIX + tableCode + PO_CLASS_PREFIX + JAVA;
			pathHandler.setFileName(fileName);
			String fileRelativePath = tableCode.toLowerCase() + File.separator + BASE;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);
			// 生成基类like实体bean
			pathHandler.setClassServiceType("service-base-criteria-po-entity");
			pathHandler.setForce(true);
			pathHandler.setTemplateName("base-criteria-po-entity.ftl");
			fileName = BASE_CLASS_PREFIX + tableCode + "Criteria" + PO_CLASS_PREFIX + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath = tableCode.toLowerCase() + File.separator + BASE;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);

			// 生成基类实体bean
			pathHandler.setClassServiceType("service-base-input-entity");
			pathHandler.setForce(true);
			pathHandler.setTemplateName("base-vo-input-entity.ftl");
			fileName = BASE_CLASS_PREFIX + tableCode + VO_INPUT_CLASS_PREFIX + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath = tableCode.toLowerCase() + File.separator + BASE;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);
			// 生成基类实体likebean
			pathHandler.setClassServiceType("service-base-criteria-input-entity");
			pathHandler.setForce(true);
			pathHandler.setTemplateName("base-vo-criteria-input-entity.ftl");
			fileName = BASE_CLASS_PREFIX + tableCode  + "Criteria" + VO_INPUT_CLASS_PREFIX + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath = tableCode.toLowerCase() + File.separator + BASE;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);

			// 生成基类实体bean
			pathHandler.setClassServiceType("service-base-output-entity");
			pathHandler.setForce(true);
			pathHandler.setTemplateName("base-vo-output-entity.ftl");
			fileName = BASE_CLASS_PREFIX + tableCode + VO_OUTPUT_CLASS_PREFIX + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath = tableCode.toLowerCase() + File.separator + BASE;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);

			// 生成基类实体子类bean
			pathHandler.setClassServiceType("service-sub-po-entity");
			pathHandler.setForce(false);
			pathHandler.setTemplateName("sub-po-entity.ftl");
			fileName = tableCode + PO_CLASS_PREFIX + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath = tableCode.toLowerCase() + File.separator + ENTITY;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);



//			// 生成基类实体bean
//			pathHandler.setClassServiceType("service-base-dto-entity");
//			pathHandler.setForce(true);
//			pathHandler.setTemplateName("base-dto-entity.ftl");
//			fileName = BASE_CLASS_PREFIX + tableCode + DTO_CLASS_PREFIX + JAVA;
//			pathHandler.setFileName(fileName);
//			fileRelativePath = tableCode.toLowerCase() + File.separator + BASE;
//			pathHandler.setFileRelativePath(fileRelativePath);
//			this.createClass(pathHandler,null,table,index,references);
//
//			// 生成实体 sub bean
//			pathHandler.setClassServiceType("service-sub-dto-entity");
//			pathHandler.setForce(false);
//			pathHandler.setTemplateName("sub-dto-entity.ftl");
//			fileName = tableCode + DTO_CLASS_PREFIX + JAVA;
//			pathHandler.setFileName(fileName);
//			fileRelativePath = tableCode.toLowerCase() + File.separator + ENTITY;
//			pathHandler.setFileRelativePath(fileRelativePath);
//			this.createClass(pathHandler,null,table,index,references);

			// 生成数据库接口 base dao
			pathHandler.setClassServiceType("service-base-interface-dao");
			pathHandler.setForce(true);
			pathHandler.setTemplateName("base-interface-dao.ftl");
			fileName = "I" + BASE_CLASS_PREFIX + tableCode + DAO_CLASS_PREFIX + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath = tableCode.toLowerCase() + File.separator + BASE;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);

			// 生成数据库接口 sub dao
			pathHandler.setClassServiceType("service-sub-interface-dao");
			pathHandler.setForce(false);
			pathHandler.setTemplateName("sub-interface-dao.ftl");
			fileName = "I" + tableCode + DAO_CLASS_PREFIX + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath = tableCode.toLowerCase();
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);

			// 生成base mybatis mapper文件
			pathHandler.setClassServiceType("service-base-sql-mapper");
			pathHandler.setForce(true);
			pathHandler.setTemplateName("base-sql-mapper.ftl");
			fileName = BASE + "-" + tableCode.toLowerCase() + "-sql-mapper.xml";
			pathHandler.setFileName(fileName);
			fileRelativePath = tableCode.toLowerCase() + File.separator + BASE;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,tables,table,index,references);

			// 生成实体 sub mybatis
			pathHandler.setClassServiceType("service-sub-sql-mapper");
			pathHandler.setForce(false);
			pathHandler.setTemplateName("sub-sql-mapper.ftl");
			fileName =  "custom-" + tableCode.toLowerCase() + "-sql-mapper.xml";
			pathHandler.setFileName(fileName);
			fileRelativePath = tableCode.toLowerCase();
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);

			// 生成base service
			pathHandler.setClassServiceType("service-base-interface-service");
			pathHandler.setForce(true);
			pathHandler.setTemplateName("base-interface-service.ftl");
			fileName = "I" + BASE_CLASS_PREFIX + tableCode + SERVICE_CLASS_PREFIX + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath = tableCode.toLowerCase() + File.separator + BASE;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);

			// 生成sub interface service
			pathHandler.setClassServiceType("service-sub-interface-service");
			pathHandler.setForce(false);
			pathHandler.setTemplateName("sub-interface-service.ftl");
			fileName = "I" +  tableCode + SERVICE_CLASS_PREFIX + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath = tableCode.toLowerCase();
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);

			// 生成实体base service
			pathHandler.setClassServiceType("service-base-class-service");
			pathHandler.setForce(true);
			pathHandler.setTemplateName("base-service.ftl");
			fileName = BASE_CLASS_PREFIX + tableCode + SERVICE_CLASS_PREFIX + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath = tableCode.toLowerCase() + File.separator + BASE;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);

			// 生成 sub service
			pathHandler.setClassServiceType("service-sub-class-service");
			pathHandler.setForce(false);
			pathHandler.setTemplateName("sub-service.ftl");
			fileName = tableCode + SERVICE_CLASS_PREFIX + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath = tableCode.toLowerCase();
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);

			// 生成message文件
			pathHandler.setClassServiceType("service-message");
			pathHandler.setForce(false);
			pathHandler.setTemplateName("service-messages.ftl");
			fileName = StringUtil.toLowerCaseFirstOne(tableCode) + MESSAGE_LOCAL + PROPERTIES;
			pathHandler.setFileName(fileName);
			fileRelativePath = tableCode.toLowerCase() + File.separator + PROPERTIES_DIR;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);

			//生成sub-input-entity
			pathHandler.setClassServiceType("sub-input-entity");
			pathHandler.setForce(false);
			pathHandler.setTemplateName("sub-input-entity.ftl");
			fileName = tableCode + VO_INPUT_CLASS_PREFIX + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath = tableCode.toLowerCase() + File.separator + ENTITY;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler, null, table, index, references);
			//生成多种请求参数实体
			Map<String,String> map = new HashMap<String,String>(){
				private static final long serialVersionUID = 5148661569288364271L;
				{
					put("Create","创建数据");
					put("Modify","修改数据");
					put("Delete","删除数据");
					put("One","单条数据");
					put("List","列表数据");
					put("Paginate","分页列表数据");
				}
			};
			for(Map.Entry<String,String> entry: map.entrySet()){
				String key = entry.getKey();
				String value = entry.getValue();
				model.put("methodName",key);
				model.put("methodDesc",value);
				pathHandler.setClassServiceType("sub-input-entity");
				pathHandler.setForce(false);
				pathHandler.setTemplateName("sub-input-entity.ftl");
				fileName = tableCode + key + VO_INPUT_CLASS_PREFIX + JAVA;
				pathHandler.setFileName(fileName);
				fileRelativePath = tableCode.toLowerCase() + File.separator + ENTITY;
				pathHandler.setFileRelativePath(fileRelativePath);
				this.createClass(model,pathHandler, null, table, index, references);
			}

			//生成restful vo ouput
			pathHandler.setClassServiceType("ful-vo-ouput");
			pathHandler.setForce(false);
			pathHandler.setTemplateName("sub-output-entity.ftl");
			fileName = tableCode + VO_OUTPUT_CLASS_PREFIX + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath = tableCode.toLowerCase() + File.separator + ENTITY;;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);

			// 生成restful api controller
			pathHandler.setClassServiceType("restful-base-controller");
			pathHandler.setForce(true);
			pathHandler.setTemplateName("base-restful-controller.ftl");
			fileName = BASE_CLASS_PREFIX + tableCode + CONTROLLER_CLASS_PREFIX + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath = tableCode.toLowerCase() + File.separator + BASE;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);

			// 生成restful api controller
			pathHandler.setClassServiceType("restful-sub-controller");
			if (isBms) {
				pathHandler.setTemplateName("sub-restful-controller.ftl");
			}
			else{
				pathHandler.setTemplateName("sub-restful-controller-null.ftl");
			}
			pathHandler.setForce(false);
			fileName = tableCode + CONTROLLER_CLASS_PREFIX + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath = tableCode.toLowerCase();
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);
		}
		logger.info("系统开发文件生成完成.");
	}
	
	
	/**
	 * 创建feign base client api
	 * @param pdm pdm解析对象  pdm解析对象
	 * @param path 工程路径对象  工程路径对象
	 * @param ignoreTables 忽略生成的模型表
	 */
	public void createBaseFeignClientApi(Pdm pdm,Path path,Map<String,String> ignoreTables) throws Exception {
		logger.info("开始进行系统开发文件生成...");
		if (pdm == null || path == null) {
			logger.info("Pdm或Path参数对象不能为空！");
			return ;
		}
		// 生成器根目录
		String creatorRootPath = path.getCreatorRootPath();
		// 服务根目录
		String serviceRootPath = path.getServiceRootPath();
		// 资源文件根目录
		String recsourcesPath = path.getResourcesPath();
		// java类根目录
		String javaPath = path.getJavaPath();
		// core目录
		String dependPackage = path.getDependPackage();
		//微服务名称简写
		String serviceSimpleName = path.getServiceSimpleName();
		//路径处理程序
		PathHandler pathHandler = new PathHandler();
		// 生成模板文件存放目录
		String creatorTemplatePath = null;
		// 目标项目类路径
		String targetProjectClassPath = null;
		// 目标项目资源文件路径
		String targetProjectResourcesPath = null;
		//工程相对目录
		String relativeProjectPath = path.getRelativePath();
		boolean booRel = relativeProjectPath.startsWith("/");
		if (booRel) {
			relativeProjectPath = relativeProjectPath.substring(1, relativeProjectPath.length());
		}
		//生成类，资源文件，mybatis 配置文件
		creatorTemplatePath = creatorRootPath + File.separator
				+ recsourcesPath + File.separator
				+ "creator" + File.separator
				+ "template" + File.separator
				+ "class";
		targetProjectClassPath = serviceRootPath + File.separator + javaPath;
		if (!relativeProjectPath.equals("")) {
			targetProjectClassPath += File.separator + relativeProjectPath;
		}
		targetProjectResourcesPath = serviceRootPath + File.separator + recsourcesPath;
		//设置核心包路径
		pathHandler.setDependPackage(dependPackage);
		//设置生成器模板路径
		pathHandler.setCreatorTemplatePath(creatorTemplatePath);
		//设置项目资源文件路径
		pathHandler.setTargetProjectResourcesPath(targetProjectResourcesPath);
		//设置项目类路径
		pathHandler.setTargetProjectClassPath(targetProjectClassPath);
		//设置项目相对路径
		pathHandler.setRelativeProjectPath(relativeProjectPath);
		//设置工程简称
		pathHandler.setServiceSimpleName(serviceSimpleName);
		//忽略的表过滤掉
		ArrayList<PdmTable> tables = pdm.getTables();
		//转换成类型路径
		relativeProjectPath = relativeProjectPath.replaceAll("/", ".");
		//设置项目相对路径，间路径中的斜杠（/）转换成类包路径（.）
		pathHandler.setRelativeProjectPath(relativeProjectPath);
		// 表关系
		ArrayList<PdmReference> references = pdm.getReferences();
		int index = 0;
		// 生成表相关的配置文件
		for(PdmTable table:tables){
			//freemarker模型
			Map<String, Object> model = new HashMap<String, Object>();
			//判断表是否忽略生成
			String originalCode = table.getOriginalCode() == null?"":table.getOriginalCode();
			if (ignoreTables == null){
				continue;
			}
			String ignoreTableCode = ignoreTables.get(originalCode);
			if (ignoreTableCode != null) {
				continue;
			}
			if (table == null || table.getCode() == null){
				continue;
			}
			//表名称去掉下滑线后的名称
			String tableCode = table.getCode();

			// 生成基类实体bean
			pathHandler.setClassServiceType("base-feign-po-input");
			pathHandler.setForce(true);
			pathHandler.setTemplateName("base-feign-vo-input.ftl");
			String fileName = BASE_CLASS_PREFIX + tableCode + VO_INPUT_CLASS_PREFIX + JAVA;
			pathHandler.setFileName(fileName);
			String fileRelativePath =  serviceSimpleName + File.separator + VO;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);

			// 生成feign base interface
			pathHandler.setClassServiceType("base-feign-interface");
			pathHandler.setForce(true);
			pathHandler.setTemplateName("base-feign-interface.ftl");
			fileName =  "I" + BASE_CLASS_PREFIX + tableCode + CLIENT_CLASS_PREFIX + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath = serviceSimpleName + File.separator + SERVICE;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);
		}
		logger.info("系统开发文件生成完成.");
	}

	/**
	 * 创建feign client api
	 * @param pdm pdm解析对象  pdm解析对象
	 * @param path 工程路径对象  工程路径对象
	 * @param ignoreTables 忽略生成的模型表
	 */
	public void createFeignClientApi(Pdm pdm,Path path,Map<String,String> ignoreTables) throws Exception {
		logger.info("开始进行系统开发文件生成...");
		if (pdm == null || path == null) {
			logger.info("Pdm或Path参数对象不能为空！");
			return ;
		}
		// 生成器根目录
		String creatorRootPath = path.getCreatorRootPath();
		// 服务根目录
		String serviceRootPath = path.getServiceRootPath();
		// 资源文件根目录
		String recsourcesPath = path.getResourcesPath();
		// java类根目录
		String javaPath = path.getJavaPath();
		// core目录
		String dependPackage = path.getDependPackage();
		//微服务名称
		String serviceName = path.getServiceName();
		//微服务名称简写
		String serviceSimpleName = path.getServiceSimpleName();
		//路径处理程序
		PathHandler pathHandler = new PathHandler();
		// 生成模板文件存放目录
		String creatorTemplatePath = null;
		// 目标项目类路径
		String targetProjectClassPath = null;
		// 目标项目资源文件路径
		String targetProjectResourcesPath = null;
		//工程相对目录
		String relativeProjectPath = path.getRelativePath();
		boolean booRel = relativeProjectPath.startsWith("/");
		if (booRel) {
			relativeProjectPath = relativeProjectPath.substring(1, relativeProjectPath.length());
		}
		//生成类，资源文件，mybatis 配置文件
		creatorTemplatePath = creatorRootPath + File.separator
				+ recsourcesPath + File.separator
				+ "creator" + File.separator
				+ "template" + File.separator
				+ "class";
		targetProjectClassPath = serviceRootPath + File.separator + javaPath;
		if (!relativeProjectPath.equals("")) {
			targetProjectClassPath += File.separator + relativeProjectPath;
		}
		targetProjectResourcesPath = serviceRootPath + File.separator + recsourcesPath;
		//设置微服务名称
		pathHandler.setServiceName(serviceName);
		//设置核心包路径
		pathHandler.setDependPackage(dependPackage);
		//设置生成器模板路径
		pathHandler.setCreatorTemplatePath(creatorTemplatePath);
		//设置项目资源文件路径
		pathHandler.setTargetProjectResourcesPath(targetProjectResourcesPath);
		//设置项目类路径
		pathHandler.setTargetProjectClassPath(targetProjectClassPath);
		//设置项目相对路径
		pathHandler.setRelativeProjectPath(relativeProjectPath);
		//设置工程简称
		pathHandler.setServiceSimpleName(serviceSimpleName);
		//忽略的表过滤掉
		ArrayList<PdmTable> tables = pdm.getTables();
		//转换成类型路径
		relativeProjectPath = relativeProjectPath.replaceAll("/", ".");
		//设置项目相对路径，间路径中的斜杠（/）转换成类包路径（.）
		pathHandler.setRelativeProjectPath(relativeProjectPath);
		// 表关系
		ArrayList<PdmReference> references = pdm.getReferences();
		int index = 0;
		// 生成表相关的配置文件
		for(PdmTable table:tables){
			//freemarker模型
			Map<String, Object> model = new HashMap<String, Object>();
			//判断表是否忽略生成
			String originalCode = table.getOriginalCode() == null?"":table.getOriginalCode();
			if (ignoreTables == null){
				continue;
			}
			String ignoreTableCode = ignoreTables.get(originalCode);
			if (ignoreTableCode != null) {
				continue;
			}
			if (table == null || table.getCode() == null){
				continue;
			}
			//表名称去掉下滑线后的名称
			String tableCode = table.getCode();
			// 生成基类实体bean
			pathHandler.setClassServiceType("base-feign-vo");
			pathHandler.setForce(true);
			pathHandler.setTemplateName("base-feign-vo.ftl");
			String fileName = BASE_CLASS_PREFIX + tableCode + JAVA;
			pathHandler.setFileName(fileName);
			String fileRelativePath =  serviceSimpleName + File.separator + VO + File.separator + BASE;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);

			pathHandler.setClassServiceType("base-feign-criteria-vo");
			pathHandler.setForce(true);
			pathHandler.setTemplateName("base-feign-criteria-vo.ftl");
			fileName = BASE_CLASS_PREFIX + tableCode + "Criteria" + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath =  serviceSimpleName + File.separator + VO + File.separator + BASE;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);


			// 生成feign base interface
			pathHandler.setClassServiceType("base-feign-interface");
			pathHandler.setForce(true);
			pathHandler.setTemplateName("base-feign-interface.ftl");
			fileName =  "I" + BASE_CLASS_PREFIX + tableCode + CLIENT_CLASS_PREFIX + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath = serviceSimpleName + File.separator + SERVICE + File.separator + BASE;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);

			// 生成基类实体bean
			pathHandler.setClassServiceType("sub-feign-vo-input");
			pathHandler.setForce(false);
			pathHandler.setTemplateName("sub-feign-vo-input.ftl");
			fileName = tableCode + VO_INPUT_CLASS_PREFIX + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath = serviceSimpleName + File.separator + VO;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);

			pathHandler.setClassServiceType("sub-feign-vo-output");
			pathHandler.setForce(false);
			pathHandler.setTemplateName("sub-feign-vo-output.ftl");
			fileName = tableCode + VO_OUTPUT_CLASS_PREFIX + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath = serviceSimpleName + File.separator + VO;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);

			// 生成feign base interface
			pathHandler.setClassServiceType("sub-feign-interface");
			pathHandler.setForce(false);
			pathHandler.setTemplateName("sub-feign-interface.ftl");
			fileName =  "I" + tableCode + "Client" + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath = serviceSimpleName + File.separator + SERVICE;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);
		}
		logger.info("系统开发文件生成完成.");
	}

	/**
	 * 生成web端调用类和资源文件
	 * @param pdm pdm解析对象  pdm解析对形象
	 * @param path 工程路径对象  工程路径对象
	 * @param ignoreTables 忽略生成的模型表
	 */
	public void createWebClientAPI(Pdm pdm,Path path,Boolean isFWebClient,Map<String,String> ignoreTables) throws Exception {
		logger.info("开始进行系统开发文件生成...");
		if (pdm == null || path == null) {
			logger.info("Pdm或Path参数对象不能为空！");
			return ;
		}
		// 生成器根目录
		String creatorRootPath = path.getCreatorRootPath();
		// 服务根目录
		String serviceRootPath = path.getServiceRootPath();
		// 资源文件根目录
		String recsourcesPath = path.getResourcesPath();
		//微服务名称简写
		String serviceSimpleName = path.getServiceSimpleName();
		//微服务名称
		String serviceName = path.getServiceName();
		// java类根目录
		String javaPath = path.getJavaPath();
		// core目录
		String dependPackage = path.getDependPackage();
		//路径处理程序
		PathHandler pathHandler = new PathHandler();
		// 生成模板文件存放目录
		String creatorTemplatePath = null;
		// 目标项目类路径
		String targetProjectClassPath = null;
		// 目标项目资源文件路径
		String targetProjectResourcesPath = null;
		//工程相对目录
		String relativeProjectPath = path.getRelativePath();
		boolean booRel = relativeProjectPath.startsWith("/");
		if (booRel) {
			relativeProjectPath = relativeProjectPath.substring(1, relativeProjectPath.length());
		}
		relativeProjectPath +=  "/" + serviceSimpleName;
		//生成类，资源文件，mybatis 配置文件
		creatorTemplatePath = creatorRootPath + File.separator
				+ recsourcesPath + File.separator
				+ "creator" + File.separator
				+ "template" + File.separator
				+ "class";
		targetProjectClassPath = serviceRootPath + File.separator + javaPath;
		if (!relativeProjectPath.equals("")) {
			targetProjectClassPath += File.separator + relativeProjectPath;
		}
		targetProjectResourcesPath = serviceRootPath + File.separator + recsourcesPath;
		//设置服务名称
		pathHandler.setServiceName(serviceName);
		//设置服务简称
		pathHandler.setServiceSimpleName(serviceSimpleName);
		//设置核心包路径
		pathHandler.setDependPackage(dependPackage);
		//设置生成器模板路径
		pathHandler.setCreatorTemplatePath(creatorTemplatePath);
		//设置项目资源文件路径
		pathHandler.setTargetProjectResourcesPath(targetProjectResourcesPath);
		//设置项目类路径
		pathHandler.setTargetProjectClassPath(targetProjectClassPath);
		//设置项目相对路径
		pathHandler.setRelativeProjectPath(relativeProjectPath);
		//设置接口前缀
		pathHandler.setApiPerfix(path.getApiPerfix());
		//忽略的表过滤掉
		ArrayList<PdmTable> tables = pdm.getTables();
		ArrayList<PdmTable> genTables = new ArrayList<PdmTable>();
		//转换成类型路径
		relativeProjectPath = relativeProjectPath.replaceAll("/", ".");
		//设置项目相对路径，间路径中的斜杠（/）转换成类包路径（.）
		pathHandler.setRelativeProjectPath(relativeProjectPath);
		// 表关系
		ArrayList<PdmReference> references = pdm.getReferences();
		// 生成表相关的配置文件
		int index = 0;
		for(PdmTable table:tables){
			//freemarker模型
			Map<String, Object> model = new HashMap<String, Object>();
			//判断表是否忽略生成
			String originalCode = table.getOriginalCode() == null?"":table.getOriginalCode();
			if (ignoreTables == null){
				continue;
			}
			String ignoreTableCode = ignoreTables.get(originalCode);
			if (ignoreTableCode != null) {
				continue;
			}
			if (table == null || table.getCode() == null){
				continue;
			}
			index += 1;
			//表名称去掉下滑线后的名称
			String tableCode = table.getCode();
			// 生成message文件
			pathHandler.setClassServiceType("web-message");
			pathHandler.setForce(false);
			pathHandler.setTemplateName("web-messages.ftl");
			String fileName = StringUtil.toLowerCaseFirstOne(tableCode) + MESSAGE_LOCAL + PROPERTIES;
			pathHandler.setFileName(fileName);
			String fileRelativePath =  tableCode.toLowerCase();
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);

			//生成restful base vo input
			pathHandler.setClassServiceType("web-base-restful-criteria-form");
			pathHandler.setForce(true);
			pathHandler.setTemplateName("web-base-restful-criteria-form.ftl");
			fileName = BASE_CLASS_PREFIX + tableCode + "Criteria" + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath =  tableCode.toLowerCase() + File.separator + BASE;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);

			pathHandler.setClassServiceType("web-base-restful-form");
			pathHandler.setForce(true);
			pathHandler.setTemplateName("web-base-restful-form.ftl");
			fileName = BASE_CLASS_PREFIX + tableCode + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath =  tableCode.toLowerCase() + File.separator + BASE;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);

			// 生成feign base interface
			pathHandler.setClassServiceType("web-base-feign-interface");
			pathHandler.setForce(true);
			pathHandler.setTemplateName("web-base-feign-interface.ftl");
			fileName = "I" + BASE_CLASS_PREFIX + tableCode + CLIENT_CLASS_PREFIX + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath = tableCode.toLowerCase() + File.separator + BASE;
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler, null, table, index, references);

			//生成restful vo 表单
			pathHandler.setClassServiceType("web-sub-restful-vo-form");
			pathHandler.setForce(false);
			pathHandler.setTemplateName("web-sub-restful-vo-form.ftl");
			fileName = tableCode + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath =  tableCode.toLowerCase();
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler,null,table,index,references);
			// 生成feign sub interface
			pathHandler.setClassServiceType("web-sub-feign-interface");
			pathHandler.setForce(false);
			pathHandler.setTemplateName("web-sub-feign-interface.ftl");
			fileName = "I" + tableCode + CLIENT_CLASS_PREFIX + JAVA;
			pathHandler.setFileName(fileName);
			fileRelativePath = tableCode.toLowerCase();
			pathHandler.setFileRelativePath(fileRelativePath);
			this.createClass(model,pathHandler, null, table, index, references);
		}
		logger.info("系统开发文件生成完成.");
	}

	/**
	 *
	 * 生成类文件通用方法
	 * @param pathHandler 路径处理器对象
	 * @param tables pdm 表解析对象
	 * @param table pdm 表解析对象
	 * @param references pdm关联表对象
	 */
	private void createClass(Map<String, Object> model,
							 PathHandler pathHandler,
							 ArrayList<PdmTable> tables,
							 PdmTable table,
							 Integer index,
							 ArrayList<PdmReference> references) throws Exception {
		if (table == null) {
			logger.info("PDM实体模型解析对象[PdmTable]不能为空.");
			throw new Exception("PDM实体模型对象[PdmTable]不能为空");
		}
		// 表名称(名称结构必须为xx_xxxxxx)
		String tableCode = table.getCode();
		if (tableCode == null) {
			logger.info("PDM实体模型解析对象[PdmTable]代码[tableCode]不能为空.");
			throw new Exception("PDM实体模型对象[PdmTable]代码[tableCode]不能为空");
		}
		//生成类业务类型
		String classServiceType = pathHandler.getClassServiceType();
		//生成器模板路径
		String creatorTemplatePath = pathHandler.getCreatorTemplatePath();
		//类文件存放根目录
		String targetProjectClassPath = pathHandler.getTargetProjectClassPath();
		//工程相对路径
		String relativeProjectPath = pathHandler.getRelativeProjectPath();
		//依赖包路径
		String dependPackage = pathHandler.getDependPackage();
		//模板名称
		String templateName = pathHandler.getTemplateName();
		//服务名称简写
		String serviceSimpleName = pathHandler.getServiceSimpleName();
		//服务名称
		String serviceName = pathHandler.getServiceName();
		//生成文件的相对路径
		String fileRelativePath = pathHandler.getFileRelativePath();
		//生成文件名
		String fileName = pathHandler.getFileName();
		//接口前缀
		String apiPerfix = pathHandler.getApiPerfix() == null?"api":pathHandler.getApiPerfix();
		logger.info("开始进行" + tableCode + "数据库表的" + classServiceType + "业务文件生成.");
		Writer out = null;
		try {
			// freemarker 配置对象
			Configuration config = new Configuration();
			// 模板文件目录
			File file = new File(creatorTemplatePath);
			// 加载模板文件
			config.setDirectoryForTemplateLoading(file);
			// 设置包装对象
			config.setObjectWrapper(new DefaultObjectWrapper());
			// 加载模板文件
			Template template = config.getTemplate(templateName, ENCODING);
			//生成文件绝对目录
			String filePath = targetProjectClassPath + File.separator + fileRelativePath;
			File creatorFilePath = new File(filePath);
			//目录不存在生成目录
			if (!creatorFilePath.exists()) {
				// 生成目录
				boolean boo = creatorFilePath.mkdirs();
				if (boo) {
					logger.info("成功创建生成文件存放目录：" + filePath);
				}
			}
			//生成文件绝对路径
			String absolutefileName = filePath + File.separator + fileName;
			Boolean force = pathHandler.getForce();
			File creatorFile = new File(absolutefileName);
			//判断文件是否要每次强制生成
			if (creatorFile.exists()) {
				if (force) {
					boolean boo = creatorFile.delete();
					if (boo) {
						logger.info("名称为：" + fileName + " 的文件已经成功删除，进行重新生成.");
					}
				} else {
					logger.info("名称为：" + fileName + " 的文件已经存在，此文件不进行二次生成.");
					return;
				}
			}
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(absolutefileName), "UTF-8"));
			try {
				//文件相对路径（类路径）
				model.put("relativeProjectPath", relativeProjectPath);
				//core包路径
				if (dependPackage != null) {
					String dependPackageArr[] = dependPackage.split(",");
					for (int i = 0; i < dependPackageArr.length; i++) {
						String dep = dependPackageArr[i];
						if (dep == null || dep.equals("")) {
							continue;
						}
						String key = dep.replaceAll("\\.","_");
						model.put(key, dep);
					}
				}
				// 设置table对象
				if (tables != null) {
					model.put("tables", tables);
				}
				//设置接口前缀到上文
				model.put("apiPerfix", apiPerfix);
				// 设置table对象
				model.put("table", table);
				// 设置索引
				model.put("tindex", index);
				// 表关系
				model.put("references", references);
				// 实体名称
				model.put("entity", tableCode);
				// 设置列对象
				model.put("columns", table.getColumns());
				// 设置类实体对象全小写作为类目录
				model.put("entityPath", tableCode.toLowerCase());
				// 设置业务目录名称
				model.put("module",MODULES);
				// 设置业务Base常量名
				model.put("base",BASE);
				//服务名称简写
				model.put("servieSimpleName",serviceSimpleName == null?"":serviceSimpleName);
				//服务名称简写
				model.put("servieName",serviceName == null?"":serviceName);
				// 生成文件
				template.process(model, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			logger.info("数据库表[" + index + "]:" + tableCode + "的" + classServiceType + "业务文件[" + fileName + "]生成成功");
			logger.info("生成存放路径:" + absolutefileName);

		} catch (IOException e1) {
			e1.printStackTrace();
			logger.info("数据库表:" + tableCode + "的" + classServiceType + "业务文件[" + fileName + "]生成失败");
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 生成mybatis 基础配置文件
	 * @param pathHandler 工程路径对象 Handler
	 * @param table 表解析对象s
	 */
	private void createMybatisConfig(PathHandler pathHandler,
			ArrayList<PdmTable> tables){
		if(tables != null && tables.size() > 0){
			logger.info("开始进行【mybatis-config.xml】配置文件生成...");
			String daoTemplatePath = pathHandler.getCreatorTemplatePath();
			String targetProjectResourcesPath = pathHandler.getTargetProjectResourcesPath();
			String relativeProjectPath = pathHandler.getRelativeProjectPath();
			try{
				// freemarker 配置对象
				Configuration config = new Configuration();
				// 模板文件目录
				File file = new File(daoTemplatePath);
				// 加载模板文件
				config.setDirectoryForTemplateLoading(file);
				// 设置包装对象
				config.setObjectWrapper(new DefaultObjectWrapper());
				// 加载模板文件
				Template template = config.getTemplate("mybatis-config.ftl",ENCODING);
				// bean名称
				targetProjectResourcesPath = targetProjectResourcesPath  + File.separator + CONFIG;
				File customAbsolutePathFile = new File(targetProjectResourcesPath);
				if(!customAbsolutePathFile.exists()){
					// 生成目录
					customAbsolutePathFile.mkdirs();
				}
				String mybatisFileName = targetProjectResourcesPath + File.separator + "mybatis-config.xml";
				File mybatisFileNameFile = new File(mybatisFileName);
				// 文件存在，删除在生成
				if(mybatisFileNameFile.exists()){
					mybatisFileNameFile.delete();
				}
				String relativeProjectClassPath = relativeProjectPath.replaceAll("/",".");
				Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mybatisFileName),"UTF-8"));
				try{
					// 上文数据存储对象
					Map<String,Object> model = new HashMap<String,Object>();
					model.put("relativeProjectClassPath",relativeProjectClassPath);
					model.put("relativeProjectPath",relativeProjectPath);
					// 设置table对象
					model.put("tables",tables);
					// 生成文件
					template.process(model,out);
					logger.info("生成名称为：" + "【mybatis-config.xml】的Mybatis文件.");
					if(out != null){
						out.flush();
						out.close();
					}
				}
				catch(TemplateException e){
					e.printStackTrace();
				}
				logger.info("【mybatis-config.xml】配置文件生成完成.");
			}
			catch(IOException e1){
				e1.printStackTrace();
				logger.info("【mybatis-config.xml】配置文件生成失败.");
			}
		}
	}
}