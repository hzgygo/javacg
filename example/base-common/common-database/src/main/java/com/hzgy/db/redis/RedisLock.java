package com.hzgy.db.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class RedisLock {

    private static Logger logger = LoggerFactory.getLogger(RedisLock.class);

    public static final String REDIS_LOCK = "RedisLock:";

    /**
     * 10s锁的有效时间
     */
    private static final long DEFAULT_EXPIRE = 80;

    /**
     * 默认加锁时间5秒
     */
    private static final long DEFAULT_WAIT_LOCK_TIME_OUT = 5;


    @Resource
    RedisTemplate<String, Integer> redisTemplate;

    /**
     * 获取阻塞锁，锁过期时间5秒，等待获取锁80s
     * @param key
     * @return
     */
    public boolean syncLock(String key) {
        return syncLock(key, DEFAULT_WAIT_LOCK_TIME_OUT, DEFAULT_EXPIRE);
    }

    /**
     * 获取阻塞锁，等待获取锁时长80s
     * @param key
     * @param expireTime
     * @return
     */
    public boolean syncLock(String key, long expireTime) {
        return syncLock(key, expireTime, DEFAULT_EXPIRE);
    }

    /**
     * 获取阻塞锁
     * @param key 锁key
     * @param expireTime 锁的过期时间(秒)
     * @param timeout 获取锁的最长等待时间(秒)
     * @return
     */
    public Boolean syncLock(String key, long expireTime, long timeout) {
        String lockKey = generateLockKey(key);
        long nanoWaitForLock = TimeUnit.SECONDS.toNanos(timeout);
        long start = System.nanoTime();

        try {
            while ((System.nanoTime() - start) < nanoWaitForLock) {
                try {
                    if (redisTemplate.opsForValue().setIfAbsent(lockKey, 1)) {
                        logger.debug("加锁，KEY：" + lockKey);
                        redisTemplate.expire(lockKey, expireTime, TimeUnit.SECONDS);
                        return true;
                    }
                } catch (Exception e) {
                    unlock(lockKey);
                    logger.debug("加锁，KEY：" + lockKey + " 失败，释放锁");
                }
                Long lockExpireTime = redisTemplate.getExpire(lockKey);
                if (lockExpireTime != null && -1L == lockExpireTime) {
                    redisTemplate.expire(lockKey, expireTime, TimeUnit.SECONDS);
                }
                //加随机时间防止活锁
                TimeUnit.MILLISECONDS.sleep(200 + new Random().nextInt(100));
            }

        } catch (Exception e){
            logger.debug("加锁出错【" + e + "】，KEY：" + lockKey);
        }

        return false;
    }


    /**
     * 非阻塞锁
     * @param key
     * @param expireTime
     * @return
     */
    public Boolean lock(String key, long expireTime) {
        String lockKey = generateLockKey(key);
        try {
            if (redisTemplate.opsForValue().setIfAbsent(lockKey, 1)) {
                logger.debug("加锁，KEY：" + lockKey);
                redisTemplate.expire(lockKey, expireTime, TimeUnit.SECONDS);
                return true;
            }
        } catch (Exception e) {
            unlock(lockKey);
            logger.debug("加锁，KEY：" + lockKey + " 失败，释放锁");
        }
        Long lockExpireTime = redisTemplate.getExpire(lockKey);
        if (lockExpireTime != null && -1L == lockExpireTime) {
            redisTemplate.expire(lockKey, expireTime, TimeUnit.SECONDS);
        }

        return false;
    }

    /**
     * 解锁
     * @param key
     */
    public void unlock(String key) {
        String lockKey = generateLockKey(key);
        logger.debug("解锁，KEY：" + key);
        redisTemplate.delete(lockKey);
    }


    private String generateLockKey(String key) {
        return String.format(REDIS_LOCK + "%s", key);
    }
}
