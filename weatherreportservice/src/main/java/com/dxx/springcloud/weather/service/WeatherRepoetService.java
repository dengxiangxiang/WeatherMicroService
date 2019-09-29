package com.dxx.springcloud.weather.service;

import java.util.List;

import com.dxx.springcloud.weather.domain.City;
import com.dxx.springcloud.weather.domain.WeatherResponse;

public interface WeatherRepoetService  {

	public WeatherResponse getDataByCityId(String cityId) throws Exception ;

	public List<City> listCity() throws Exception  ;
}
