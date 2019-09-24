package com.dxx.springcloud.weather.repository;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepositoryImpl implements RedisRepository {
	
	@Autowired
	private RedisTemplate<String,String> redisTemplate;

	@Override
	public void setWithExpire(String key, String value, long time, TimeUnit timeUnit) {
		ValueOperations <String,String> ops = redisTemplate.opsForValue();
		ops.set(key,value,time,timeUnit);
	}

	@Override
	public String get(String key) {
		ValueOperations <String,String> ops = redisTemplate.opsForValue();
		return ops.get(key);
	}

}
