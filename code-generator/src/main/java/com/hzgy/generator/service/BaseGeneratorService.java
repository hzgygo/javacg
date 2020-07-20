package com.hzgy.generator.service;

import java.io.File;
import java.io.IOException;




public abstract class BaseGeneratorService {
	
	/**数据库设计文件存放目录**/
	public static String PDM_FILE_PATH = "classpath:creater/database/";
	/**视图模板文件存放目录**/
	public static String CLASS_TEMPLATE_FILE_PATH = "classpath:creater/template/class/";
	/**临时文件存放目录**/
	public static String TMP_FILE_PATH = "classpath:creater/tmp/";
	/**实体bean存放目录**/
	public static String ENTITY_CLASS_FILE_PATH = "classpath:com/bs/plugins/custom/";
	/**视图页面模板存放目录**/
	public static String VIEW_TEMPLATE_FILE_PATH = "classpath:creater/template/view/";
	/**视图文件存放目录**/
	public static String VIEW_FILE_PATH = "WebContent/WEB-INF/view/";
	/**视图文件存放目录**/
	public static String VIEW_FILE_PATH_MAVEN = "webapp/WEB-INF/view/";
	/**类过滤属性**/
	public static String filterAttributes [] = {"serialVersionUID"};
	/**模板编码**/
	public static String ENCODING = "utf-8";
	/**基类目录**/
	public static String BASE = "base";
	/**类前缀**/
	public static String BASE_CLASS_PREFIX = "Base";
	public static String DAO_CLASS_PREFIX = "Dao";
	public static String SERVICE_CLASS_PREFIX = "Service";
	public static String API_CLASS_PREFIX = "Api";
	public static String CONTROLLER_CLASS_PREFIX = "Controller";
	public static String DO_CLASS_PREFIX = "Do";
	public static String DTO_CLASS_PREFIX = "Dto";
	public static String PO_CLASS_PREFIX = "Po";
	public static String VO_CLASS_PREFIX = "Vo";
	public static String VO_INPUT_CLASS_PREFIX = "Input";
	public static String VO_OUTPUT_CLASS_PREFIX = "Output";
	public static String CLIENT_CLASS_PREFIX = "Client";
	public static String DTO = "dto";
	public static String VO = "vo";
	public static String PO = "po";
	/**java文件后缀**/
	public static String JAVA = ".java";
	/**jsp文件后缀**/
	public static String JSP = ".jsp";
	/**ftl文件后缀**/
	public static String FTL = ".ftl";
	/**xml文件后缀**/
	public static String XML = ".xml";
	/**api**/
	public static String API = "api";
	/**properties文件后缀**/
	public static String PROPERTIES = ".properties";
	/**entity存放目录**/
	public static String ENTITY = "entity";
	/**模块目录**/
	public static String MODULES = "modules";
	/**模型目录**/
	public static String MODEL = "model";
	/**dao存放目录**/
	public static String DAO = "dao";
	/**service存放目录**/
	public static String SERVICE = "service";
	/**controller存放目录**/
	public static String CONTROLLER = "controller";
	/**mybatis存放目录**/
	public static String MYBATIS = "mybatis";
	/**数据库相关文件存放路径*/
	public static String DATABASE = "database";
	/**数据库相关文件存放路径*/
	public static String CONFIG = "configures";
	/**dubbo存放目录**/
	public static String DUBBO = "dubbo";
	/**message存放目录**/
	public static String MESSAGE = "message";
	/**message存放目录**/
	public static String PROPERTIES_DIR = "properties";
	/**message存放目录**/
	public static String MESSAGE_LOCAL = "Messages_zh_CN";
	
	public BaseGeneratorService(){
	}
	
	
	/**
	 * 判断文件是否有修改
	 * @param fileAbsolutePath
	 * @return
	 */
	public boolean fileIsModify(String fileAbsolutePath){
		File file = new File(fileAbsolutePath);
		boolean isModify = false;
		if (file.exists()){
			//当前文件的最后一次修改时间
			long currentLastModifyTime = file.lastModified();
			String fileName = file.getName();
			String tmpFilePath = file.getParent() 
					+ File.separator 
					+ "tmp";
			File filePath = new File(tmpFilePath);
			//判断文件目录是否存在
			if (!filePath.exists()){
				filePath.mkdirs();
			}
			String tmpFilePathName = tmpFilePath
					+ File.separator 
					+ fileName+"_"+currentLastModifyTime
					+ ".tmp";
			File tmpFile = new File(tmpFilePathName);
			//判断文件是否存在，若干不存在生成新的临时文件
			if (!tmpFile.exists()){
				try {
					tmpFile.createNewFile();
					isModify = true;
					//删除历史临时文件，可能存在也可能不存在
					File fileList[] = filePath.listFiles();
					if (fileList != null){
						for(File fileTemp:fileList){
							String fileTempName = fileTemp.getName();
							if (fileTempName.startsWith(fileName)){
								fileTemp.delete();
								break;
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				isModify = false;
			}
		}
		return isModify;
	}
	
	/**
	 * 判断文件是否有修改
	 * @param fileAbsolutePath
	 * @param fileTmpAbsolutePath
	 * @return
	 */
	public boolean fileIsModify(String fileAbsolutePath,String fileTmpAbsolutePath,String type){
		File file = new File(fileAbsolutePath);
		boolean isModify = false;
		if (file.exists()){
			//当前文件的最后一次修改时间
			long currentLastModifyTime = file.lastModified();
			String fileName = file.getName();
			String tmpFilePath = fileTmpAbsolutePath + File.separator + type;
			File filePath = new File(tmpFilePath);
			//判断文件目录是否存在
			if (!filePath.exists()){
				filePath.mkdirs();
			}
			String tmpFilePathName = tmpFilePath
					+ File.separator 
					+ fileName+"_"+currentLastModifyTime
					+ ".tmp";
			File tmpFile = new File(tmpFilePathName);
			//判断文件是否存在，若干不存在生成新的临时文件
			if (!tmpFile.exists()){
				try {
					tmpFile.createNewFile();
					isModify = true;
					//删除历史临时文件，可能存在也可能不存在
					File fileList[] = filePath.listFiles();
					if (fileList != null){
						for(File fileTemp:fileList){
							String fileTempName = fileTemp.getName();
							if (fileTempName.startsWith(fileName)){
								fileTemp.delete();
								break;
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				isModify = false;
			}
		}
		return isModify;
	}
	
	
	/**
	 * 获取工程的绝对路径
	 * @param absolutePathTemp
	 * @return
	 */
	public String getAbsolutePath(String absolutePathTemp,String type){
		if (absolutePathTemp == null){
			return null;
		}
		String absolutePath = null;
		int index = -1;
		if (absolutePathTemp.indexOf("build") > 0){
			//类编译在build目录下
			index = absolutePathTemp.indexOf("build");
		}
		else if (absolutePathTemp.indexOf("WebContent") > 0){
			//类编译在WEB-INF目录下
			index = absolutePathTemp.indexOf("WebContent");
		}
		else if (absolutePathTemp.indexOf("WebRoot") > 0){
			//类编译在WEB-INF目录下
			index = absolutePathTemp.indexOf("WebRoot");
		}
		else if (absolutePathTemp.indexOf("webapp") > 0){
			//类编译在WEB-INF目录下
			index = absolutePathTemp.indexOf("webapp");
		}
		else {
			return null;
		}
		int indexClasses = absolutePathTemp.indexOf("classes");
		if (index >= 0){
			String startAbsolutePath = absolutePathTemp.substring(0, index);
			String endAbsolutePath = absolutePathTemp.substring(indexClasses + 7, absolutePathTemp.length());
			absolutePath = startAbsolutePath + type + endAbsolutePath;
		}
		return absolutePath;
	}
	
	public String getResourcesAbsolutePath(String absolutePathTemp){
		if (absolutePathTemp == null){
			return null;
		}
		String absolutePath = null;
		int index = -1;
		if (absolutePathTemp.indexOf("build") > 0){
			//类编译在build目录下
			index = absolutePathTemp.indexOf("build");
		}
		else if (absolutePathTemp.indexOf("WebContent") > 0){
			//类编译在WEB-INF目录下
			index = absolutePathTemp.indexOf("WebContent");
		}
		else if (absolutePathTemp.indexOf("WebRoot") > 0){
			//类编译在WEB-INF目录下
			index = absolutePathTemp.indexOf("WebRoot");
		}
		else if (absolutePathTemp.indexOf("webapp") > 0){
			//类编译在WEB-INF目录下
			index = absolutePathTemp.indexOf("webapp");
		}
		if (index >= 0){
			absolutePath = absolutePathTemp.substring(0, index);
		}
		return absolutePath;
	}
	
	

	
	/**
	 * 视图文件存放目录
	 * @param absolutePathTemp
	 * @return
	 */
	public String getViewAbsolutePath(String absolutePathTemp,String type){
		String absolutePath = null;
		int index = -1;
		if (absolutePathTemp.indexOf("build") > 0){
			//类编译在build目录下
			index = absolutePathTemp.indexOf("build");
		}
		else if (absolutePathTemp.indexOf("WebContent") > 0){
			//类编译在WEB-INF目录下
			index = absolutePathTemp.indexOf("WebContent");
		}
		else if (absolutePathTemp.indexOf("WebRoot") > 0){
			//类编译在WEB-INF目录下
			index = absolutePathTemp.indexOf("WebRoot");
		}
		else if (absolutePathTemp.indexOf("webapp") > 0){
			//类编译在WEB-INF目录下
			index = absolutePathTemp.indexOf("webapp");
		}
		else {
			return null;
		}
		String startAbsolutePath = absolutePathTemp.substring(0, index);
		if (type.equals("maven")){
			absolutePath  = startAbsolutePath + VIEW_FILE_PATH_MAVEN;
		}
		else{
			absolutePath  = startAbsolutePath + VIEW_FILE_PATH;
		}
		return absolutePath;
	}
	
}