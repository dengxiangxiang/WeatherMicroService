package com.dxx.springcloud.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisPoolingClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfiguration {
	/***
	 * 配置Jedis连接池
	 * @return
	 */
	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(8);
        config.setMaxIdle(8);
        config.setMinIdle(0);
        config.setMaxWaitMillis(100000);

        return config;
	}
	
	/**
	 * 配置单机版redis 地址，也可以配置集群版
	 * @return
	 */
	@Bean
	public RedisStandaloneConfiguration redisStandaloneConfiguration() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
		config.setHostName("127.0.0.1");
		config.setPassword("");
		config.setPort(6379);
		return config;		
	}
	
	/**
	 * 使用连接池配置JedisClientConfiguration
	 * @param jedisPoolConfig
	 * @return
	 */
	@Bean
	public JedisClientConfiguration jedisClientConfiguration(JedisPoolConfig jedisPoolConfig) {
		JedisPoolingClientConfigurationBuilder jpcf = (JedisPoolingClientConfigurationBuilder)JedisClientConfiguration.builder();
        //修改我们的连接池配置
        jpcf.poolConfig(jedisPoolConfig);
        //通过构造器来构造jedis客户端配置
        JedisClientConfiguration jedisClientConfiguration = jpcf.build();
        return jedisClientConfiguration;
	}
	
	/**
	 * 提供Jackson序列化器
	 * @return
	 */
    @Bean
    public Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer(){
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(objectMapper);

        return serializer;
    }
	
    /**
     * 使用 单机redis参数 和 jedisclientconfig 配置redis链接工厂
     * @param redisStandaloneConfiguration
     * @param jedisClientConfiguration
     * @return
     */
	@Bean
    public RedisConnectionFactory redisConnectionFactory(
    		RedisStandaloneConfiguration redisStandaloneConfiguration,
    		JedisClientConfiguration jedisClientConfiguration) {
        JedisConnectionFactory fac = new JedisConnectionFactory(redisStandaloneConfiguration,jedisClientConfiguration);
        return fac;
    }
 
	/**
	 * 使用链接工厂和序列化器配置RedisTemplate
	 * @param redisConnectionFactory
	 * @param jackson2JsonRedisSerializer
	 * @return
	 */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(
    		RedisConnectionFactory redisConnectionFactory,
    		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        
        //字符串方式序列化KEY
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        //JSON方式序列化VALUE
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
