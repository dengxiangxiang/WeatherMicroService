package com.dxx.springcloud.weather.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dxx.springcloud.weather.domain.City;

@Component
public class CityDataClientFallback implements CityDataClient {

	@Override
	public List<City> listCity()  {
		List<City> list=new ArrayList<City>();
		
		City shanghai = new City();
		shanghai.setCityId("101020100");
		shanghai.setCityName("上海");
		shanghai.setCityCode("shanghai");
		shanghai.setProvince("上海");
		
		list.add(shanghai);
		return list;
		
	}

}
