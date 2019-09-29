package com.dxx.springcloud.weather.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxx.springcloud.weather.domain.City;
import com.dxx.springcloud.weather.domain.WeatherResponse;

@Service(value="serviceWithFeign")
public class WeatherReportServiceImpl2 implements WeatherRepoetService {
	@Autowired
	private CityDataClient cityDataClient;

	@Autowired
	private WeatherDataQueryClient weatherDataQueryClient;

	@Override
	public WeatherResponse getDataByCityId(String cityId) {
		return weatherDataQueryClient.getDataByCityId(cityId);
	}

	@Override
	public List<City> listCity()  {
		return cityDataClient.listCity();
	}

}
