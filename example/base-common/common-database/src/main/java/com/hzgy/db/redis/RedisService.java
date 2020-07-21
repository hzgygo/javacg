package com.hzgy.db.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisService<T> {

	@Resource
	StringRedisTemplate stringRedisTemplate;

	@Resource(name = "stringRedisTemplate")
	ValueOperations<String, String> valueOperationString;

	@Resource
	RedisTemplate<String, T> redisTemplate;

	@Resource(name = "redisTemplate")
	ValueOperations<String,T> valueOperationsObject;

	/**
	 * 根据指定key获取字符串数据
	 * @param key redis key参数
	 * @return 返回字符串
	 */
	public String getStringData(String key) {
		return valueOperationString.get(key);
	}

	/**
	 * 存储字符串数据
	 * @param key redis key参数
	 * @param value redis value参数
	 */
	public void setStringData(String key, String value) {
		valueOperationString.set(key, value);
	}
	
	/**
	 * 存储字符串数据，并设置失效时间和时间类型
	 * @param key  redis key参数
	 * @param value redis value参数
	 * @param timeout redis存储时间
	 * @param timeUnit 时间单位（类型）
	 */
	public void setStringData(String key, String value,long timeout,TimeUnit timeUnit) {
		valueOperationString.set(key, value, timeout, timeUnit);
	}

	/**根绝key值，删除对应的数据
	 * 删除指定key
	 * @param key redis key参数
	 */
	public void removeByKey(String key) {
		stringRedisTemplate.delete(key);
	}

	/**
	 * 根据指定o获取Object
	 * @param objectKey redis objectKey参数
	 * @return 返回泛型
	 */
	public T getObjectData(Object objectKey) {
		return valueOperationsObject.get(objectKey);
	}

	/**
	 * 设置obj缓存
	 * @param objectKey redis objectKey参数
	 * @param objectValue redis objectValue参数
	 */
	public void setObjectData(String objectKey, T objectValue) {
		valueOperationsObject.set(objectKey, objectValue);
	}

	/**
	 * 设置obj缓存
	 * @param objectKey redis objectKey参数
	 * @param objectValue redis objectValue参数
	 * @param timeout redis存储时间
	 */
	public void setObjectData(String objectKey, T objectValue,long timeout) {
		valueOperationsObject.set(objectKey, objectValue,timeout);
	}

	/**
	 * 存储字符串数据，并设置失效时间和时间类型
	 * @param objectKey  redis key参数
	 * @param objectValue redis value参数
	 * @param timeout redis存储时间
	 * @param timeUnit 时间单位（类型）
	 */
	public void setObjectData(String objectKey, T objectValue,long timeout,TimeUnit timeUnit) {
		valueOperationsObject.set(objectKey, objectValue,timeout,timeUnit);
	}


	/**
	 * 删除Obj缓存
	 * @param objectKey redis objectKey参数
	 */
	public void deleteByObject(String objectKey) {
		redisTemplate.delete(objectKey);
	}

	/**
	 * 根据id清除数据
	 * @param id id key
	 */
	public void clear(String id) {
		Set<String> keys = redisTemplate.keys("*:" + id + "*");
		if (!CollectionUtils.isEmpty(keys)) {
			redisTemplate.delete(keys);
		}
	}

	/**
	 * 获取连接数
	 * @return 数量
	 */
	public int getSize() {
		Long size = (Long) redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.dbSize();
			}
		});
		return size.intValue();
	}



}
