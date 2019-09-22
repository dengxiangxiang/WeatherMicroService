package com.dxx.springcloud.weather.domain;

public class WeatherResponse {

	private static final Long serialVersionUID = 1L;
	private Weather data;
	private String status;
	private String desc;
	public Weather getData() {
		return data;
	}
	public void setData(Weather data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public static Long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
