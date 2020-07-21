package com.hzgy.core.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hzgy.core.common.constant.Constants;
import com.hzgy.core.common.FileType;
import com.hzgy.core.common.NumberFormat;
import com.hzgy.core.entity.ResultData;
import com.hzgy.core.entity.block.EncryptData;
import com.hzgy.core.initialize.AutoDataProcessor;
import com.hzgy.core.util.AESUtil;
import com.hzgy.core.util.RSAUtil;
import com.hzgy.core.util.SHAUtil;
import com.hzgy.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Locale;

import static com.hzgy.core.common.constant.Constants.*;


public abstract class BaseService{

	public static Logger logger = LoggerFactory.getLogger(BaseService.class);

	private static final String SERVICE_NAME_KEY = "spring.application.name";

	public static final String CLASS_PATH = "classpath:";
	
	@Resource
	public ResourceBundleMessageSource resourceBundleMessageSource;

	@Resource
	public AutoDataProcessor autoDataProcessor;

	@Resource
	public Environment environment;

	@Value(value="${spring.profiles.active}")
	public String active;

	@PostConstruct
	public void setApplicationDebug(){
		if (environment != null){
			try {
				String isdebug = environment.getProperty("application.debug");
				if (StringUtil.isEmpty(isdebug)){
					Constants.IS_DEBUG = false;
				}
				if (isdebug.equals("true")){
					Constants.IS_DEBUG = true;
				}
			}
			catch (Exception e){
				Constants.IS_DEBUG = false;
			}
		}
	}

	/**
	 * 获取服务器端公钥字符串
	 * @return 返回api服务器端公钥字符串
	 */
	private String getServerPublicKey(){
		try {
			String configRootPath = this.getConfigRootPath();
			String keyPath = configRootPath + File.separator + "security";
			//公钥文件
			String publicKeyFile = keyPath + File.separator + "server-public.key";
			//获取公钥
			PublicKey publicKey = (PublicKey) RSAUtil.getKey(publicKeyFile);
			//生成公钥字符串
			return Base64.getEncoder().encodeToString(publicKey.getEncoded());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 获取客户端公钥
	 * @return 返回公钥对象
	 * @throws Exception 异常
	 */
	private PublicKey getPublicKey(String alias) throws Exception {
		String configRootPath = this.getConfigRootPath();
		String keyPath = configRootPath + File.separator + "security";
		//公钥文件
		String publicKeyFile = keyPath + File.separator + alias + "-client-pub.key";
		//获取公钥
		return (PublicKey) RSAUtil.getKey(publicKeyFile);
	}



	/**
	 * 获取服务器私钥
	 *
	 * @return 返回私钥对象
	 * @throws Exception 异常
	 */
	private PrivateKey getPrivateKey(String alias) throws Exception {
		String configRootPath = this.getConfigRootPath();
		String keyPath = configRootPath + File.separator + "security";
		//私钥文件
		String privateKeyFile = keyPath + File.separator + alias + "-client-pri.key";
		//获取私钥
		return (PrivateKey) RSAUtil.getKey(privateKeyFile);
	}

	/**
	 * 提交数据
	 *
	 * @param jsonMateData json数据
	 */
	public EncryptData postEncryptData(String alias, String jsonMateData) {
		logger.info("请求业务数据:" + jsonMateData);
		String configRootPath = this.getConfigRootPath();
		//通过接口获取的服务器公钥
		String spuk = this.getServerPublicKey();
		try {
			//客户端动态秘钥,可以随机生成，16位长度
			String aeskey = StringUtil.getRandom(16,true,true,true);
			//获取私钥文件对象
			PrivateKey prk = getPrivateKey(alias);
			//获取公钥文件对象
//            PublicKey puk = getPublicKey(alias);
			//aes加密data，然后base64&URL encoder,做为接口传输数据
			String data64 = AESUtil.encryptToBase64(jsonMateData, aeskey);
			String reqData = java.net.URLEncoder.encode(data64, Constants.ENCODING_UTF);
			//原始数据sha256生成签名摘要在生成签名，然后base64&URL encoder,做为接口传输数据
			String jsonDataSha256 = SHAUtil.getSHA256(jsonMateData);
			byte[] sign = RSAUtil.sign(prk, jsonDataSha256.getBytes(Constants.ENCODING_UTF));
			String sign64 = Base64.getEncoder().encodeToString(sign);
			String reqSign = java.net.URLEncoder.encode(sign64, Constants.ENCODING_UTF);
			//调用端公钥字符串通过aes+base64加密,然后URL encoder,做为接口传输数据
			String pukPath = configRootPath + File.separator + "security" + File.separator + alias + "-client.crt";
			PublicKey rsaPublicKey = RSAUtil.getPublicKey(pukPath);
			String spuk64 = Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded());
			String puk64 = AESUtil.encryptToBase64(spuk64, aeskey);
			String reqPuk = java.net.URLEncoder.encode(puk64, Constants.ENCODING_UTF);
			//aes密码通过服务器公钥加密
			PublicKey serverPuk = RSAUtil.publicKeyByStr(spuk);
			byte[] aeskeyBytes = RSAUtil.encrypt(serverPuk, aeskey.getBytes(Constants.ENCODING_UTF));
			String aeskey64 = Base64.getEncoder().encodeToString(aeskeyBytes);
			String reqPwd = java.net.URLEncoder.encode(aeskey64, Constants.ENCODING_UTF);
			EncryptData encryptData = new EncryptData();
			encryptData.setSign(reqSign);
			encryptData.setPuk(reqPuk);
			encryptData.setData(reqData);
			encryptData.setPwd(reqPwd);
			return encryptData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new EncryptData();
	}

	/**
	 * 获取国家化文件配置的提示信息
	 * @param code 国际化文件code
	 * @param replaceMsg 国家化替换内容数组
	 * @return 返回国家化文件配置内容
	 */
	public String getMessage(String code,String replaceMsg[]) {
		String returnMsg = null;
		try {
			returnMsg = resourceBundleMessageSource.getMessage(code, replaceMsg, Locale.CHINESE);
		}
		catch(Exception e) {
			returnMsg = code;
			System.out.println("get message error:" + e.getMessage());
		}
		return returnMsg;
	}


	/**
	 * 获取国家化文件配置的提示信息
	 * @param code 国际化文件code
	 * @param replaceMsg 国家化替换内容数组
	 * @return 返回国家化文件配置内容
	 */
	public String getMessage(String code,String replaceMsg[],Locale locale) {
		String returnMsg = null;
		try {
			returnMsg = resourceBundleMessageSource.getMessage(code, replaceMsg,locale);
		}
		catch(Exception e) {
			returnMsg = code;
			System.out.println("get message error:" + e.getMessage());
		}
		return returnMsg;
	}
	
	/**
	 * 获取自己提示信息
	 * @param cls 类
	 * @param method 方法名称
	 * @param code 国际化文件key
	 * @return 返回国家化文件配置内容
	 */
	public String getMessage(Class<?> cls,String method,String code,String replaceMsg[]) {
		String returnMsg;
		String key = null;
		try {
			key = StringUtil.toLowerCaseFirstOne(cls.getSimpleName()) + "." + method + "." + code;
			returnMsg = resourceBundleMessageSource.getMessage(key, replaceMsg, Locale.CHINESE);
			if (returnMsg == null || returnMsg.equals("")) {
				returnMsg = key;
			}
		}
		catch(Exception e) {
			returnMsg = key;
			System.out.println("get message error:" + e.getMessage());
		}
		return returnMsg;
	}

	public String getMessage(Class<?> cls,String method,String code) {
		String returnMsg;
		String key = null;
		try {
			key = cls.getSimpleName() + "." + method + "." + code;
			returnMsg = resourceBundleMessageSource.getMessage(key, null, Locale.CHINESE);
			if (returnMsg.equals("")) {
				returnMsg = key;
			}
		}
		catch(Exception e) {
			returnMsg = key;
			System.out.println("get message error:" + e.getMessage());
		}
		return returnMsg;
	}

	/**
	 * 验证未通过返回验证信息
	 *
	 * @param response   http response对象
	 * @param resultData 返回结果字符串
	 * @throws IOException 抛io异常
	 */
	public void responseOut(HttpServletResponse response, ResultData resultData){
		response.setCharacterEncoding(ENCODING_UTF);
		response.setContentType(CONTENT_TYPE_JSON);
		try {
			String reult = "";
			if (resultData != null){
				reult = JSON.toJSONString(resultData, SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
			}
            if (IS_DEBUG) {
                logger.info(String.format("responsse result:%s", reult));
            }
			response.getOutputStream().write(reult.getBytes(ENCODING_UTF));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 强制关闭防止重写
			try {
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void responseOut(HttpServletResponse response, String object){
		response.setCharacterEncoding(ENCODING_UTF);
		response.setContentType(CONTENT_TYPE_TEXT);
		try {
			if (IS_DEBUG) {
				logger.info(String.format("response result:%s", object));
			}
			response.getOutputStream().write(object.getBytes(ENCODING_UTF));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 强制关闭防止重写
			try {
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void downloadResponse(Class<?> cls, String method,Integer code, HttpServletResponse response){
		String message = getMessage(cls,method,String.valueOf(code));
		ResultData<String> resultData = new ResultData<>();
		resultData.setMessage(message);
		resultData.setCode(code);
		this.responseOut(response,resultData);
	}


	/**
	 * 获取平台域名
	 * @return 平台域名
	 */
	protected String getPlatformDomain(){
		String pdomain = environment.getProperty(Constants.PLATFORM_DOMAIN);
		if  (StringUtil.isEmpty(pdomain)){
			pdomain = "tbcpp.taipnet.com";
		}
		return pdomain;
	}

	/**
	 * 上传那文件根路径
	 * @return
	 */
	protected String getUploadFileRootPath(){
		String fileRootPath = environment.getProperty(Constants.FILE_ROOT_PATH);
		if  (StringUtil.isEmpty(fileRootPath)){
			fileRootPath = "/data/file/upload";
		}
		return fileRootPath;
	}

	/**
	 * 下载那文件根路径
	 * @return
	 */
	protected String getDownloadFileRootPath(){
		String fileRootPath = environment.getProperty(Constants.FILE_DOWNLOAD_ROOT_PATH);
		if  (StringUtil.isEmpty(fileRootPath)){
			fileRootPath = "/data/file/download";
		}
		return fileRootPath;
	}

	/**
	 * 上传那文件根路径
	 * @return
	 */
	protected String getFileServiceDomain(){
		String fileServiceUrl = environment.getProperty(FILE_SERVICE_DOMAIN);
		if  (StringUtil.isEmpty(fileServiceUrl)){
			fileServiceUrl = "http://localhost:9006";
		}
		return fileServiceUrl;
	}


	/**
	 * 获取资源服务器域名
	 * @return 资源服务器域名
	 */
	protected String getResourceDomain(){
		String pdomain = environment.getProperty(Constants.RESOURCE_DOMAIN);
		if  (StringUtil.isEmpty(pdomain)){
			pdomain = "res.taipnet.com";
		}
		return pdomain;
	}

	/**
	 * 获取登录地址
	 * @return
	 */
	protected String getLoginUrl(){
		String loginUrl = environment.getProperty(Constants.LOGIN_URL);
		if  (StringUtil.isEmpty(loginUrl)){
			loginUrl = "http://10.20.15.147:7001/web/auth";
		}
		return loginUrl;
	}


	/**
	 * 获取系统根目录
	 * @return 返回系统路径
	 */
	protected String getRootPath(){
		String  rootPath = System.getProperty("user.dir");
		//开发环境按照类的根存放目录获取路径
		if (!StringUtil.isEmpty(active) && active.equals("dev")){
			File path = null;
			try {
				path = new File(ResourceUtils.getURL(CLASS_PATH).getPath());
				rootPath = path.getParentFile().getAbsolutePath();
			} catch (FileNotFoundException e) {
				rootPath = System.getProperty("user.dir");
			}
		}
		return rootPath;
	}

	/**
	 * 获取系统配置根目录
	 * @return 返回系统路径
	 */
	protected String getConfigRootPath(){
		String  rootPath = getRootPath();
		return rootPath + File.separator + Constants.CONFIG;
	}

	/**
	 * 获取用户统一权限系统url
	 * @return
	 */
	protected Boolean getUserAuthIsOpen() {
		String isopen = environment.getProperty("user.auth.isopen");
		if (StringUtil.isEmpty(isopen)){
			return false;
		}
		else{
			if (isopen.equals("true")){
				return true;
			}
			else {
				return false;
			}
		}
	}


	/**
	 * 获取用户统一权限系统url
	 * @param relUrl 接口地址相对路径,斜线开头
	 * @return
	 */
	protected String getUserAuthUrl(String relUrl) {
		String remoteUrl = environment.getProperty("user.auth.url");
		if (StringUtil.isEmpty(relUrl)){
			return remoteUrl;
		}
		else{
			return remoteUrl + relUrl;
		}
	}


	/**
	 * 获取系统配置根目录
	 * @return 返回系统路径
	 */
	protected String getTreePath(String treePath){
		if (StringUtil.isEmpty(treePath)){
			return null;
		}
		String arr[] = treePath.split(",");
		String returnTreePath = null;
		for (String tp:arr){
			if (StringUtil.isEmpty(tp)){
				continue;
			}
			if (returnTreePath == null){
				returnTreePath = tp;
			}
			else{
				returnTreePath += "," + tp;
			}
		}
		return returnTreePath;
	}

	/**
	 * 获取英文首字母大写数字
	 * @param num 数字
	 * @return 英文首字母大写数字
	 */
	public String getUpperEnNum(Integer num){
		if (num == null){
			return "ZERO";
		}
		if (num.equals(1)){
			return NumberFormat.NUM_ONE.getValue();
		}
		else if (num.equals(2)){
			return NumberFormat.NUM_TWO.getValue();
		}
		else if (num.equals(3)){
			return NumberFormat.NUM_THREE.getValue();
		}
		else if (num.equals(4)){
			return NumberFormat.NUM_FOUR.getValue();
		}
		else if (num.equals(5)){
			return NumberFormat.NUM_FIVE.getValue();
		}
		else if (num.equals(6)){
			return NumberFormat.NUM_SIX.getValue();
		}
		else if (num.equals(7)){
			return NumberFormat.NUM_SEVEN.getValue();
		}
		else if (num.equals(8)){
			return NumberFormat.NUM_EIGHT.getValue();
		}
		else if (num.equals(9)){
			return NumberFormat.NUM_NINE.getValue();
		}
		else if (num.equals(10)){
			return NumberFormat.NUM_TEN.getValue();
		}
		return "ZERO";
	}

	/**
	 * 获取文件类型
	 * @param typeName 类型名称
	 * @return 类型值
	 */
	protected Integer getFileType(String typeName){
		Integer type = 0;
		typeName = typeName.toUpperCase();
		switch (typeName){
			case ".MP4":
				type = FileType.MP4.getType();
				break;
			case ".OGG3":
				type = FileType.OGG3.getType();
				break;
			case ".MP3":
				type = FileType.MP3.getType();
				break;
			case ".WAV":
				type = FileType.WAV.getType();
				break;
			case ".GIF":
				type = FileType.GIF.getType();
				break;
			case ".PNG":
				type = FileType.PNG.getType();
				break;
			case ".JPG":
				type = FileType.JPG.getType();
				break;
		}
		return type;
	}
}
