package com.hzgy.generator.dao.impl;

import com.hzgy.generator.dao.IDao;
import com.hzgy.generator.dao.MySqlSessionFactory;
import com.hzgy.generator.entity.PdmTable;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

public class TableDao implements IDao<PdmTable> {

	static Logger logger = Logger.getLogger(TableDao.class);
	/**
	 * 获取SqlSessionFactory 对象
	 */
	private SqlSessionFactory sqlSessionFactory;

	public TableDao(String databaseName) {
		//初始化
		MySqlSessionFactory.init(databaseName);
		logger.info("get SqlSessionFactory by database name:" + databaseName);
		sqlSessionFactory = MySqlSessionFactory.getSqlSessionFactory(databaseName);
		logger.info("get SqlSessionFactory result:" + sqlSessionFactory);
	}

	@Override
	public int insert(String sqlid, PdmTable entity) {
		SqlSession sqlSession = null;
		int ravl = 0;
		try {
			sqlSession = sqlSessionFactory.openSession();
			ravl = sqlSession.insert(sqlid, entity);
			return ravl;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (sqlSession != null) {
				sqlSession.commit();
			}
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return ravl;
	}

	@Override
	public int update(String sqlid, PdmTable entity) {
		SqlSession sqlSession = null;
		int ravl = 0;
		try {
			sqlSession = sqlSessionFactory.openSession();
			ravl = sqlSession.update(sqlid, entity);
			return ravl;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (sqlSession != null) {
				sqlSession.commit();
			}
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return ravl;
	}

	@Override
	public int delete(String sqlid, PdmTable entity) {
		SqlSession sqlSession = null;
		int ravl = 0;
		try {
			sqlSession = sqlSessionFactory.openSession();
			ravl = sqlSession.delete(sqlid, entity);
			return ravl;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (sqlSession != null) {
				sqlSession.commit();
			}
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return ravl;
	}

	@Override
	public int deleteById(String sqlid, PdmTable entity) {
		SqlSession sqlSession = null;
		int ravl = 0;
		try {
			sqlSession = sqlSessionFactory.openSession();
			ravl = sqlSession.delete(sqlid, entity);
			return ravl;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (sqlSession != null) {
				sqlSession.commit();
			}
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return ravl;
	}

	@Override
	public int deleteAll(String sqlid) {
		SqlSession sqlSession = null;
		int ravl = 0;
		try {
			sqlSession = sqlSessionFactory.openSession();
			ravl = sqlSession.delete(sqlid);
			return ravl;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (sqlSession != null) {
				sqlSession.commit();
			}
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return ravl;
	}

	@Override
	public Long getCount(String sqlid, PdmTable entity) {
		SqlSession sqlSession = null;
		long ravl = 0;
		try {
			sqlSession = sqlSessionFactory.openSession();
			ravl = sqlSession.selectOne(sqlid, entity);
			return ravl;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return ravl;
	}

	@Override
	public List<PdmTable> selectList(String sqlid, PdmTable entity) {
		SqlSession sqlSession = null;
		List<PdmTable> list = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			list = sqlSession.selectList(sqlid, entity);
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return list;
	}

	public List<HashMap<String, Object>> selectList(String sqlid, HashMap<String, Object> paramMap) {
		SqlSession sqlSession = null;
		List<HashMap<String, Object>> list = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			list = sqlSession.selectList(sqlid, paramMap);
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return list;
	}

	@Override
	public List<PdmTable> selectAll(String sqlid) {
		SqlSession sqlSession = null;
		List<PdmTable> list = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			list = sqlSession.selectList(sqlid);
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return list;
	}

	@Override
	public PdmTable selectOne(String sqlid, PdmTable entity) {
		SqlSession sqlSession = null;
		PdmTable table = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			table = sqlSession.selectOne(sqlid, entity);
			return table;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return table;
	}

	/**
	 * 创建数据标
	 * @param sql
	 */
	public boolean createTable(String tableName, String sql) {
		Connection connection = null;
		Statement statement = null;
		try {
			logger.info("开始创建名称为:" + tableName + "的数据库表.");
			logger.info("建表脚本为:" + sql);
			SqlSession sqlSession = sqlSessionFactory.openSession();
			connection = sqlSession.getConnection();
			statement = connection.createStatement();
			statement.addBatch(sql);
			statement.executeBatch();
			logger.info("名称为:" + tableName + "的数据库表创建成功.");
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.info("名称为:" + tableName + "的数据库表创建失败.");
			System.exit(1);
		}
		finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 创建表关系
	 * @param referenceName
	 * @param sql
	 * @return
	 */
	public boolean createTableReference(String tableName, String referenceName, String sql) {
		Connection connection = null;
		Statement statement = null;
		try {
			logger.info("开始为：" + tableName + "创建名称为 " + referenceName + " 的数据库表关系.");
			logger.info("关系脚本为:" + sql);
			SqlSession sqlSession = sqlSessionFactory.openSession();
			connection = sqlSession.getConnection();
			statement = connection.createStatement();
			statement.addBatch(sql);
			statement.executeBatch();
			logger.info("名称为:" + referenceName + "的数据库表关系创建成功.");
			return true;
		}
		catch (Exception e) {
			logger.info("失败原因:" + e.getMessage());
			logger.info("名称为:" + referenceName + "的数据库表关系创建失败.");
		}
		finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 创建索引
	 * @param indexName
	 * @param sql
	 * @return
	 */
	public boolean createTableIndex(String tableName, String indexName, String sql) {
		Connection connection = null;
		Statement statement = null;
		try {
			logger.info("开始为：" + tableName + "创建名称为 " + indexName + " 的数据库表索引.");
			logger.info("索引创建脚本为:" + sql);
			SqlSession sqlSession = sqlSessionFactory.openSession();
			connection = sqlSession.getConnection();
			statement = connection.createStatement();
			statement.addBatch(sql);
			statement.executeBatch();
			logger.info("名称为:" + indexName + "的数据库表索引创建成功.");
			return true;
		}
		catch (Exception e) {
			logger.info("失败原因:" + e.getMessage());
			logger.info("名称为:" + indexName + "的数据库表索引创建失败.");
		}
		finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 更新表信息
	 * @param tableCode
	 * @param sql
	 * @return
	 */
	public boolean alterTable(String tableCode, String sql) {
		Connection connection = null;
		Statement statement = null;
		try {
			logger.info("更新表名称为：" + tableCode + " 的数据库表信息.");
			logger.info("执行脚本为:" + sql);
			SqlSession sqlSession = sqlSessionFactory.openSession();
			connection = sqlSession.getConnection();
			statement = connection.createStatement();
			statement.addBatch(sql);
			statement.executeBatch();
			logger.info("更新表名称为：" + tableCode + " 的数据库表信息更新成功.");
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.info("更新表名称为：" + tableCode + " 的数据库表信息更新失败.");
		}
		finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 判断数据库表是否存在
	 * @param tableName
	 * @return
	 */
	public boolean getTable(String tableName) {
		Connection connection = null;
		ResultSet rs = null;
		String[] types = { "TABLE" };
		try {
			logger.info("sqlSessionFactory:" + sqlSessionFactory);
			SqlSession sqlSession = sqlSessionFactory.openSession();
			connection = sqlSession.getConnection();
			rs = connection.getMetaData().getTables(null, null, tableName, types);
			while (rs.next()) {
				return true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (connection != null) {
					connection.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public ResultSet select(String sql) {
		Connection connection = null;
		Statement statement = null;
		try {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			connection = sqlSession.getConnection();
			statement = connection.createStatement();
			return statement.executeQuery(sql);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
