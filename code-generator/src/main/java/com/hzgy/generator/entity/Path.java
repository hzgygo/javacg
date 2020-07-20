package com.hzgy.generator.entity;

public class Path extends Entity{
	private static final long serialVersionUID = 9634355791194004L;

	/**
	 * 微服务名称简写，用于生成对外调用接口或实体时使用
	 */
	private String serviceSimpleName;

	/**
	 * 微服务名称，生成时使用，后续可以根据需求自行修改
	 */
	private String serviceName;

	/**
	 *  api 前缀
	 */
	private String apiPerfix;

	/**
	 *  api 相对路径
	 */
	private String apiPath;
	/**
	 * 工程跟目录
	 */
	private String serviceRootPath;

	/**
	 * 生成器根目录
	 */
	private String generatorRootPath;
	
	/**
	 * 工程相对路径
	 */
	private String relativePath;

	/**
	 * 资源文件相对路径
	 */
	private String resourcesPath;

	/**
	 * java相对路径
	 */
	private String javaPath;

	/**
	 * 核心包java路径
	 */
	private String dependPackage;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceSimpleName() {
		return serviceSimpleName;
	}

	public void setServiceSimpleName(String serviceSimpleName) {
		this.serviceSimpleName = serviceSimpleName;
	}

	public String getApiPath() {
		return apiPath;
	}

	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}

	public String getApiPerfix() {
		return apiPerfix;
	}

	public void setApiPerfix(String apiPerfix) {
		this.apiPerfix = apiPerfix;
	}

	public String getServiceRootPath() {
		return serviceRootPath;
	}

	public void setServiceRootPath(String serviceRootPath) {
		this.serviceRootPath = serviceRootPath;
	}

	public String getGeneratorRootPath() {
		return generatorRootPath;
	}

	public void setGeneratorRootPath(String generatorRootPath) {
		this.generatorRootPath = generatorRootPath;
	}

	public String getRelativePath() {
		return relativePath;
	}
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public String getResourcesPath() {
		return resourcesPath;
	}

	public void setResourcesPath(String resourcesPath) {
		this.resourcesPath = resourcesPath;
	}

	public String getJavaPath() {
		return javaPath;
	}

	public void setJavaPath(String javaPath) {
		this.javaPath = javaPath;
	}

	public String getDependPackage() {
		return dependPackage;
	}

	public void setDependPackage(String dependPackage) {
		this.dependPackage = dependPackage;
	}
}
