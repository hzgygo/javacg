package com.hzgy.generator.dao;

import java.util.List;

import com.hzgy.generator.entity.Entity;


public interface IDao<T extends Entity>{
	/**
	 * 数据库插入
	 * 
	 * @param sqlid
	 * @param t
	 */
	public int insert(String sqlid, T entity);

	/**
	 * 数据库修改
	 * 
	 * @param sqlid
	 * @param t
	 */
	public int update(String sqlid, T entity);

	/**
	 * 数据库删除
	 * 
	 * @param sqlid
	 * @param t
	 */
	public int delete(String sqlid, T entity);
	
	/**
	 * 根据主键ID删除数据
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public int deleteById(String sqlid, T entity);
	
	/**
	 * 删除所有数据
	 * @return
	 * @throws Exception
	 */
	public int deleteAll(String sqlid);

	/**
	 * 计算满足条件条数
	 * @param sqlid
	 * @param t
	 */
	public Long getCount(String sqlid, T entity);

	/**
	 * 查询列表
	 * @param sqlid
	 * @param t
	 * @return
	 */
	public List<T> selectList(String sqlid, T entity);
	
	/**
	 *查询所有数据
	 * @param sqlid
	 * @param t
	 * @return
	 */
	public List<T> selectAll(String sqlid);

	/**
	 * 查询单条记录
	 * @param sqlid
	 * @param t
	 * @return
	 */
	public Object selectOne(String sqlid, T entity);
}
