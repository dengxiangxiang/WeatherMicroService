package com.dxx.springcloud.weather.service;

import java.util.List;

import com.dxx.springcloud.weather.domain.City;

public interface CityDataService {
	 List<City> listCity() throws Exception;
}
