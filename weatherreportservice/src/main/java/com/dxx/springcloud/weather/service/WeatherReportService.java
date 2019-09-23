package com.dxx.springcloud.weather.service;

import com.dxx.springcloud.weather.domain.Weather;

public interface WeatherReportService {
	Weather getDataByCityId(String cityId);
}
