package com.dxx.springcloud.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxx.springcloud.weather.domain.Weather;

@Service
public class WeatherReportServiceImpl implements WeatherReportService {

	@Autowired
	private WeatherDataService weatherDataService;
	@Override
	public Weather getDataByCityId(String cityId) {
		// TODO Auto-generated method stub
		return weatherDataService.getDataByCityId(cityId).getData();
	}

}
