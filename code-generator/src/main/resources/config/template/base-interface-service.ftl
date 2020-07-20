package ${relativeProjectPath}.${entityPath}.base;

import java.util.List;

import com.github.pagehelper.PageInfo;
import ${path_core}.entity.BaseInput;
import ${path_core}.entity.BaseOutput;
import ${path_core}.entity.ResultData;
import ${path_core}.service.IService;
import ${path_core}.exception.base.BaseException;

public interface IBase${entity}Service<Input extends BaseInput,Output extends BaseOutput> extends IService{
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
	 * @param input input参数对象
	 * @return ResultData<Integer>
	 */
	ResultData<Integer> create(Input input) throws BaseException;

	<#if idIsExist == 'true'>
	/**
	 * 根据id修改
	 * @param input input参数对象
	 * @return ResultData<Integer>
	 */
	ResultData<Integer> modifyById(Input input) throws BaseException;
	</#if>
	
	/**
	 * 动态条件删除
	 * @param input input参数对象
	 * @return ResultData<Integer>
	 */
	ResultData<Integer> deleteCriteria(Input input) throws BaseException;
	
	<#if idIsExist == 'true'>
	/**
	 * 根据Id删除数据
	 * @param id 主键id
	 * @return ResultData<Integer>
	 */
	ResultData<Integer> deleteById(Long id) throws BaseException;
	</#if>

	/**
	 * 删除所有数据
	 * @return ResultData<Integer>
	 */
	ResultData<Integer> deleteAll() throws BaseException;
	
	/**
	 * 动态条件查询列表数据
	 * @param input input参数对象
	 * @return ResultData<List<Output>>
	 */
	ResultData<List<Output>> listCriteria(Input input) throws BaseException;
<#if idIsExist == 'true'>
	/**
	 * 动态条件查询主键Id数据
	 * @param input input参数对象
	 * @return ResultData<List<Long>>
	 */
	ResultData<List<Long>> listIdsCriteria(Input input) throws BaseException;

	/**
	 * 根据id串，in的方式查询数据
	 * @param input input参数对象
	 * @return ResultData<List<Output>>
	 */
	ResultData<List<Output>> listInIds(Input input) throws BaseException;
</#if>
	/**
	 * 查询所有数据
	 * @return ResultData<List<Output>>
	 */
	ResultData<List<Output>> listAll() throws BaseException;
	
	<#if idIsExist == 'true'>
	/**
	 * 根据id，获取单条数据
	 * @param id 主键id
	 * @return ResultData<Output>
	 */
	ResultData<Output> getOneById(Long id) throws BaseException;
	</#if>

	/**
	 * 动态条件，获取单条数据
	 * @param input input对象
	 * @return ResultData<Output>
	 */
	ResultData<Output> getOne(Input input) throws BaseException;

	/**
	 * 动态条件，查询记录总数
	 * @param input input参数对象
	 * @return ResultData<Long>
	 */
	ResultData<Long> count(Input input) throws BaseException;

	/**
	 * 动态条件，自动分页查询列表数据
	 * @param  input input参数对象
	 * @return ResultData<PageInfo<Output>>
	 */
	ResultData<PageInfo> listPaginated(Input input) throws BaseException;

	/**
	 * 动态条件，手动分页查询列表数据
	 * @param input input参数对象
	 * @return ResultData<PageInfo<Output>>
	 */
	ResultData<PageInfo<Output>> listPaginatedManual(Input input) throws BaseException;


<#list references as reference>
	<#assign join=""/>
	<#assign joinTableName=""/>
	<#assign isJoin="false"/>
	<#if table.code == reference.parentTable.code>
	<#assign join="left"/>
	<#assign isJoin="true"/>
	<#assign ctOriginalCode="${reference.childTable.originalCode}"/>
	<#assign ctCode="${reference.childTable.code}"/>
	<#assign ctIndex="${ctCode?index_of('_')}"/>
	<#assign ctLength="${ctCode?length}"/>
	<#assign joinTableName="${ctCode?substring(ctIndex?number+1,ctLength?number)?uncap_first}"/>
	/**
	* 动态条件关联查询列表数据
	* @param input input参数对象
	* @return ResultData<List<Output>>
	*/
	ResultData<List<Output>> listCriteria${join?cap_first}${joinTableName?cap_first}(Input input) throws BaseException;
<#if idIsExist == 'true'>
	/**
	* 根据id串，in的方式关联查询数据
	* @param input input参数对象
	* @return ResultData<List<Output>>
	*/
	ResultData<List<Output>> listInIds${join?cap_first}${joinTableName?cap_first}(Input input) throws BaseException;
</#if>
	/**
	* 关联查询所有数据
	* @return ResultData<List<Output>>
	*/
	ResultData<List<Output>> listAll${join?cap_first}${joinTableName?cap_first}() throws BaseException;

	<#if idIsExist == 'true'>
	/**
	* 根据id，关联获取单条数据
	* @param id 主键id
	* @return ResultData<Output>
	*/
	ResultData<Output> getOneById${join?cap_first}${joinTableName?cap_first}(Long id) throws BaseException;
	</#if>

	/**
	* 动态条件，关联获取单条数据
	* @param input input对象
	* @return ResultData<Output>
	*/
	ResultData<Output> getOne${join?cap_first}${joinTableName?cap_first}(Input input) throws BaseException;

	/**
	* 动态条件，自动分页关联查询列表数据
	* @param  input input参数对象
	* @return ResultData<PageInfo<Output>>
	*/
	ResultData<PageInfo> listPaginated${join?cap_first}${joinTableName?cap_first}(Input input) throws BaseException;
	</#if>
	<#if table.code == reference.childTable.code>
    <#assign join="inner"/>
    <#assign isJoin="true"/>
    <#assign ctOriginalCode="${reference.parentTable.originalCode}"/>
    <#assign ctCode="${reference.parentTable.code}"/>
    <#assign ctIndex="${ctCode?index_of('_')}"/>
    <#assign ctLength="${ctCode?length}"/>
    <#assign joinTableName="${ctCode?substring(ctIndex?number+1,ctLength?number)?uncap_first}"/>
    /**
    * 动态条件关联查询列表数据
    * @param input input参数对象
    * @return ResultData<List<Output>>
    */
    ResultData<List<Output>> listCriteria${join?cap_first}${joinTableName?cap_first}(Input input) throws BaseException;
    <#if idIsExist == 'true'>
    /**
    * 根据id串，in的方式关联查询数据
    * @param input input参数对象
    * @return ResultData<List<Output>>
    */
    ResultData<List<Output>> listInIds${join?cap_first}${joinTableName?cap_first}(Input input) throws BaseException;
    </#if>
    /**
    * 关联查询所有数据
    * @return ResultData<List<Output>>
    */
    ResultData<List<Output>> listAll${join?cap_first}${joinTableName?cap_first}() throws BaseException;

    <#if idIsExist == 'true'>
    /**
    * 根据id，关联获取单条数据
    * @param id 主键id
    * @return ResultData<Output>
    */
    ResultData<Output> getOneById${join?cap_first}${joinTableName?cap_first}(Long id) throws BaseException;
    </#if>

    /**
    * 动态条件，关联获取单条数据
    * @param input input对象
    * @return ResultData<Output>
    */
    ResultData<Output> getOne${join?cap_first}${joinTableName?cap_first}(Input input) throws BaseException;

    /**
    * 动态条件，自动分页关联查询列表数据
    * @param  input input参数对象
    * @return ResultData<PageInfo<Output>>
    */
    ResultData<PageInfo> listPaginated${join?cap_first}${joinTableName?cap_first}(Input input) throws BaseException;
	</#if>

</#list>
}
