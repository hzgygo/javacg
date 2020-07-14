package ${relativeProjectPath}.${entityPath}.base;

import java.util.List;
import ${relativeProjectPath}.${entityPath}.entity.${entity}Po;
import ${com_jbt_db}.dao.IDao;
import ${com_jbt_core}.exception.DAOException;

public interface IBase${entity}Dao extends IDao{
<#assign idIsExist="false"/>
<#list columns as column>
<#assign property="result"/>
<#if column.code?lower_case == 'id' >
	<#assign property="id"/>
	<#assign idIsExist="true"/>
</#if>
</#list>

	/**
	 * 保存数据
     * @param ${entity?uncap_first}Po 实体bean对象
	 * @return 返回整数结果 1：成功 0：失败
	 * @throws DAOException 异常抛出
     */
	Integer save(${entity}Po ${entity?uncap_first}Po) throws DAOException;

	/**
	* 数据库修改
	* @param ${entity?uncap_first}Po 实体bean对象
	* @return 返回整数结果 1：成功 0：失败
	* @throws DAOException 异常抛出
	*/
	Integer update(${entity}Po ${entity?uncap_first}Po) throws DAOException;
	
	<#if idIsExist == 'true'>
	/**
	 * 数据库修改
     * @param ${entity?uncap_first}Po 实体bean对象
     * @return 返回整数结果 1：成功 0：失败
     * @throws DAOException 异常抛出
     */
	Integer updateById(${entity}Po ${entity?uncap_first}Po) throws DAOException;
	</#if>
	
	/**
	 * 动态条件删除数据
	 * @param ${entity?uncap_first}Po 实体bean对象
	 * @return 返回整数结果 1：成功 0：失败
	 * @throws DAOException 异常抛出
	 */
	Integer removeCriteria(${entity}Po ${entity?uncap_first}Po) throws DAOException;

	<#if idIsExist == 'true'>
	/**
	 * 根据主键ID删除数据
	 * @param id 主键Id
     * @return 返回整数结果 1：成功 0：失败
     * @throws DAOException 异常抛出
     */
	Integer removeById(Long id) throws DAOException;
	</#if>
	
	/**
	 * 删除所有数据
	 * @return void
	 * @throws DAOException 异常抛出
	 */
	Integer removeAll() throws DAOException;

	/**
	 * 动态条件查询列表
	 * @param ${entity?uncap_first}Po 实体bean对象
	 * @return List 返回数据列表
     * @throws DAOException 异常抛出
	 */
	List<${entity}Po> listCriteria(${entity}Po ${entity?uncap_first}Po) throws DAOException;

<#if idIsExist == 'true'>
	/**
	 * 查询所有Id列表
	 * @return List 返回ids数据列表
     * @throws DAOException 异常抛出
	 */
	List<Long> listIdsCriteria(${entity}Po ${entity?uncap_first}Po) throws DAOException;

	/**
	 * 根绝Ids查询列表数据
	 * @return List  返回数据列表
     * @throws DAOException 异常抛出
	 */
	List<${entity}Po> listByInIds(${entity}Po ${entity?uncap_first}Po) throws DAOException;
</#if>
	/**
	 * 查询所有数据
	 * @return List 返回数据列表
     * @throws DAOException 异常抛出
	 */
	List<${entity}Po> listAll() throws DAOException;

	<#if idIsExist == 'true'>
	/**
	 * 查询单条记录
	 * @param id 主键id
	 * @return 返回唯一对象
     * @throws DAOException 异常抛出
	 */
	${entity}Po getOneById(Long id) throws DAOException;
	</#if>
	
	/**
	 * 动态查询单条记录
	 * @param ${entity?uncap_first}Po 实体bean对象
     * @return 返回唯一对象
     * @throws DAOException 异常抛出
	 */
	${entity}Po getOneCriteria(${entity}Po ${entity?uncap_first}Po) throws DAOException;

	/**
	 * 统计记录数
	 * @param ${entity?uncap_first}Po 实体bean对象
     * @return 返回记录数
     * @throws DAOException 异常抛出
	 */
	Long count(${entity}Po ${entity?uncap_first}Po) throws DAOException;

	/**
	 * 自动分页查询列表（PageHelper 插件）
	 * @param ${entity?uncap_first}Po 实体bean对象
	 * @return list 返回自动分页列表
     * @throws DAOException 异常抛出
	 */
	List<${entity}Po> listPaginated(${entity}Po ${entity?uncap_first}Po) throws DAOException;
	
	
	/**
	 * 手动分页查询
	 * @param ${entity?uncap_first}Po 实体bean对象
	 * @return list 返回手动分页列表
     * @throws DAOException 异常抛出
	 */
	List<${entity}Po> listPaginatedManual(${entity}Po ${entity?uncap_first}Po) throws DAOException;
	
	/**
	 * 手动分页查询总记录数
	 * @param ${entity?uncap_first}Po  实体bean对象
     * @throws DAOException 异常抛出
	 */
	Long countPaginatedManual(${entity}Po ${entity?uncap_first}Po) throws DAOException;

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
    * 动态条件关联查询列表
    * @param ${entity?uncap_first}Po 实体bean对象
    * @return List 返回数据列表
    * @throws DAOException 异常抛出
    */
    List<${entity}Po> listCriteria${join?cap_first}${joinTableName?cap_first}(${entity}Po ${entity?uncap_first}Po) throws DAOException;
<#if idIsExist == 'true'>
	/**
    * 根绝Ids关联查询列表数据
    * @return List  返回数据列表
    * @throws DAOException 异常抛出
    */
    List<${entity}Po> listInIds${join?cap_first}${joinTableName?cap_first}(${entity}Po ${entity?uncap_first}Po) throws DAOException;
</#if>
    /**
    * 关联查询所有数据
    * @return List 返回数据列表
    * @throws DAOException 异常抛出
    */
    List<${entity}Po> listAll${join?cap_first}${joinTableName?cap_first}() throws DAOException;

	<#if idIsExist == 'true'>
        /**
        * 关联查询单条记录
        * @param id 主键id
        * @return 返回唯一对象
        * @throws DAOException 异常抛出
        */
	${entity}Po getOneById${join?cap_first}${joinTableName?cap_first}(Long id) throws DAOException;
	</#if>

    /**
    * 动态关联查询单条记录
    * @param ${entity?uncap_first}Po 实体bean对象
    * @return 返回唯一对象
    * @throws DAOException 异常抛出
    */
	${entity}Po getOneCriteria${join?cap_first}${joinTableName?cap_first}(${entity}Po ${entity?uncap_first}Po) throws DAOException;

    /**
    * 自动分页关联查询列表（PageHelper 插件）
    * @param ${entity?uncap_first}Po 实体bean对象
    * @return list 返回自动分页列表
    * @throws DAOException 异常抛出
    */
    List<${entity}Po> listPaginated${join?cap_first}${joinTableName?cap_first}(${entity}Po ${entity?uncap_first}Po) throws DAOException;

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
    * 动态条件关联查询列表
    * @param ${entity?uncap_first}Po 实体bean对象
    * @return List 返回数据列表
    * @throws DAOException 异常抛出
    */
    List<${entity}Po> listCriteria${join?cap_first}${joinTableName?cap_first}(${entity}Po ${entity?uncap_first}Po) throws DAOException;
<#if idIsExist == 'true'>
	/**
    * 根绝Ids关联查询列表数据
    * @return List  返回数据列表
    * @throws DAOException 异常抛出
    */
    List<${entity}Po> listInIds${join?cap_first}${joinTableName?cap_first}(${entity}Po ${entity?uncap_first}Po) throws DAOException;
</#if>
    /**
    * 关联查询所有数据
    * @return List 返回数据列表
    * @throws DAOException 异常抛出
    */
    List<${entity}Po> listAll${join?cap_first}${joinTableName?cap_first}() throws DAOException;

	<#if idIsExist == 'true'>
        /**
        * 关联查询单条记录
        * @param id 主键id
        * @return 返回唯一对象
        * @throws DAOException 异常抛出
        */
	${entity}Po getOneById${join?cap_first}${joinTableName?cap_first}(Long id) throws DAOException;
	</#if>

    /**
    * 动态关联查询单条记录
    * @param ${entity?uncap_first}Po 实体bean对象
    * @return 返回唯一对象
    * @throws DAOException 异常抛出
    */
	${entity}Po getOneCriteria${join?cap_first}${joinTableName?cap_first}(${entity}Po ${entity?uncap_first}Po) throws DAOException;

    /**
    * 自动分页关联查询列表（PageHelper 插件）
    * @param ${entity?uncap_first}Po 实体bean对象
    * @return list 返回自动分页列表
    * @throws DAOException 异常抛出
    */
    List<${entity}Po> listPaginated${join?cap_first}${joinTableName?cap_first}(${entity}Po ${entity?uncap_first}Po) throws DAOException;

</#if>
</#list>
}
