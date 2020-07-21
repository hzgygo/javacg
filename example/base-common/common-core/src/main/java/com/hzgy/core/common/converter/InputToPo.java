package com.hzgy.core.common.converter;

import com.hzgy.core.entity.BasePo;
import com.hzgy.core.entity.BaseVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *  input 转成dto的工具类
 */
public class InputToPo<Input extends BaseVo,Po extends BasePo>{

	/**
	 * input对象转换成po对象
	 * @param input input对象
	 * @param poCls po class对象
	 * @return po 对象
	 */
	@SuppressWarnings("unchecked")
	public Po toPo(Input input,Class<Po> poCls) {
		if (input == null) {
			return null;
		}
		try {
			Object obj = poCls.newInstance();
			BeanUtils.copyProperties(input,obj);
			return (Po)obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * input列表对象转换成po列表对象
	 * @param inputs inputs列表
	 * @param poCls po class对象
	 * @return po列表
	 */
	public List<Po> toListPo(List<Input> inputs,Class<Po> poCls){
		List<Po> pos = new ArrayList<>();
		inputs.forEach(input -> pos.add(toPo(input,poCls)));
		return pos;
	}
}
