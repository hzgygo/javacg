package com.hzgy.user.modules.permission.base;

import java.util.List;
import com.hzgy.user.modules.permission.entity.PermissionPo;
import com.hzgy.db.dao.IDao;
import com.hzgy.core.exception.DAOException;

public interface IBasePermissionDao extends IDao{

	/**
	 * 保存数据
     * @param permissionPo 实体bean对象
	 * @return 返回整数结果 1：成功 0：失败
	 * @throws DAOException 异常抛出
     */
	Integer save(PermissionPo permissionPo) throws DAOException;

	/**
	* 数据库修改
	* @param permissionPo 实体bean对象
	* @return 返回整数结果 1：成功 0：失败
	* @throws DAOException 异常抛出
	*/
	Integer update(PermissionPo permissionPo) throws DAOException;
	
	/**
	 * 数据库修改
     * @param permissionPo 实体bean对象
     * @return 返回整数结果 1：成功 0：失败
     * @throws DAOException 异常抛出
     */
	Integer updateById(PermissionPo permissionPo) throws DAOException;
	
	/**
	 * 动态条件删除数据
	 * @param permissionPo 实体bean对象
	 * @return 返回整数结果 1：成功 0：失败
	 * @throws DAOException 异常抛出
	 */
	Integer removeCriteria(PermissionPo permissionPo) throws DAOException;

	/**
	 * 根据主键ID删除数据
	 * @param id 主键Id
     * @return 返回整数结果 1：成功 0：失败
     * @throws DAOException 异常抛出
     */
	Integer removeById(Long id) throws DAOException;
	
	/**
	 * 删除所有数据
	 * @return void
	 * @throws DAOException 异常抛出
	 */
	Integer removeAll() throws DAOException;

	/**
	 * 动态条件查询列表
	 * @param permissionPo 实体bean对象
	 * @return List 返回数据列表
     * @throws DAOException 异常抛出
	 */
	List<PermissionPo> listCriteria(PermissionPo permissionPo) throws DAOException;

	/**
	 * 查询所有Id列表
	 * @return List 返回ids数据列表
     * @throws DAOException 异常抛出
	 */
	List<Long> listIdsCriteria(PermissionPo permissionPo) throws DAOException;

	/**
	 * 根绝Ids查询列表数据
	 * @return List  返回数据列表
     * @throws DAOException 异常抛出
	 */
	List<PermissionPo> listByInIds(PermissionPo permissionPo) throws DAOException;
	/**
	 * 查询所有数据
	 * @return List 返回数据列表
     * @throws DAOException 异常抛出
	 */
	List<PermissionPo> listAll() throws DAOException;

	/**
	 * 查询单条记录
	 * @param id 主键id
	 * @return 返回唯一对象
     * @throws DAOException 异常抛出
	 */
	PermissionPo getOneById(Long id) throws DAOException;
	
	/**
	 * 动态查询单条记录
	 * @param permissionPo 实体bean对象
     * @return 返回唯一对象
     * @throws DAOException 异常抛出
	 */
	PermissionPo getOneCriteria(PermissionPo permissionPo) throws DAOException;

	/**
	 * 统计记录数
	 * @param permissionPo 实体bean对象
     * @return 返回记录数
     * @throws DAOException 异常抛出
	 */
	Long count(PermissionPo permissionPo) throws DAOException;

	/**
	 * 自动分页查询列表（PageHelper 插件）
	 * @param permissionPo 实体bean对象
	 * @return list 返回自动分页列表
     * @throws DAOException 异常抛出
	 */
	List<PermissionPo> listPaginated(PermissionPo permissionPo) throws DAOException;
	
	
	/**
	 * 手动分页查询
	 * @param permissionPo 实体bean对象
	 * @return list 返回手动分页列表
     * @throws DAOException 异常抛出
	 */
	List<PermissionPo> listPaginatedManual(PermissionPo permissionPo) throws DAOException;
	
	/**
	 * 手动分页查询总记录数
	 * @param permissionPo  实体bean对象
     * @throws DAOException 异常抛出
	 */
	Long countPaginatedManual(PermissionPo permissionPo) throws DAOException;

    /**
    * 动态条件关联查询列表
    * @param permissionPo 实体bean对象
    * @return List 返回数据列表
    * @throws DAOException 异常抛出
    */
    List<PermissionPo> listCriteriaLeftRolePermitRelation(PermissionPo permissionPo) throws DAOException;
	/**
    * 根绝Ids关联查询列表数据
    * @return List  返回数据列表
    * @throws DAOException 异常抛出
    */
    List<PermissionPo> listInIdsLeftRolePermitRelation(PermissionPo permissionPo) throws DAOException;
    /**
    * 关联查询所有数据
    * @return List 返回数据列表
    * @throws DAOException 异常抛出
    */
    List<PermissionPo> listAllLeftRolePermitRelation() throws DAOException;

        /**
        * 关联查询单条记录
        * @param id 主键id
        * @return 返回唯一对象
        * @throws DAOException 异常抛出
        */
	PermissionPo getOneByIdLeftRolePermitRelation(Long id) throws DAOException;

    /**
    * 动态关联查询单条记录
    * @param permissionPo 实体bean对象
    * @return 返回唯一对象
    * @throws DAOException 异常抛出
    */
	PermissionPo getOneCriteriaLeftRolePermitRelation(PermissionPo permissionPo) throws DAOException;

    /**
    * 自动分页关联查询列表（PageHelper 插件）
    * @param permissionPo 实体bean对象
    * @return list 返回自动分页列表
    * @throws DAOException 异常抛出
    */
    List<PermissionPo> listPaginatedLeftRolePermitRelation(PermissionPo permissionPo) throws DAOException;

}
