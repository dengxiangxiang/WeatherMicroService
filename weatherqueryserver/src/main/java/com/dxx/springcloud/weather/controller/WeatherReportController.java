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
import com.dxx.springcloud.weather.service.CityDataService;
import com.dxx.springcloud.weather.service.WeatherReportService;

@RestController
@RequestMapping("/report")
public class WeatherReportController {
	
	@Autowired
	private CityDataService cityDataService;
	
	@Autowired
	private WeatherReportService weatherReportService;
	
	@ResponseBody
	@GetMapping("/cityId/{cityId}")
	public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId, Model model) {
		model.addAttribute("tittle","MicroService Weather...");
		model.addAttribute("cityId", cityId);
		List<City> cityList = new ArrayList<City>();
		try {
			cityList=cityDataService.listCity();			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("cityList", cityList);
		model.addAttribute("report", weatherReportService.getDataByCityId(cityId));
		return new ModelAndView("weather/report","reportModel",model);
	}
}
