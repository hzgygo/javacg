package com.hzgy.generator.service;

import com.hzgy.generator.entity.Path;

public class PathHandler extends Path {

	private static final long serialVersionUID = 9634355791194004L;

	//待生成类业务类型
	private String classServiceType;
	//是否强制生成文件（true:每次都重新生成新的文件；false:不重新生成）
	private Boolean isForce;
	// 生成模板文件存放目录
	private String generatorTemplatePath;
	// 目标项目类路径
	private String targetProjectClassPath;
	// 目标项目资源文件路径
	private String targetProjectResourcesPath;
	//工程相对目录
	private String relativeProjectPath;
	//java文件后缀
	private String javaClassSuffix;
	//模板名称
	private String templateName;
	//生成文件名称
	private String fileName;
	//服务名称简写
	private String serviceSimpleName;
	//生成文件相对路径（不用类文件的相对路径）
	private String fileRelativePath;

	public String getClassServiceType() {
		return classServiceType;
	}

	public void setClassServiceType(String classServiceType) {
		this.classServiceType = classServiceType;
	}

	public Boolean getForce() {
		return isForce;
	}

	public void setForce(Boolean force) {
		isForce = force;
	}

	public String getGeneratorTemplatePath() {
		return generatorTemplatePath;
	}

	public void setGeneratorTemplatePath(String generatorTemplatePath) {
		this.generatorTemplatePath = generatorTemplatePath;
	}

	public String getTargetProjectClassPath() {
		return targetProjectClassPath;
	}

	public void setTargetProjectClassPath(String targetProjectClassPath) {
		this.targetProjectClassPath = targetProjectClassPath;
	}

	public String getTargetProjectResourcesPath() {
		return targetProjectResourcesPath;
	}

	public void setTargetProjectResourcesPath(String targetProjectResourcesPath) {
		this.targetProjectResourcesPath = targetProjectResourcesPath;
	}

	public String getRelativeProjectPath() {
		return relativeProjectPath;
	}

	public void setRelativeProjectPath(String relativeProjectPath) {
		this.relativeProjectPath = relativeProjectPath;
	}

	public String getJavaClassSuffix() {
		return javaClassSuffix;
	}

	public void setJavaClassSuffix(String javaClassSuffix) {
		this.javaClassSuffix = javaClassSuffix;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileRelativePath() {
		return fileRelativePath;
	}

	public void setFileRelativePath(String fileRelativePath) {
		this.fileRelativePath = fileRelativePath;
	}

	@Override
	public String getServiceSimpleName() {
		return serviceSimpleName;
	}

	@Override
	public void setServiceSimpleName(String serviceSimpleName) {
		this.serviceSimpleName = serviceSimpleName;
	}
}
