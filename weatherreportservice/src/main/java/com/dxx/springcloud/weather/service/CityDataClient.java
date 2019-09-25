package com.dxx.springcloud.weather.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.dxx.springcloud.weather.domain.City;

@FeignClient(name="citydataserver",fallback=CityDataClientFallback.class)
public interface CityDataClient {
	@GetMapping("cities")
	 List<City> listCity() throws Exception;
}
