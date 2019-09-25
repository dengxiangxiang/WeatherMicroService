package com.dxx.springcloud.weather.service;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dxx.springcloud.weather.domain.WeatherResponse;

@FeignClient(name="weatherqueryserver", fallback=WeatherDataQueryClientFallback.class)
public interface WeatherDataQueryClient {

	@GetMapping("/weather/cityId/{cityId}")
	WeatherResponse getDataByCityId(@PathVariable("cityId") String cityId);
}
