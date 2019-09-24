package com.dxx.springcloud.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxx.springcloud.weather.domain.Weather;

@Service
public class WeatherReportServiceImpl implements WeatherReportService {

	@Autowired
	private WeatherDataQueryClient weatherDataQueryClient;
	@Override
	public Weather getDataByCityId(String cityId) {
		return weatherDataQueryClient.getDataByCityId(cityId).getData();
	}

}
