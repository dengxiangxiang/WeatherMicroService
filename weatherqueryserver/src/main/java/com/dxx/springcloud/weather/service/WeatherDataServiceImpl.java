package com.dxx.springcloud.weather.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dxx.springcloud.weather.domain.WeatherResponse;
import com.dxx.springcloud.weather.repository.RedisRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {
	private final static Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);
	private final String WEATHER_URL = "http://wthrcdn.etouch.cn/weather_mini";

	
	@Autowired
	private RedisRepository redisRepository;
	
	
	@Override
	public WeatherResponse getDataByCityId(String cityId) {
		// TODO Auto-generated method stub
		String uri = WEATHER_URL+"?citykey="+cityId;
		
		return doGetWeatherData(uri);
	}

	@Override
	public WeatherResponse getDataByCityName(String cityName) {
		// TODO Auto-generated method stub
		String uri = WEATHER_URL+"?city="+cityName;
		
		return doGetWeatherData(uri);
	}
	

	private WeatherResponse doGetWeatherData(String uri) {
		String strBody=null;
		strBody= redisRepository.get(uri);
		if(strBody==null) {
			logger.error("不存在key：{}", uri);
		}
		
		ObjectMapper mapper= new ObjectMapper();
		WeatherResponse weather =null;
		
		try {
			weather = mapper.readValue(strBody, WeatherResponse.class);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return weather;
	}



}
