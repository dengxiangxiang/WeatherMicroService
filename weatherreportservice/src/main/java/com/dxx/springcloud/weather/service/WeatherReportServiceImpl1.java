package com.dxx.springcloud.weather.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dxx.springcloud.weather.domain.City;
import com.dxx.springcloud.weather.domain.WeatherResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@Service(value="serviceWithRestTemplate")
public class WeatherReportServiceImpl1 implements WeatherRepoetService{
		
	@Autowired
	private RestTemplate restTemplate;
	
@Override
@HystrixCommand(fallbackMethod="listCityFallback",
				groupKey="listCityGroup",
				commandKey="listCityCommand",
				threadPoolKey="listCityThreadPool")
	public List<City> listCity() throws Exception {
	System.out.println(Thread.currentThread().getName()+" is running.....ListCity");
	City[] cities = restTemplate.getForObject("http://citydataserver/cities", City[].class);
	List<City> list=  Arrays.asList(cities);
	return list;
	}

	@Override
	@HystrixCommand(fallbackMethod="getDataByCityIdFallback",
					groupKey="getDataByCityIdGroup",
					commandKey="getDataByCityIdCommand",
					threadPoolKey="getDataByCityIdThreadPool")
	public WeatherResponse getDataByCityId(String cityId) throws Exception {
		System.out.println(Thread.currentThread().getName()+" is running.....WeatherResponse");
		WeatherResponse weatherResponse = restTemplate
				.getForObject("http://weatherqueryserver/weather/cityId/" + cityId, WeatherResponse.class);
		return weatherResponse;
	}
	
	protected WeatherResponse getDataByCityIdFallback(String cityId) throws Exception{
		System.out.println(Thread.currentThread().getName()+" is running.....WeatherResponse fallback");
		WeatherResponse weatherResponse = new WeatherResponse();
		return weatherResponse;
	}
	
	protected List<City> listCityFallback(){
		System.out.println(Thread.currentThread().getName()+" is running.....ListCity fallback");
		List<City> list = new ArrayList<City>();

		City shanghai = new City();
		shanghai.setCityId("101020100");
		shanghai.setCityName("上海");
		shanghai.setCityCode("shanghai");
		shanghai.setProvince("上海");

		list.add(shanghai);
		return list;
	}

	

}
