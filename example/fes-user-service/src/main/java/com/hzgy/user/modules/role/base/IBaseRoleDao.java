package com.hzgy.user.modules.role.base;

import java.util.List;
import com.hzgy.user.modules.role.entity.RolePo;
import com.hzgy.db.dao.IDao;
import com.hzgy.core.exception.DAOException;

public interface IBaseRoleDao extends IDao{

	/**
	 * 保存数据
     * @param rolePo 实体bean对象
	 * @return 返回整数结果 1：成功 0：失败
	 * @throws DAOException 异常抛出
     */
	Integer save(RolePo rolePo) throws DAOException;

	/**
	* 数据库修改
	* @param rolePo 实体bean对象
	* @return 返回整数结果 1：成功 0：失败
	* @throws DAOException 异常抛出
	*/
	Integer update(RolePo rolePo) throws DAOException;
	
	/**
	 * 数据库修改
     * @param rolePo 实体bean对象
     * @return 返回整数结果 1：成功 0：失败
     * @throws DAOException 异常抛出
     */
	Integer updateById(RolePo rolePo) throws DAOException;
	
	/**
	 * 动态条件删除数据
	 * @param rolePo 实体bean对象
	 * @return 返回整数结果 1：成功 0：失败
	 * @throws DAOException 异常抛出
	 */
	Integer removeCriteria(RolePo rolePo) throws DAOException;

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
	 * @param rolePo 实体bean对象
	 * @return List 返回数据列表
     * @throws DAOException 异常抛出
	 */
	List<RolePo> listCriteria(RolePo rolePo) throws DAOException;

	/**
	 * 查询所有Id列表
	 * @return List 返回ids数据列表
     * @throws DAOException 异常抛出
	 */
	List<Long> listIdsCriteria(RolePo rolePo) throws DAOException;

	/**
	 * 根绝Ids查询列表数据
	 * @return List  返回数据列表
     * @throws DAOException 异常抛出
	 */
	List<RolePo> listByInIds(RolePo rolePo) throws DAOException;
	/**
	 * 查询所有数据
	 * @return List 返回数据列表
     * @throws DAOException 异常抛出
	 */
	List<RolePo> listAll() throws DAOException;

	/**
	 * 查询单条记录
	 * @param id 主键id
	 * @return 返回唯一对象
     * @throws DAOException 异常抛出
	 */
	RolePo getOneById(Long id) throws DAOException;
	
	/**
	 * 动态查询单条记录
	 * @param rolePo 实体bean对象
     * @return 返回唯一对象
     * @throws DAOException 异常抛出
	 */
	RolePo getOneCriteria(RolePo rolePo) throws DAOException;

	/**
	 * 统计记录数
	 * @param rolePo 实体bean对象
     * @return 返回记录数
     * @throws DAOException 异常抛出
	 */
	Long count(RolePo rolePo) throws DAOException;

	/**
	 * 自动分页查询列表（PageHelper 插件）
	 * @param rolePo 实体bean对象
	 * @return list 返回自动分页列表
     * @throws DAOException 异常抛出
	 */
	List<RolePo> listPaginated(RolePo rolePo) throws DAOException;
	
	
	/**
	 * 手动分页查询
	 * @param rolePo 实体bean对象
	 * @return list 返回手动分页列表
     * @throws DAOException 异常抛出
	 */
	List<RolePo> listPaginatedManual(RolePo rolePo) throws DAOException;
	
	/**
	 * 手动分页查询总记录数
	 * @param rolePo  实体bean对象
     * @throws DAOException 异常抛出
	 */
	Long countPaginatedManual(RolePo rolePo) throws DAOException;

    /**
    * 动态条件关联查询列表
    * @param rolePo 实体bean对象
    * @return List 返回数据列表
    * @throws DAOException 异常抛出
    */
    List<RolePo> listCriteriaLeftRolePermitRelation(RolePo rolePo) throws DAOException;
	/**
    * 根绝Ids关联查询列表数据
    * @return List  返回数据列表
    * @throws DAOException 异常抛出
    */
    List<RolePo> listInIdsLeftRolePermitRelation(RolePo rolePo) throws DAOException;
    /**
    * 关联查询所有数据
    * @return List 返回数据列表
    * @throws DAOException 异常抛出
    */
    List<RolePo> listAllLeftRolePermitRelation() throws DAOException;

        /**
        * 关联查询单条记录
        * @param id 主键id
        * @return 返回唯一对象
        * @throws DAOException 异常抛出
        */
	RolePo getOneByIdLeftRolePermitRelation(Long id) throws DAOException;

    /**
    * 动态关联查询单条记录
    * @param rolePo 实体bean对象
    * @return 返回唯一对象
    * @throws DAOException 异常抛出
    */
	RolePo getOneCriteriaLeftRolePermitRelation(RolePo rolePo) throws DAOException;

    /**
    * 自动分页关联查询列表（PageHelper 插件）
    * @param rolePo 实体bean对象
    * @return list 返回自动分页列表
    * @throws DAOException 异常抛出
    */
    List<RolePo> listPaginatedLeftRolePermitRelation(RolePo rolePo) throws DAOException;

    /**
    * 动态条件关联查询列表
    * @param rolePo 实体bean对象
    * @return List 返回数据列表
    * @throws DAOException 异常抛出
    */
    List<RolePo> listCriteriaLeftUserRoleRelation(RolePo rolePo) throws DAOException;
	/**
    * 根绝Ids关联查询列表数据
    * @return List  返回数据列表
    * @throws DAOException 异常抛出
    */
    List<RolePo> listInIdsLeftUserRoleRelation(RolePo rolePo) throws DAOException;
    /**
    * 关联查询所有数据
    * @return List 返回数据列表
    * @throws DAOException 异常抛出
    */
    List<RolePo> listAllLeftUserRoleRelation() throws DAOException;

        /**
        * 关联查询单条记录
        * @param id 主键id
        * @return 返回唯一对象
        * @throws DAOException 异常抛出
        */
	RolePo getOneByIdLeftUserRoleRelation(Long id) throws DAOException;

    /**
    * 动态关联查询单条记录
    * @param rolePo 实体bean对象
    * @return 返回唯一对象
    * @throws DAOException 异常抛出
    */
	RolePo getOneCriteriaLeftUserRoleRelation(RolePo rolePo) throws DAOException;

    /**
    * 自动分页关联查询列表（PageHelper 插件）
    * @param rolePo 实体bean对象
    * @return list 返回自动分页列表
    * @throws DAOException 异常抛出
    */
    List<RolePo> listPaginatedLeftUserRoleRelation(RolePo rolePo) throws DAOException;

}
