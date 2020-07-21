package com.hzgy.user.modules.user.base;

import java.util.List;
import com.hzgy.user.modules.user.entity.UserPo;
import com.hzgy.db.dao.IDao;
import com.hzgy.core.exception.DAOException;

public interface IBaseUserDao extends IDao{

	/**
	 * 保存数据
     * @param userPo 实体bean对象
	 * @return 返回整数结果 1：成功 0：失败
	 * @throws DAOException 异常抛出
     */
	Integer save(UserPo userPo) throws DAOException;

	/**
	* 数据库修改
	* @param userPo 实体bean对象
	* @return 返回整数结果 1：成功 0：失败
	* @throws DAOException 异常抛出
	*/
	Integer update(UserPo userPo) throws DAOException;
	
	/**
	 * 数据库修改
     * @param userPo 实体bean对象
     * @return 返回整数结果 1：成功 0：失败
     * @throws DAOException 异常抛出
     */
	Integer updateById(UserPo userPo) throws DAOException;
	
	/**
	 * 动态条件删除数据
	 * @param userPo 实体bean对象
	 * @return 返回整数结果 1：成功 0：失败
	 * @throws DAOException 异常抛出
	 */
	Integer removeCriteria(UserPo userPo) throws DAOException;

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
	 * @param userPo 实体bean对象
	 * @return List 返回数据列表
     * @throws DAOException 异常抛出
	 */
	List<UserPo> listCriteria(UserPo userPo) throws DAOException;

	/**
	 * 查询所有Id列表
	 * @return List 返回ids数据列表
     * @throws DAOException 异常抛出
	 */
	List<Long> listIdsCriteria(UserPo userPo) throws DAOException;

	/**
	 * 根绝Ids查询列表数据
	 * @return List  返回数据列表
     * @throws DAOException 异常抛出
	 */
	List<UserPo> listByInIds(UserPo userPo) throws DAOException;
	/**
	 * 查询所有数据
	 * @return List 返回数据列表
     * @throws DAOException 异常抛出
	 */
	List<UserPo> listAll() throws DAOException;

	/**
	 * 查询单条记录
	 * @param id 主键id
	 * @return 返回唯一对象
     * @throws DAOException 异常抛出
	 */
	UserPo getOneById(Long id) throws DAOException;
	
	/**
	 * 动态查询单条记录
	 * @param userPo 实体bean对象
     * @return 返回唯一对象
     * @throws DAOException 异常抛出
	 */
	UserPo getOneCriteria(UserPo userPo) throws DAOException;

	/**
	 * 统计记录数
	 * @param userPo 实体bean对象
     * @return 返回记录数
     * @throws DAOException 异常抛出
	 */
	Long count(UserPo userPo) throws DAOException;

	/**
	 * 自动分页查询列表（PageHelper 插件）
	 * @param userPo 实体bean对象
	 * @return list 返回自动分页列表
     * @throws DAOException 异常抛出
	 */
	List<UserPo> listPaginated(UserPo userPo) throws DAOException;
	
	
	/**
	 * 手动分页查询
	 * @param userPo 实体bean对象
	 * @return list 返回手动分页列表
     * @throws DAOException 异常抛出
	 */
	List<UserPo> listPaginatedManual(UserPo userPo) throws DAOException;
	
	/**
	 * 手动分页查询总记录数
	 * @param userPo  实体bean对象
     * @throws DAOException 异常抛出
	 */
	Long countPaginatedManual(UserPo userPo) throws DAOException;

    /**
    * 动态条件关联查询列表
    * @param userPo 实体bean对象
    * @return List 返回数据列表
    * @throws DAOException 异常抛出
    */
    List<UserPo> listCriteriaLeftUserRoleRelation(UserPo userPo) throws DAOException;
	/**
    * 根绝Ids关联查询列表数据
    * @return List  返回数据列表
    * @throws DAOException 异常抛出
    */
    List<UserPo> listInIdsLeftUserRoleRelation(UserPo userPo) throws DAOException;
    /**
    * 关联查询所有数据
    * @return List 返回数据列表
    * @throws DAOException 异常抛出
    */
    List<UserPo> listAllLeftUserRoleRelation() throws DAOException;

        /**
        * 关联查询单条记录
        * @param id 主键id
        * @return 返回唯一对象
        * @throws DAOException 异常抛出
        */
	UserPo getOneByIdLeftUserRoleRelation(Long id) throws DAOException;

    /**
    * 动态关联查询单条记录
    * @param userPo 实体bean对象
    * @return 返回唯一对象
    * @throws DAOException 异常抛出
    */
	UserPo getOneCriteriaLeftUserRoleRelation(UserPo userPo) throws DAOException;

    /**
    * 自动分页关联查询列表（PageHelper 插件）
    * @param userPo 实体bean对象
    * @return list 返回自动分页列表
    * @throws DAOException 异常抛出
    */
    List<UserPo> listPaginatedLeftUserRoleRelation(UserPo userPo) throws DAOException;

    /**
    * 动态条件关联查询列表
    * @param userPo 实体bean对象
    * @return List 返回数据列表
    * @throws DAOException 异常抛出
    */
    List<UserPo> listCriteriaInnerOrganization(UserPo userPo) throws DAOException;
	/**
    * 根绝Ids关联查询列表数据
    * @return List  返回数据列表
    * @throws DAOException 异常抛出
    */
    List<UserPo> listInIdsInnerOrganization(UserPo userPo) throws DAOException;
    /**
    * 关联查询所有数据
    * @return List 返回数据列表
    * @throws DAOException 异常抛出
    */
    List<UserPo> listAllInnerOrganization() throws DAOException;

        /**
        * 关联查询单条记录
        * @param id 主键id
        * @return 返回唯一对象
        * @throws DAOException 异常抛出
        */
	UserPo getOneByIdInnerOrganization(Long id) throws DAOException;

    /**
    * 动态关联查询单条记录
    * @param userPo 实体bean对象
    * @return 返回唯一对象
    * @throws DAOException 异常抛出
    */
	UserPo getOneCriteriaInnerOrganization(UserPo userPo) throws DAOException;

    /**
    * 自动分页关联查询列表（PageHelper 插件）
    * @param userPo 实体bean对象
    * @return list 返回自动分页列表
    * @throws DAOException 异常抛出
    */
    List<UserPo> listPaginatedInnerOrganization(UserPo userPo) throws DAOException;

}
