package com.hzgy.core.common.converter;

import com.alibaba.fastjson.JSON;
import com.hzgy.core.entity.BaseOutput;
import com.hzgy.core.entity.BasePo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * po 转成dto的工具类
 */
@SuppressWarnings("unchecked")
public class PoToOutput<Po extends BasePo,Output extends BaseOutput>{

	/**
	 * po对象转换成output对象
	 * @param po po对象
	 * @param outputCls output class对象
	 * @return Output 对象
	 */
	public Output toOutput(Po po,Class<Output> outputCls) {
		if (po == null) {
			return null;
		}
		try {
			Object obj = outputCls.newInstance();
			BeanUtils.copyProperties(po,obj);
			return (Output)obj;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过json方式拷贝
	 * @param po         源对象
	 * @param outputCls 目标类
	 * @return 拷贝结果
	 */
	public Output jsonOutput(Po po,Class<Output> outputCls) {
		if (po == null) {
			return null;
		}
		try {
			String json = JSON.toJSONString(po);
			Object obj = JSON.parseObject(json,outputCls);
			return (Output)obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * po对象列表转换成output对象列表
	 * @param pos po列表
	 * @param outputCls  output class对象
	 * @return Output列表
	 */
	public List<Output> toListOutput(List<Po> pos,Class<Output> outputCls){
		List<Output> outputs = new ArrayList<>();
		pos.forEach(po -> outputs.add(toOutput(po,outputCls)));
		return outputs;
	}

	/**
	 * 通过json方式转换对象
	 * @param pos   请求列表
	 * @param outputCls 转换类
	 * @return 返回列表
	 */
	public List<Output> jsonListOutput(List<Po> pos,Class<Output> outputCls){
		List<Output> outputs = new ArrayList<>();
		pos.forEach(po -> outputs.add(jsonOutput(po,outputCls)));
		return outputs;
	}
}
