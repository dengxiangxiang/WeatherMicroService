package com.dxx.springcloud.weather.job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.dxx.springcloud.weather.domain.City;
import com.dxx.springcloud.weather.service.CityDataService;
import com.dxx.springcloud.weather.service.WeatherDataService;

public class WeatherDataSyncJob extends QuartzJobBean{
	private final static Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

	@Autowired
	private WeatherDataService weatherDataService;
	
	@Autowired
	private CityDataService cityDataService;
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		logger.info("开始同步天气数据---------");
		List<City> cityList=null;
		try {
			cityList= cityDataService.listCity();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(cityList!=null) {
			for(City city : cityList) {
				logger.info("正在同步 {} 的天气---------",city.getCityName());
				weatherDataService.syncDataByCityId(city.getCityId());
			}
		}	
		logger.info("结束天气数据同步---------");
	}
	

}
