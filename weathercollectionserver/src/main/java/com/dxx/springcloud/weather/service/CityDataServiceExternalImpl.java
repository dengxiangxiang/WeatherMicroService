package com.dxx.springcloud.weather.service;

import com.dxx.springcloud.weather.domain.City;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityDataServiceExternalImpl implements CityDataService {
    public List<City> listCity() throws Exception {
//        throw new Exception("应该通过远程调用实现。");

        List<City> cityList = new ArrayList<>();
        City city = new City();
        city.setCityId("101280101");
        city.setCityName("广州");
        cityList.add(city);
        return cityList;
    }
}
