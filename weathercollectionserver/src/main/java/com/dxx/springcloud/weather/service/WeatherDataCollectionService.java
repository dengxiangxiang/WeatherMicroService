package com.dxx.springcloud.weather.service;

public interface WeatherDataCollectionService {

	void syncDataByCityId(String cityId);
}
