package com.dxx.springcloud.weather.service;

import org.springframework.stereotype.Component;

import com.dxx.springcloud.weather.domain.WeatherResponse;

@Component
public class WeatherDataQueryClientFallback implements WeatherDataQueryClient {

	@Override
	public WeatherResponse getDataByCityId(String cityId) {
		return new WeatherResponse();
	}

}
