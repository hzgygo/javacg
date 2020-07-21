package com.hzgy.core.exception;

import com.hzgy.core.exception.base.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodeException extends BaseException {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(CodeException.class);

	/**
	 * 业务返回码
	 */
	private Integer code;

	/**
	 * 强制默认使用的提示信息
	 */
	private String  defaultMsg;

	/**
	 * 是否强制使用信息
	 */
	private Boolean isDefault = false;

	/**
	 * 替换错误消息,多个消息用##分割
	 */
	private String [] replaceMsg;

	private Object data;

	public CodeException(Integer code, Throwable t) {
		super(String.valueOf(code), t);
		this.code = code;
		logger.error("service exception:" + code,t);
	}

	public CodeException(Integer code) {
		super(String.valueOf(code));
		this.code = code;
		logger.error("service exception:" + code);
	}

	/**
	 * 消息异常处理类
	 * @param code			错误代码
	 * @param msg			提示信息
	 * @param isDefault	是否为强制使用信息
	 */
	public CodeException(Integer code,String msg,boolean isDefault) {
		super(String.valueOf(code));
		this.code = code;
		this.isDefault = isDefault;
		if (isDefault) {
			this.defaultMsg = msg;
		}
		else {
			if (this.replaceMsg == null) {
				this.replaceMsg = new String[1];
			}
			this.replaceMsg[0] = msg;
		}
		logger.error("service exception:" + code);
	}

	public CodeException(Integer code,String msg,boolean isDefault,Object data) {
		super(String.valueOf(code));
		this.code = code;
		this.data = data;
		this.isDefault = isDefault;
		if (isDefault) {
			this.defaultMsg = msg;
		}
		else {
			if (this.replaceMsg == null) {
				this.replaceMsg = new String[1];
			}
			this.replaceMsg[0] = msg;
		}
		logger.error("service exception:" + code);
	}

	public CodeException(Integer code,String rmsg) {
		super(String.valueOf(code));
		this.code = code;
		if (this.replaceMsg == null){
			this.replaceMsg = new String[1];
		}
		this.replaceMsg[0] = rmsg;
		logger.error("service exception:" + code);
	}

	public CodeException(Integer code,String [] rmsg) {
		super(String.valueOf(code));
		this.code = code;
		this.replaceMsg = rmsg;
		logger.error("service exception:" + code);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String[] getReplaceMsg() {
		return replaceMsg;
	}

	public void setReplaceMsg(String[] replaceMsg) {
		this.replaceMsg = replaceMsg;
	}

	public String getDefaultMsg() {
		return defaultMsg;
	}

	public void setDefaultMsg(String defaultMsg) {
		this.defaultMsg = defaultMsg;
	}

	public Boolean getDefault() {
		return isDefault;
	}

	public void setDefault(Boolean aDefault) {
		isDefault = aDefault;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}