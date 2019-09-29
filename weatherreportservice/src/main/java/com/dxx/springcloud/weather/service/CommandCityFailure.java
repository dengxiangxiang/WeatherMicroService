package com.dxx.springcloud.weather.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dxx.springcloud.weather.domain.City;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

@Service
public class CommandCityFailure extends HystrixCommand<List<City>> {

	@Autowired
	private RestTemplate restTemplate;

	protected CommandCityFailure() {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CityGroup")));
	}

	@Override
	protected List<City> run() throws Exception {
		System.out.println(Thread.currentThread().getName()+" is running.....ListCity");
		City[] cities = restTemplate.getForObject("http://citydataserver/cities", City[].class);
		List<City> list=  Arrays.asList(cities);
		return list;
	}

	@Override
	protected List<City> getFallback() {
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
