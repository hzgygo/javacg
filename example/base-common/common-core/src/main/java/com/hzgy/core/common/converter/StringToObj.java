package com.hzgy.core.common.converter;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * po 转成dto的工具类
 */
@SuppressWarnings("unchecked")
public class StringToObj<T>{

	/**
	 * 通过json方式拷贝
	 * @param s         源对象
	 * @param tcls 目标类
	 * @return 拷贝结果
	 */
	public T jsonObj(String s,Class<T> tcls) {
		if (s == null) {
			return null;
		}
		try {
			Object obj = JSON.parseObject(s,tcls);
			return (T)obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
