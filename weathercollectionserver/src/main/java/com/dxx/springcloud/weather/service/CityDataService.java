package com.dxx.springcloud.weather.service;

import com.dxx.springcloud.weather.domain.City;

import java.util.List;

public interface CityDataService {
	 List<City> listCity() throws Exception;
}
