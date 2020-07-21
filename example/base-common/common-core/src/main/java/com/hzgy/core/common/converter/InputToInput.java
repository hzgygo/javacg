package com.hzgy.core.common.converter;

import com.hzgy.core.entity.BaseVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *  input 转成input的工具类
 */
public class InputToInput<InputSrc extends BaseVo,InputTarget extends BaseVo>{

	/**
	 * input对象转换成Input对象
	 * @param inputSrc input对象
	 * @param inputTargetCls input对象类
	 * @return input 对象
	 */
	@SuppressWarnings("unchecked")
	public InputTarget toInput(InputSrc inputSrc,Class<InputTarget> inputTargetCls) {
		if (inputSrc == null) {
			return null;
		}
		try {
			Object obj = inputTargetCls.newInstance();
			BeanUtils.copyProperties(inputSrc,obj);
			return (InputTarget)obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * input列表对象转换成po列表对象
	 * @param inputSrc inputs列表
	 * @param inputTargetCls input class对象
	 * @return input列表
	 */
	public List<InputTarget> toListInput(List<InputSrc> inputSrc,Class<InputTarget> inputTargetCls){
		List<InputTarget> inputTargets = new ArrayList<>();
		inputSrc.forEach(input -> inputTargets.add(toInput(input,inputTargetCls)));
		return inputTargets;
	}
}
