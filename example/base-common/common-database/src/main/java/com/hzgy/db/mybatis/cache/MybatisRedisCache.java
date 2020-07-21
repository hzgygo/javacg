package com.hzgy.db.mybatis.cache;

import com.hzgy.core.spring.SpringContextUtil;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用Redis来做Mybatis的二级缓存 实现Mybatis的Cache接口
 */
public class MybatisRedisCache  implements Cache {

	private static final Logger logger = LoggerFactory.getLogger(MybatisRedisCache.class);
	/**
	 * 读写锁
	 */
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

	private RedisTemplate<String, Object> redisTemplate;

	private String id;

	public void getRedisTemplate(){
		if (redisTemplate == null){
			redisTemplate = SpringContextUtil.getBean("redisTemplate");
		}
	}

	@Resource
	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public MybatisRedisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		logger.info("Redis Cache id " + id);
		this.id = id;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void putObject(Object key, Object value) {
		logger.info("MybatisRedisCache put object.");
		this.getRedisTemplate();
		if (value != null) {
			// 向Redis中添加数据，有效时间是2天
			redisTemplate.opsForValue().set(key.toString(), value, 2, TimeUnit.DAYS);
		}
	}

	@Override
	public Object getObject(Object key) {
		this.getRedisTemplate();
		try {
			logger.info("MybatisRedisCache get object.");
			if (key != null) {
				Object obj = redisTemplate.opsForValue().get(key.toString());
				return obj;
			}
		}
		catch (Exception e) {
			logger.error("get object error:" + e.getMessage(),e);
		}
		return null;
	}

	@Override
	public Object removeObject(Object key) {
		this.getRedisTemplate();
		try {
			if (key != null) {
				redisTemplate.delete(key.toString());
			}
		}
		catch (Exception e) {
			logger.error("remove object error:" + e.getMessage(),e);
		}
		return null;
	}

	@Override
	public void clear() {
		this.getRedisTemplate();
		logger.info("clear cache by key:" + this.id);
		try {
			Set<String> keys = redisTemplate.keys("*" + this.id + "*");
			if (!CollectionUtils.isEmpty(keys)) {
				logger.info("clear cache keys count:" + keys.size());
				redisTemplate.delete(keys);
			}
			else{
                logger.info("clear cache keys is null.");
            }
		}
		catch (Exception e) {
			logger.error("clear error:" + e.getMessage(),e);
		}
	}

	@Override
	public int getSize() {
		this.getRedisTemplate();
		Long size = (Long) redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.dbSize();
			}
		});
		return size.intValue();
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}
}