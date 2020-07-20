package ${relativeProjectPath}.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import ${path_core}.entity.BaseVo;
import ${path_core}.exception.base.BaseException;

public interface IBase${entity}<Vo extends BaseVo>{
<#assign idIsExist="false"/>
<#list columns as column>
<#assign property="result"/>
<#if column.code?lower_case == 'id' >
	<#assign property="id"/>
	<#assign idIsExist="true"/>
</#if>
</#list>

	/**
	 * 保存
	 * @param dto dto参数对象
	 * @return Integer
	 */
	Integer save(Dto dto) throws BaseException;

	<#if idIsExist == 'true'>
	/**
	 * 根据id修改
	 * @param dto dto参数对象
	 * @return Integer
	 */
	Integer updateById(Dto dto) throws BaseException;
	</#if>
	
	/**
	 * 动态条件修改数据
	 * @param dto dto对象
	 * @return Integer
	 */
    Integer updateCriteria(Dto dto) throws BaseException;
	
	/**
	 * 动态条件删除
	 * @param dto dto参数对象
	 * @return Integer
	 */
	Integer removeCriteria(Dto dto) throws BaseException;
	
	<#if idIsExist == 'true'>
	/**
	 * 根据Id删除数据
	 * @param id 主键id
	 * @return Integer
	 */
	Integer removeById(Long id) throws BaseException;
	</#if>

	/**
	 * 删除所有数据
	 * @return Integer
	 */
    Integer removeAll() throws BaseException;
	
	/**
	 * 动态条件查询列表数据
	 * @param dto dto参数对象
	 * @return List<Dto>
	 */
	List<Dto> listCriteria(Dto dto) throws BaseException;

	/**
	 * 动态条件查询主键Id数据
	 * @param dto dto参数对象
	 * @return List<Long>
	 */
	List<Long> listIdsCriteria(Dto dto) throws BaseException;

	/**
	 * 根据id串，in的方式查询数据
	 * @param dto dto参数对象
	 * @return List<Dto>
	 */
	List<Dto> listByInIds(Dto dto) throws BaseException;

	/**
	 * 查询所有数据
	 * @return List<Dto>
	 */
	List<Dto> listAll() throws BaseException;
	
	<#if idIsExist == 'true'>
	/**
	 * 根据id，获取单条数据
	 * @param id 主键id
	 * @return Dto
	 */
	Dto getOneById(Long id) throws BaseException;
	</#if>

	/**
	 * 动态条件，获取单条数据
	 * @param dto dto对象
	 * @return Dto
	 */
	Dto getOneCriteria(Dto dto) throws BaseException;

	/**
	 * 动态条件，查询记录总数
	 * @param dto dto参数对象
	 * @return Long
	 */
	Long count(Dto dto) throws BaseException;

	/**
	 * 动态条件，自动分页查询列表数据
	 * @param  dto dto参数对象
	 * @return PageInfo<Dto>
	 */
	PageInfo<Dto> listPaginated(Dto dto) throws BaseException;

	/**
	 * 动态条件，手动分页查询列表数据
	 * @param dto dto参数对象
	 * @return PageInfo<Dto>
	 */
	public PageInfo<Dto> listPaginatedManual(Dto  dto) throws BaseException;
	
}
