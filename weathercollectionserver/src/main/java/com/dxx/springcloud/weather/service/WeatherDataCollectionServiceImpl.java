package com.dxx.springcloud.weather.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dxx.springcloud.weather.repository.RedisRepository;

@Service
public class WeatherDataCollectionServiceImpl implements WeatherDataCollectionService {

	private final String WEATHER_URL = "http://wthrcdn.etouch.cn/weather_mini";
	private final Long TIME_OUT = 180L;
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RedisRepository redisRepository;

	
	@Override
	public void syncDataByCityId(String cityId) {
		String uri = WEATHER_URL+"?citykey="+cityId;
		saveWeatherData(uri);
	}
	
	private void saveWeatherData(String uri) {
		ResponseEntity<String> response = restTemplate.getForEntity(uri,String.class);
		String strBody = null;
		if(response.getStatusCodeValue()==200) {
			strBody= response.getBody();			
		}
		redisRepository.setWithExpire(uri, strBody, TIME_OUT, TimeUnit.SECONDS);
	}

}
