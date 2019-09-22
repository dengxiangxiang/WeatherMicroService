package com.dxx.springcloud.weather.repository;

import java.util.concurrent.TimeUnit;

public interface RedisRepository {

	void setWithExpire(String key, String value, long time, TimeUnit timeUnit);
	
	String get(String key);
}
