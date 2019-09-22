package com.dxx.springcloud.weather.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.dxx.springcloud.weather.domain.City;
import com.dxx.springcloud.weather.domain.CityList;
import com.dxx.springcloud.weather.util.XmlBuilder;

@Service
public class CityDataServiceImpl implements CityDataService{

	@Override
	public List<City> listCity() throws Exception {
		// TODO Auto-generated method stub
		Resource resource = new ClassPathResource("citylist.xml");
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(resource.getInputStream(),"utf-8"));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		
		while((line=bufferReader.readLine())!=null) {
			buffer.append(line);
		}
		bufferReader.close();
		
		CityList cityList = (CityList)XmlBuilder.xmlStrToObject(CityList.class, buffer.toString());
		return cityList.getCityList();
	}

}
