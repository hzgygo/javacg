package com.hzgy.core.common.converter;

import com.hzgy.core.entity.BaseDto;
import com.hzgy.core.entity.BasePo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * dto 转成po的工具类
 */
@SuppressWarnings("unchecked")
public class DtoToPo<Dto extends BaseDto,Po extends BasePo>{

	/**
	 * dto对象转换成po对象
	 * @param dto dto对象
	 * @param poCls po class对象
	 * @return dto 对象
	 */

	public Po toPo(Dto dto,Class<Po> poCls) {
		if (dto == null) {
			return null;
		}
		try {
			Object obj = poCls.newInstance();
			BeanUtils.copyProperties(dto,obj);
			return (Po)obj;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * dto列表转换成po列表
	 * @param dtos po列表
	 * @param poCls po class对象
	 * @return dto列表
	 */
	public List<Po> toListPo( List<Dto> dtos,Class<Po> poCls){
		List<Po> pos = new ArrayList<>();
		dtos.forEach(dto -> pos.add(toPo(dto,poCls)));
		return pos;
	}
}
