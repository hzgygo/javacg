package com.hzgy.user.modules.rolepermitrelation.base;

import java.util.List;
import com.hzgy.user.modules.rolepermitrelation.entity.RolePermitRelationPo;
import com.hzgy.db.dao.IDao;
import com.hzgy.core.exception.DAOException;

public interface IBaseRolePermitRelationDao extends IDao{

	/**
	 * 保存数据
     * @param rolePermitRelationPo 实体bean对象
	 * @return 返回整数结果 1：成功 0：失败
	 * @throws DAOException 异常抛出
     */
	Integer save(RolePermitRelationPo rolePermitRelationPo) throws DAOException;

	/**
	* 数据库修改
	* @param rolePermitRelationPo 实体bean对象
	* @return 返回整数结果 1：成功 0：失败
	* @throws DAOException 异常抛出
	*/
	Integer update(RolePermitRelationPo rolePermitRelationPo) throws DAOException;
	
	
	/**
	 * 动态条件删除数据
	 * @param rolePermitRelationPo 实体bean对象
	 * @return 返回整数结果 1：成功 0：失败
	 * @throws DAOException 异常抛出
	 */
	Integer removeCriteria(RolePermitRelationPo rolePermitRelationPo) throws DAOException;

	
	/**
	 * 删除所有数据
	 * @return void
	 * @throws DAOException 异常抛出
	 */
	Integer removeAll() throws DAOException;

	/**
	 * 动态条件查询列表
	 * @param rolePermitRelationPo 实体bean对象
	 * @return List 返回数据列表
     * @throws DAOException 异常抛出
	 */
	List<RolePermitRelationPo> listCriteria(RolePermitRelationPo rolePermitRelationPo) throws DAOException;

	/**
	 * 查询所有数据
	 * @return List 返回数据列表
     * @throws DAOException 异常抛出
	 */
	List<RolePermitRelationPo> listAll() throws DAOException;

	
	/**
	 * 动态查询单条记录
	 * @param rolePermitRelationPo 实体bean对象
     * @return 返回唯一对象
     * @throws DAOException 异常抛出
	 */
	RolePermitRelationPo getOneCriteria(RolePermitRelationPo rolePermitRelationPo) throws DAOException;

	/**
	 * 统计记录数
	 * @param rolePermitRelationPo 实体bean对象
     * @return 返回记录数
     * @throws DAOException 异常抛出
	 */
	Long count(RolePermitRelationPo rolePermitRelationPo) throws DAOException;

	/**
	 * 自动分页查询列表（PageHelper 插件）
	 * @param rolePermitRelationPo 实体bean对象
	 * @return list 返回自动分页列表
     * @throws DAOException 异常抛出
	 */
	List<RolePermitRelationPo> listPaginated(RolePermitRelationPo rolePermitRelationPo) throws DAOException;
	
	
	/**
	 * 手动分页查询
	 * @param rolePermitRelationPo 实体bean对象
	 * @return list 返回手动分页列表
     * @throws DAOException 异常抛出
	 */
	List<RolePermitRelationPo> listPaginatedManual(RolePermitRelationPo rolePermitRelationPo) throws DAOException;
	
	/**
	 * 手动分页查询总记录数
	 * @param rolePermitRelationPo  实体bean对象
     * @throws DAOException 异常抛出
	 */
	Long countPaginatedManual(RolePermitRelationPo rolePermitRelationPo) throws DAOException;

    /**
    * 动态条件关联查询列表
    * @param rolePermitRelationPo 实体bean对象
    * @return List 返回数据列表
    * @throws DAOException 异常抛出
    */
    List<RolePermitRelationPo> listCriteriaInnerRole(RolePermitRelationPo rolePermitRelationPo) throws DAOException;
    /**
    * 关联查询所有数据
    * @return List 返回数据列表
    * @throws DAOException 异常抛出
    */
    List<RolePermitRelationPo> listAllInnerRole() throws DAOException;


    /**
    * 动态关联查询单条记录
    * @param rolePermitRelationPo 实体bean对象
    * @return 返回唯一对象
    * @throws DAOException 异常抛出
    */
	RolePermitRelationPo getOneCriteriaInnerRole(RolePermitRelationPo rolePermitRelationPo) throws DAOException;

    /**
    * 自动分页关联查询列表（PageHelper 插件）
    * @param rolePermitRelationPo 实体bean对象
    * @return list 返回自动分页列表
    * @throws DAOException 异常抛出
    */
    List<RolePermitRelationPo> listPaginatedInnerRole(RolePermitRelationPo rolePermitRelationPo) throws DAOException;

    /**
    * 动态条件关联查询列表
    * @param rolePermitRelationPo 实体bean对象
    * @return List 返回数据列表
    * @throws DAOException 异常抛出
    */
    List<RolePermitRelationPo> listCriteriaInnerPermission(RolePermitRelationPo rolePermitRelationPo) throws DAOException;
    /**
    * 关联查询所有数据
    * @return List 返回数据列表
    * @throws DAOException 异常抛出
    */
    List<RolePermitRelationPo> listAllInnerPermission() throws DAOException;


    /**
    * 动态关联查询单条记录
    * @param rolePermitRelationPo 实体bean对象
    * @return 返回唯一对象
    * @throws DAOException 异常抛出
    */
	RolePermitRelationPo getOneCriteriaInnerPermission(RolePermitRelationPo rolePermitRelationPo) throws DAOException;

    /**
    * 自动分页关联查询列表（PageHelper 插件）
    * @param rolePermitRelationPo 实体bean对象
    * @return list 返回自动分页列表
    * @throws DAOException 异常抛出
    */
    List<RolePermitRelationPo> listPaginatedInnerPermission(RolePermitRelationPo rolePermitRelationPo) throws DAOException;

}
