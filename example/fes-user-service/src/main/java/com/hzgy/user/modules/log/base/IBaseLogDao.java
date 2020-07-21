package com.hzgy.user.modules.log.base;

import java.util.List;
import com.hzgy.user.modules.log.entity.LogPo;
import com.hzgy.db.dao.IDao;
import com.hzgy.core.exception.DAOException;

public interface IBaseLogDao extends IDao{

	/**
	 * 保存数据
     * @param logPo 实体bean对象
	 * @return 返回整数结果 1：成功 0：失败
	 * @throws DAOException 异常抛出
     */
	Integer save(LogPo logPo) throws DAOException;

	/**
	* 数据库修改
	* @param logPo 实体bean对象
	* @return 返回整数结果 1：成功 0：失败
	* @throws DAOException 异常抛出
	*/
	Integer update(LogPo logPo) throws DAOException;
	
	/**
	 * 数据库修改
     * @param logPo 实体bean对象
     * @return 返回整数结果 1：成功 0：失败
     * @throws DAOException 异常抛出
     */
	Integer updateById(LogPo logPo) throws DAOException;
	
	/**
	 * 动态条件删除数据
	 * @param logPo 实体bean对象
	 * @return 返回整数结果 1：成功 0：失败
	 * @throws DAOException 异常抛出
	 */
	Integer removeCriteria(LogPo logPo) throws DAOException;

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
	 * @param logPo 实体bean对象
	 * @return List 返回数据列表
     * @throws DAOException 异常抛出
	 */
	List<LogPo> listCriteria(LogPo logPo) throws DAOException;

	/**
	 * 查询所有Id列表
	 * @return List 返回ids数据列表
     * @throws DAOException 异常抛出
	 */
	List<Long> listIdsCriteria(LogPo logPo) throws DAOException;

	/**
	 * 根绝Ids查询列表数据
	 * @return List  返回数据列表
     * @throws DAOException 异常抛出
	 */
	List<LogPo> listByInIds(LogPo logPo) throws DAOException;
	/**
	 * 查询所有数据
	 * @return List 返回数据列表
     * @throws DAOException 异常抛出
	 */
	List<LogPo> listAll() throws DAOException;

	/**
	 * 查询单条记录
	 * @param id 主键id
	 * @return 返回唯一对象
     * @throws DAOException 异常抛出
	 */
	LogPo getOneById(Long id) throws DAOException;
	
	/**
	 * 动态查询单条记录
	 * @param logPo 实体bean对象
     * @return 返回唯一对象
     * @throws DAOException 异常抛出
	 */
	LogPo getOneCriteria(LogPo logPo) throws DAOException;

	/**
	 * 统计记录数
	 * @param logPo 实体bean对象
     * @return 返回记录数
     * @throws DAOException 异常抛出
	 */
	Long count(LogPo logPo) throws DAOException;

	/**
	 * 自动分页查询列表（PageHelper 插件）
	 * @param logPo 实体bean对象
	 * @return list 返回自动分页列表
     * @throws DAOException 异常抛出
	 */
	List<LogPo> listPaginated(LogPo logPo) throws DAOException;
	
	
	/**
	 * 手动分页查询
	 * @param logPo 实体bean对象
	 * @return list 返回手动分页列表
     * @throws DAOException 异常抛出
	 */
	List<LogPo> listPaginatedManual(LogPo logPo) throws DAOException;
	
	/**
	 * 手动分页查询总记录数
	 * @param logPo  实体bean对象
     * @throws DAOException 异常抛出
	 */
	Long countPaginatedManual(LogPo logPo) throws DAOException;

}
