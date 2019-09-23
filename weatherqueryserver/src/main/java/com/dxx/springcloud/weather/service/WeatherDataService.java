package com.dxx.springcloud.weather.service;

import com.dxx.springcloud.weather.domain.WeatherResponse;

public interface WeatherDataService {

	WeatherResponse getDataByCityId(String cityId);
	
	WeatherResponse getDataByCityName(String cityName);
}
