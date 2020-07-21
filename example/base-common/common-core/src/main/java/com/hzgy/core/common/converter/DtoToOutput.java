package com.hzgy.core.common.converter;

import com.hzgy.core.entity.BaseDto;
import com.hzgy.core.entity.BaseOutput;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * dto 转成output的工具类
 */
@SuppressWarnings("unchecked")
public class DtoToOutput<Dto extends BaseDto,Output extends BaseOutput>{

	/**
	 * dto to output
	 * @param dto dto对象
	 * @param outputCls output class对象
	 * @return dto 对象
	 */
	public Output toOutput(Dto dto,Class<Output> outputCls) {
		if (dto == null) {
			return null;
		}
		try {
			Object obj = outputCls.newInstance();
			BeanUtils.copyProperties(dto,obj);
			return (Output)obj;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * dto列表转换成output列表
	 * @param dtos dtos列表
	 * @param outputCls output class对象
	 * @return output列表
	 */
	public List<Output> toListOutput(List<Dto> dtos,Class<Output> outputCls){
		List<Output> outputs = new ArrayList<>();
		dtos.forEach(dto -> outputs.add(toOutput(dto,outputCls)));
		return outputs;
	}

}
