package com.dxx.springcloud.weather.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxx.springcloud.weather.domain.WeatherResponse;
import com.dxx.springcloud.weather.service.WeatherDataService;

@RestController
@RequestMapping("/weather")
public class WeatherController {
	private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);
	@Autowired
	private WeatherDataService weatherService;
	
	@GetMapping("/cityId/{cityId}")
	private WeatherResponse getReportByCityId(@PathVariable String cityId) {
		logger.info("query data here ................");
		return weatherService.getDataByCityId(cityId);
	}
	
	@GetMapping("/cityName/{cityName}")
	private WeatherResponse getResponseByCityName(@PathVariable String cityName) {
		return weatherService.getDataByCityName(cityName);
	}
}
