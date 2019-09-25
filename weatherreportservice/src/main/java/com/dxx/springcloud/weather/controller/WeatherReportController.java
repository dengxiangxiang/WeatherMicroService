package com.dxx.springcloud.weather.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dxx.springcloud.weather.domain.City;
import com.dxx.springcloud.weather.domain.Weather;
import com.dxx.springcloud.weather.service.CityDataClient;
import com.dxx.springcloud.weather.service.WeatherDataQueryClient;

@RestController
@RequestMapping("/report")
public class WeatherReportController {
	
	@Autowired
	private CityDataClient cityDataClient;
	
	@Autowired
	private WeatherDataQueryClient weatherDataQueryClient;
	
	@ResponseBody
	@GetMapping("/cityId/{cityId}")
	public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId, Model model) {
		model.addAttribute("tittle","MicroService Weather...");
		model.addAttribute("cityId", cityId);
		List<City> cityList = new ArrayList<City>();
		try {
			cityList=cityDataClient.listCity();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("cityList", cityList);
		Weather weather = weatherDataQueryClient.getDataByCityId(cityId).getData();
		model.addAttribute("report", weather);
		return new ModelAndView("weather/report","reportModel",model);
	}
}
