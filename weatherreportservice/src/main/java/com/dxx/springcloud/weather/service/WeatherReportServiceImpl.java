package com.dxx.springcloud.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxx.springcloud.weather.domain.Weather;

@Service
public class WeatherReportServiceImpl implements WeatherReportService {

	@Autowired
	private WeatherDataQueryClient weatherDataClient;
	@Override
	public Weather getDataByCityId(String cityId) {
		// TODO Auto-generated method stub
		return weatherDataClient.getDataByCityId(cityId).getData();
	}

}
