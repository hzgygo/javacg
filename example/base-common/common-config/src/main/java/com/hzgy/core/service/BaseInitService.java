package com.hzgy.core.service;

import com.hzgy.core.common.RoleCode;
import com.hzgy.core.common.Status;
import com.hzgy.core.config.AuthXmlConfig;
import com.hzgy.core.initialize.AutoDataProcessor;
import com.hzgy.core.util.StringUtil;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;


public abstract class BaseInitService {

	private static Logger logger = LoggerFactory.getLogger(BaseInitService.class);

	private static final String SERVICE_CODE_KEY = "spring.application.name";
	
	@Resource
	public ResourceBundleMessageSource resourceBundleMessageSource;

	@Resource
	public AuthXmlConfig authXmlConfig;

	@Resource
	public AutoDataProcessor autoDataProcessor;

	@Resource
	private Environment environment;

	/**
	 * 解析出来的权限列表
	 */
	private List<Map<String, Object>> listAuth = null;

	/**
     * 服务代码，对应个服务中的名称
	 */
	private String serviceCode;

	/**
	 * 是否有表前缀
	 */
	private Boolean isPerfix;

	/**
     * 初始化管理系统资源配置（management system resource config）
	 * @param listAuth xml 配置文件单个节点解析对象
	 */
	public abstract void initMSConfig(List<Map<String, Object>> listAuth);

	/**
	 * 初始化对外会员系统资源配置（external system resource config）
	 * @param listAuth xml 配置文件单个节点解析对象
	 */
	public abstract void initESConfig(List<Map<String, Object>> listAuth);

	/**
	 * 初始化Security 权限信息
	 * 管理后台功能权限和对外接口权限
	 */
	public void initSecurityAuths(){
		if (listAuth == null){
			listAuth = new ArrayList<>();
		}
		else{
			return;
		}
		List<File> xmlConfigFiles = authXmlConfig.getAuthXmlFile();
		logger.info("begin parse security auths info......");
		if (xmlConfigFiles == null || xmlConfigFiles.size() == 0){
			logger.info("not found security auths xml-config-file.");
			return;
		}
		//从资源文件中获取服务名称
		serviceCode = environment.getProperty(SERVICE_CODE_KEY);
		if (StringUtil.isEmpty(serviceCode)){
			logger.info("service name is null, please check message source key[spring.application.name] in project.");
			return;
		}
		// 获取初始化的xml配置文件
		for (File xmlFile : xmlConfigFiles) {
			if (xmlFile == null) {
				continue;
			}
			listAuth = new ArrayList<>();
			//每个文件解析成list数组
			List<Map<String,String>> authListMap = new ArrayList<>();
			logger.info("parse file:" + xmlFile.getAbsolutePath());
			File parentFile = xmlFile.getParentFile();
			// 临时文件目录
			String parentPath = parentFile.getPath() + File.separator + "tmp";
			// 插件文件名称
			String fileName = xmlFile.getName();
			// 替换后的临时文件前缀
			String tmpFileName = fileName + "_" + xmlFile.lastModified() + ".tmp";
			// 临时文件名称
			String pluginFile = parentPath + File.separator + tmpFileName;
			File tmpFile = new File(pluginFile);
			logger.info("tmp file:" + pluginFile);
			// 判断临时文件是否存在
			if (!tmpFile.exists()) {
				logger.info("the tmp file [" + tmpFile.getAbsolutePath() + "] not exists.");
				//解析文件类型
				int fileConfigType;
				if (fileName.startsWith("ms")){
					fileConfigType = 1;
				}
				else{
					fileConfigType = 2;
				}
				//处理临时文件
				this.createTmpFile(fileConfigType,tmpFile);
				// 若不存在则创建或代表文件已经被修改
				// 被修改的文件要进行重新加载
				logger.info("parse config file [" + fileName + "].");
				try {
					this.parserXml(fileConfigType,xmlFile);
				}
				catch (Exception e) {
					logger.info("parse config file[" + fileName + "] error.",e);
				}
				//调用管理系统资源配置文件存储操作
				if (fileConfigType == 1){
					this.initMSConfig(listAuth);
				}
				//调用对外系统资源配置文件存储操作
				if (fileConfigType == 2){
					this.initESConfig(listAuth);
				}
			}
			else{
				logger.info("tmp file[" + tmpFileName + "] already exist，don't parse.");
			}
		}
	}

	/**
	 * 处理临时文件，保证配置文件在不修改时，解析入库
	 * @param tmpFile 临时文件对象
	 */
	private void createTmpFile(int fileConfigType,File tmpFile){
		//如果临时文件不存在，删除本目录下的所有之前生成的临时文件
		File parentTempFile = tmpFile.getParentFile();
		if (!parentTempFile.exists()){
			boolean boo = parentTempFile.mkdirs();
			if (boo){
				logger.info("create tmp file directory success.");
			}
		}
		else {
			File fileArr[] = parentTempFile.listFiles();
			if (fileArr != null && fileArr.length > 0) {
				for (File file : fileArr) {
					String otherFileName = file.getName();
					if (fileConfigType == 1){
						if (otherFileName.contains("es")){
							continue;
						}
					}
					if (fileConfigType == 2){
						if (otherFileName.contains("ms")){
							continue;
						}
					}
					boolean boo = file.delete();
					if (boo) {
						logger.info("delete tmp file[" + tmpFile.getName() + "] success");
					}
				}
			}
		}
		try {
			boolean boo = tmpFile.createNewFile();
			if (boo){
				logger.info("create tmp file[" + tmpFile.getName() + "]");
			}
		}
		catch (Exception e) {
			boolean boo = tmpFile.delete();
			if (boo){
				logger.info("delete tmp file[" + tmpFile.getName() + "] success");
			}
		}
	}

	/**
	 * dom4j 解析xml 文件
	 * @param fileConfigType 系统配置文件类型 1：管理系统资源配置 2：对外系统资源配置
	 * @param xmlFile 解析xml文件对象
	 * @throws Exception 返回异常
	 */
	private void parserXml(int fileConfigType,File xmlFile) throws Exception {
		try {
			SAXReader saxReader = new SAXReader();
			// 把文件读入到文档
			Document document = saxReader.read(xmlFile);
			// 获取文档根节点
			Element node = document.getRootElement();
			// 递归计数器
			int index = 1;
			if (node != null) {
				Map<String,Object> parseMap = this.parseXmlNode(fileConfigType,null,null,null,null,index,node);
				try {
					//添加到列表中
					listAuth.add(parseMap);
					String nodeName = parseMap.get("codeKey") == null?null:parseMap.get("codeKey").toString();
					String scode = parseMap.get("code") == null?"0":parseMap.get("code").toString();
					Long code = Long.parseLong(scode);
					String name = parseMap.get("name") == null?null:parseMap.get("name").toString();
					String relationPath = parseMap.get("treePath") == null?null:parseMap.get("treePath").toString();
					this.recursionParser(fileConfigType,nodeName, String.valueOf(code), name, relationPath,index, node);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		catch (DocumentException e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 递归方式解析
	 * @param parentNodeName 父节点
	 * @param parentCode 父节点代码
	 * @param parentName 父节点name值
	 * @param relationPath code 关系
	 * @param index 递归次数
	 * @param ele 阶段对象
	 */
	@SuppressWarnings({ "unchecked" })
	private void recursionParser(int fileConfigType,
								 String parentNodeName,
								 String parentCode,
								 String parentName,
								 String relationPath,
								 int index,
								 Element ele) {
		index = index + 1;
		for (Iterator<Element> iterator = ele.elementIterator(); iterator.hasNext();) {
			Element node = iterator.next();
			Map<String,Object> parseMap = this.parseXmlNode(fileConfigType,parentName,parentCode,relationPath,parentNodeName,index,node);
			try {
				//添加到列表中
				listAuth.add(parseMap);
				String nodeName = parseMap.get("codeKey") == null?null:parseMap.get("codeKey").toString();
				String scode = parseMap.get("code") == null?"0":parseMap.get("code").toString();
				Long code = Long.parseLong(scode);
				String name = parseMap.get("name") == null?null:parseMap.get("name").toString();
				String perRelationPath = parseMap.get("treePath") == null?null:parseMap.get("treePath").toString();
				this.recursionParser(fileConfigType,nodeName, String.valueOf(code), name, perRelationPath, index, node);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 解析xml文件单个节点
	 * @param parentName 父节点名称
	 * @param parentCode 父节点代码
	 * @param relationPath 权限描述路径
	 * @param parentNodeName 父节点标签名称
	 * @param index 层级
	 * @param node 节点对象
	 * @return 返回处理后的权限po对象
	 */
	private Map<String,Object> parseXmlNode(int fileConfigType,
											String parentName,
											String parentCode,
											String relationPath,
											String parentNodeName,
											int index,
											Element node) {
		//节点名称
		String nodeName = node.getName();
		// 系统权限信息
		Map<String,Object> parseMap = new HashMap<>();
		//设置服务名称
		parseMap.put("serviceCode",serviceCode);
		//父权限代码
		parseMap.put("parentCode",parentCode == null?0L:Long.parseLong(parentCode));
		//父权限名称
		if (!StringUtil.isEmpty(parentName)) {
			parseMap.put("parentName",parentName);
		}
		//权限代码标识
		if (parentNodeName == null) {
			parentNodeName = nodeName;
		}
		else{
			parentNodeName += "_" + nodeName;
		}
		parseMap.put("codeKey",parentNodeName);
		//视图名称
		parseMap.put("viewName",nodeName);
		//是否有表前缀
		String isPerfixStr = this.getNodeAttrValue(node, "is-perfix");
		if (isPerfixStr != null && (isPerfixStr.equals("true") || isPerfixStr.equals("1"))){
			this.isPerfix = true;
		}
		else{
			this.isPerfix = false;
		}
		// 权限代码
		String code = this.getNodeAttrValue(node, "code");
		parseMap.put("code",code == null?0L:Long.parseLong(code));
		// 树形结构描述关系
		if (StringUtil.isEmpty(relationPath)) {
			relationPath = ",0," + code + ",";
		}
		else {
			relationPath += code + ",";
		}
		// 树形结构描述关系
		parseMap.put("treePath",relationPath);
		// 服务名称
		String serviceName = this.getNodeAttrValue(node, "service-name");
		if (!StringUtil.isEmpty(serviceName)) {
			parseMap.put("serviceName", serviceName);
		}
		else{
			//如果servicName未配置，默认和serviceCode一致
			parseMap.put("serviceName", serviceCode);
		}
		// 可访问角色代码
		String roleCode = this.getNodeAttrValue(node, "role-code");
		if (!StringUtil.isEmpty(roleCode)) {
			parseMap.put("roleCode", roleCode);
		}
		else{
			//如果roleCode 为空（未配合），默认访问角色为trust
			parseMap.put("roleCode", RoleCode.TRUST.getCode());
		}
		// 是否显示
		String isShow = this.getNodeAttrValue(node, "is-show");
		if (!StringUtil.isEmpty(isShow)) {
			parseMap.put("isShow", isShow);
		}
		else{
			parseMap.put("isShow", Status.YES.getValue());
		}
		// 中文名称
		String name = this.getNodeAttrValue(node, "name");
		if (!StringUtil.isEmpty(name)) {
			parseMap.put("name", name);
		}
		// 权限类型 开发可见权限，上线可见权限
		String type = this.getNodeAttrValue(node, "auth-type");
		if (!StringUtil.isEmpty(type)) {
			parseMap.put("authType", Integer.parseInt(type));
		}
		else{
			parseMap.put("authType", 0);
		}
		// 设置调用方法名称（service层方法）
		String methodName = this.getNodeAttrValue(node, "method-name");
		if (!StringUtil.isEmpty(methodName)) {
			parseMap.put("methodName",methodName);
		}
		// restful 风格的通配符路径
		String wildcardPath = this.getNodeAttrValue(node, "wildcard-path");
		if (!StringUtil.isEmpty(wildcardPath)) {
			if (!wildcardPath.startsWith("/")){
				wildcardPath = "/" + serviceCode + "/api/" + wildcardPath;
			}
			else{
				wildcardPath = "/" + serviceCode + "/api" + wildcardPath;
			}
			parseMap.put("wildcardPath", wildcardPath);
		}
		// 访问路径
		String urlPath = this.getNodeAttrValue(node, "url-path");
		if (!StringUtil.isEmpty(urlPath)) {
			if (!urlPath.startsWith("/")){
				urlPath = "/" + serviceCode + "/api/" + urlPath;
			}
			else{
				urlPath = "/" + serviceCode + "/api" + urlPath;
			}
			parseMap.put("urlPath", urlPath);
		}
		// 设置模型名称（表名称）
		String modelName = this.getNodeAttrValue(node, "model-name");
		if (!StringUtil.isEmpty(modelName)) {
			parseMap.put("modelName", modelName);
		}
		// 设置模型名称（表名称）
		String isDefault = this.getNodeAttrValue(node, "is-default");
		if (!StringUtil.isEmpty(isDefault)) {
			if (isDefault.equals("true") || isDefault.equals("1")) {
				parseMap.put("isDefault", 1);
			}
			else{
				parseMap.put("isDefault", 0);
			}
		}
		else{
			parseMap.put("isDefault", 0);
		}

		//实体bean名称
		String entityName = parseTableCode(modelName);
		if (!StringUtil.isEmpty(entityName)) {
			parseMap.put("entityName", entityName);
		}
		//实体bean名称
		String treeLevel = this.getNodeAttrValue(node, "tree-level");
		if (!StringUtil.isEmpty(treeLevel)) {
			// 设置树等级
			parseMap.put("treeLevel",treeLevel);
		}

		if (node.elementIterator().hasNext()) {
			parseMap.put("isChildNode",Integer.parseInt("0"));
		}
		else {
			parseMap.put("isChildNode",Integer.parseInt("1"));
		}
		return parseMap;
	}

	/**
	 * 处理表名称
	 * @param code 代码
	 * @return 返回解析后的表名称
	 */
	private String parseTableCode(String code) {
		if (code == null){
			return null;
		}
		StringBuilder sbcode = new StringBuilder();
		String entityName = "";
		String modelNameArr [] = code.split("_");
		for(String mname:modelNameArr){
			//如果有表前缀，前缀省略
			if (this.isPerfix) {
				if (StringUtil.isEmpty(entityName)){
					continue;
				}
			}
			sbcode.append(StringUtil.toUpperCaseFirstOne(mname));
		}
		return sbcode.toString();
	}

	/**
	 * 根据属性名称获取值
	 *
	 * @param node 节点node
	 * @param name 节点属性名称
	 * @return 返回节点属性值
	 */
	@SuppressWarnings("rawtypes")
	private String getNodeAttrValue(Element node, String name) {
		String rval = null;
		if (node.attributes() != null && node.attributes().size() > 0) {
			for (Iterator subiterator = node.attributeIterator(); subiterator.hasNext();) {
				Attribute item = (Attribute) subiterator.next();
				String attrName = item.getName();
				String attrValue = item.getValue();
				if (attrName != null && attrName.equals(name)) {
					if ("true".equals(attrValue.trim())) {
						attrValue = "1";
					}
					else if ("false".equals(attrValue.trim())) {
						attrValue = "0";
					}
					rval = attrValue;
				}
			}
		}
		return rval;
	}
}
