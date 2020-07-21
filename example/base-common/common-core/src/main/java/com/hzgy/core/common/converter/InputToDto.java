package com.hzgy.core.common.converter;

import com.hzgy.core.entity.BaseDto;
import com.hzgy.core.entity.BaseInput;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *  input 转成dto的工具类
 */
public class InputToDto<Input extends BaseInput,Dto extends BaseDto>{

	/**
	 * input对象转换成dto对象
	 * @param input input对象
	 * @param dtoCls dto class对象
	 * @return dto 对象
	 */
	@SuppressWarnings("unchecked")
	public Dto toDto(Input input,Class<Dto> dtoCls) {
		if (input == null) {
			return null;
		}
		try {
			Object obj = dtoCls.newInstance();
			BeanUtils.copyProperties(input,obj);
			return (Dto)obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * input列表对象转换成dto列表对象
	 * @param inputs inputs列表
	 * @param dtoCls dto class对象
	 * @return dto列表
	 */
	public List<Dto> toListDto(List<Input> inputs,Class<Dto> dtoCls){
		List<Dto> dtos = new ArrayList<>();
		inputs.forEach(input -> dtos.add(toDto(input,dtoCls)));
		return dtos;
	}
}
