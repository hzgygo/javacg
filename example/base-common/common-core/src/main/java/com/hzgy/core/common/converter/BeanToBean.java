package com.hzgy.core.common.converter;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * po 转成dto的工具类
 */
@SuppressWarnings("unchecked")
public class BeanToBean<S,T>{

	/**
	 * bean对象转换成bean对象
	 * @param s bean对象
	 * @param tcls bean class对象
	 * @return Output 对象
	 */
	public T toBean(S s,Class<T> tcls) {
		if (s == null) {
			return null;
		}
		try {
			Object obj = tcls.newInstance();
			BeanUtils.copyProperties(s,obj);
			return (T)obj;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public T toBean(S s,Class<T> tcls,String... ignoreProperties) {
		if (s == null) {
			return null;
		}
		try {
			Object obj = tcls.newInstance();
			BeanUtils.copyProperties(s,obj,ignoreProperties);
			return (T)obj;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过json方式拷贝
	 * @param s         源对象
	 * @param tcls 目标类
	 * @return 拷贝结果
	 */
	public T jsonBean(S s,Class<T> tcls) {
		if (s == null) {
			return null;
		}
		try {
			String json = JSON.toJSONString(s);
			Object obj = JSON.parseObject(json,tcls);
			return (T)obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * po对象列表转换成output对象列表
	 * @param s po列表
	 * @param tcls  output class对象
	 * @return Output列表
	 */
	public List<T> toListBean(List<S> s,Class<T> tcls){
		List<T> ts = new ArrayList<>();
		s.forEach(po -> ts.add(toBean(po,tcls)));
		return ts;
	}

	/**
	 * 通过json方式转换对象
	 * @param s   请求列表
	 * @param tcls 转换类
	 * @return 返回列表
	 */
	public List<T> jsonListBean(List<S> s,Class<T> tcls){
		List<T> ts = new ArrayList<>();
		s.forEach(so -> ts.add(jsonBean(so,tcls)));
		return ts;
	}
}
