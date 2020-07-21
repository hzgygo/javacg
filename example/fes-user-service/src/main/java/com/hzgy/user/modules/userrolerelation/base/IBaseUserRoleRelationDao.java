package com.hzgy.user.modules.userrolerelation.base;

import java.util.List;
import com.hzgy.user.modules.userrolerelation.entity.UserRoleRelationPo;
import com.hzgy.db.dao.IDao;
import com.hzgy.core.exception.DAOException;

public interface IBaseUserRoleRelationDao extends IDao{

	/**
	 * 保存数据
     * @param userRoleRelationPo 实体bean对象
	 * @return 返回整数结果 1：成功 0：失败
	 * @throws DAOException 异常抛出
     */
	Integer save(UserRoleRelationPo userRoleRelationPo) throws DAOException;

	/**
	* 数据库修改
	* @param userRoleRelationPo 实体bean对象
	* @return 返回整数结果 1：成功 0：失败
	* @throws DAOException 异常抛出
	*/
	Integer update(UserRoleRelationPo userRoleRelationPo) throws DAOException;
	
	
	/**
	 * 动态条件删除数据
	 * @param userRoleRelationPo 实体bean对象
	 * @return 返回整数结果 1：成功 0：失败
	 * @throws DAOException 异常抛出
	 */
	Integer removeCriteria(UserRoleRelationPo userRoleRelationPo) throws DAOException;

	
	/**
	 * 删除所有数据
	 * @return void
	 * @throws DAOException 异常抛出
	 */
	Integer removeAll() throws DAOException;

	/**
	 * 动态条件查询列表
	 * @param userRoleRelationPo 实体bean对象
	 * @return List 返回数据列表
     * @throws DAOException 异常抛出
	 */
	List<UserRoleRelationPo> listCriteria(UserRoleRelationPo userRoleRelationPo) throws DAOException;

	/**
	 * 查询所有数据
	 * @return List 返回数据列表
     * @throws DAOException 异常抛出
	 */
	List<UserRoleRelationPo> listAll() throws DAOException;

	
	/**
	 * 动态查询单条记录
	 * @param userRoleRelationPo 实体bean对象
     * @return 返回唯一对象
     * @throws DAOException 异常抛出
	 */
	UserRoleRelationPo getOneCriteria(UserRoleRelationPo userRoleRelationPo) throws DAOException;

	/**
	 * 统计记录数
	 * @param userRoleRelationPo 实体bean对象
     * @return 返回记录数
     * @throws DAOException 异常抛出
	 */
	Long count(UserRoleRelationPo userRoleRelationPo) throws DAOException;

	/**
	 * 自动分页查询列表（PageHelper 插件）
	 * @param userRoleRelationPo 实体bean对象
	 * @return list 返回自动分页列表
     * @throws DAOException 异常抛出
	 */
	List<UserRoleRelationPo> listPaginated(UserRoleRelationPo userRoleRelationPo) throws DAOException;
	
	
	/**
	 * 手动分页查询
	 * @param userRoleRelationPo 实体bean对象
	 * @return list 返回手动分页列表
     * @throws DAOException 异常抛出
	 */
	List<UserRoleRelationPo> listPaginatedManual(UserRoleRelationPo userRoleRelationPo) throws DAOException;
	
	/**
	 * 手动分页查询总记录数
	 * @param userRoleRelationPo  实体bean对象
     * @throws DAOException 异常抛出
	 */
	Long countPaginatedManual(UserRoleRelationPo userRoleRelationPo) throws DAOException;

    /**
    * 动态条件关联查询列表
    * @param userRoleRelationPo 实体bean对象
    * @return List 返回数据列表
    * @throws DAOException 异常抛出
    */
    List<UserRoleRelationPo> listCriteriaInnerRole(UserRoleRelationPo userRoleRelationPo) throws DAOException;
    /**
    * 关联查询所有数据
    * @return List 返回数据列表
    * @throws DAOException 异常抛出
    */
    List<UserRoleRelationPo> listAllInnerRole() throws DAOException;


    /**
    * 动态关联查询单条记录
    * @param userRoleRelationPo 实体bean对象
    * @return 返回唯一对象
    * @throws DAOException 异常抛出
    */
	UserRoleRelationPo getOneCriteriaInnerRole(UserRoleRelationPo userRoleRelationPo) throws DAOException;

    /**
    * 自动分页关联查询列表（PageHelper 插件）
    * @param userRoleRelationPo 实体bean对象
    * @return list 返回自动分页列表
    * @throws DAOException 异常抛出
    */
    List<UserRoleRelationPo> listPaginatedInnerRole(UserRoleRelationPo userRoleRelationPo) throws DAOException;

    /**
    * 动态条件关联查询列表
    * @param userRoleRelationPo 实体bean对象
    * @return List 返回数据列表
    * @throws DAOException 异常抛出
    */
    List<UserRoleRelationPo> listCriteriaInnerUser(UserRoleRelationPo userRoleRelationPo) throws DAOException;
    /**
    * 关联查询所有数据
    * @return List 返回数据列表
    * @throws DAOException 异常抛出
    */
    List<UserRoleRelationPo> listAllInnerUser() throws DAOException;


    /**
    * 动态关联查询单条记录
    * @param userRoleRelationPo 实体bean对象
    * @return 返回唯一对象
    * @throws DAOException 异常抛出
    */
	UserRoleRelationPo getOneCriteriaInnerUser(UserRoleRelationPo userRoleRelationPo) throws DAOException;

    /**
    * 自动分页关联查询列表（PageHelper 插件）
    * @param userRoleRelationPo 实体bean对象
    * @return list 返回自动分页列表
    * @throws DAOException 异常抛出
    */
    List<UserRoleRelationPo> listPaginatedInnerUser(UserRoleRelationPo userRoleRelationPo) throws DAOException;

}
