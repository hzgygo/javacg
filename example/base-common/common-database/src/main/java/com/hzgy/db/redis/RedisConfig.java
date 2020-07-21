package com.hzgy.db.redis;

import com.hzgy.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class RedisConfig {

	private static Logger logger = LoggerFactory.getLogger(RedisConfig.class);

	@Value("${spring.redis.jedis.pool.maxIdle}")
	private Integer maxIdle;

	@Value("${spring.redis.maxTotal}")
	private Integer maxTotal;

	@Value("${spring.redis.maxWaitMillis}")
	private Integer maxWaitMillis;

	@Value("${spring.redis.minEvictableIdleTimeMillis}")
	private Integer minEvictableIdleTimeMillis;

	@Value("${spring.redis.numTestsPerEvictionRun}")
	private Integer numTestsPerEvictionRun;

	@Value("${spring.redis.timeBetweenEvictionRunsMillis}")
	private long timeBetweenEvictionRunsMillis;

	@Value("${spring.redis.testOnBorrow}")
	private boolean testOnBorrow;

	@Value("${spring.redis.testWhileIdle}")
	private boolean testWhileIdle;

	@Value("${spring.redis.sentinel.masterName}")
	private String masterName;

	@Value("#{'${spring.redis.sentinel.nodes}'.split(',')}")
	private List<String> nodes;

	@Value("${spring.redis.password}")
	private String password;

	@Value("${spring.redis.database}")
	private String database;

	@Value("${spring.redis.use.sentinel}")
	private String useSentinel;

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private Integer port;

	@Value("${spring.redis.timeout}")
	private Integer timeout;
	/**
	 * JedisPoolConfig 连接池
	 *
	 * @return
	 */
	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		// 最大空闲数
		jedisPoolConfig.setMaxIdle(maxIdle);
		// 连接池的最大数据库连接数
		jedisPoolConfig.setMaxTotal(maxTotal);
		// 最大建立连接等待时间
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		// 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
		jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		// 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
		jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
		// 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
		jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		// 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
		jedisPoolConfig.setTestOnBorrow(testOnBorrow);
		// 在空闲时检查有效性, 默认false
		jedisPoolConfig.setTestWhileIdle(testWhileIdle);
		return jedisPoolConfig;
	}

	/**
	 * 配置redis的哨兵
	 *
	 */
	@Bean
	public RedisSentinelConfiguration sentinelConfiguration() {
		RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
		redisSentinelConfiguration.setMaster(masterName);
		//配置redis的哨兵sentinel
		Set<RedisNode> redisNodeSet = new HashSet<>();
		nodes.forEach(x->{
			redisNodeSet.add(new RedisNode(x.split(":")[0],Integer.parseInt(x.split(":")[1])));
		});
		redisSentinelConfiguration.setSentinels(redisNodeSet);
		return redisSentinelConfiguration;
	}

	/**
	 * 配置工厂
	 *
	 * @param jedisPoolConfig
	 * @return
	 */
	@Bean(name="jedisConnectionFactory")
	public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig, RedisSentinelConfiguration sentinelConfig) {
		JedisConnectionFactory jedisConnectionFactory = null;
		//是否启用哨兵模式，空或false不启用
		if (StringUtil.isEmpty(useSentinel) || useSentinel.equals("false")){
			jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
			if (port != null) {
				jedisConnectionFactory.setPort(port);
			}
			if (timeout != null) {
				jedisConnectionFactory.setTimeout(timeout);
			}
			if (StringUtil.isNotEmpty(host)) {
				jedisConnectionFactory.setHostName(host);
			}
		}
		else {
			jedisConnectionFactory = new JedisConnectionFactory(sentinelConfig, jedisPoolConfig);
		}
		if (StringUtil.isNotEmpty(password)) {
			jedisConnectionFactory.setPassword(password);
		}
		if (StringUtil.isNotEmpty(database)) {
			jedisConnectionFactory.setDatabase(Integer.parseInt(database));
		}
		logger.info("jedis connection factory bean init success.");
		return jedisConnectionFactory;
	}

	/**
	 * 实例化 RedisTemplate 对象
	 *
	 * @return
	 */
	@Bean(name="redisTemplate")
	public RedisTemplate<String, Object> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
		return redisTemplate;
	}

	/**
	 * 设置数据存入 redis 的序列化方式,并开启事务
	 *
	 * @param redisTemplate
	 * @param factory
	 */
	private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
		//如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		// 开启事务
		redisTemplate.setEnableTransactionSupport(true);
		redisTemplate.setConnectionFactory(factory);
	}

	@Bean(name="stringRedisTemplate")
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}
}
