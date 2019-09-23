package com.dxx.springcloud.weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxx.springcloud.weather.domain.City;
import com.dxx.springcloud.weather.service.CityDataService;

@RestController
public class CityDataController {

	@Autowired
	private CityDataService cityDataService;
	
	@RequestMapping("/cities")
	public List<City> getCityList() {
		return cityDataService.listCity();
	}
}
