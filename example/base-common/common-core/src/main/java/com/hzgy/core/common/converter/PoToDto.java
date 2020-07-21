package com.hzgy.core.common.converter;

import com.hzgy.core.entity.BaseDto;
import com.hzgy.core.entity.BasePo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * po 转成dto的工具类
 */
@SuppressWarnings("unchecked")
public class PoToDto<Po extends BasePo,Dto extends BaseDto>{

	/**
	 * po对象转换成dto对象
	 * @param po po对象
	 * @param dtoCls dto class对象
	 * @return dto 对象
	 */
	public Dto toDto(Po po,Class<Dto> dtoCls) {
		if (po == null) {
			return null;
		}
		try {
			Object obj = dtoCls.newInstance();
			BeanUtils.copyProperties(po,obj);
			return (Dto)obj;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * po对象列表转换成dto对象列表
	 * @param pos po列表
	 * @param dtoCls dto class对象
	 * @return dto列表
	 */
	public List<Dto> toListDto(List<Po> pos,Class<Dto> dtoCls){
		List<Dto> dtos = new ArrayList<>();
		pos.forEach(po -> dtos.add(toDto(po,dtoCls)));
		return dtos;
	}
}
