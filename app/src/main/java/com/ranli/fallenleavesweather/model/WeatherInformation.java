package com.ranli.fallenleavesweather.model;

import java.io.Serializable;

public class WeatherInformation implements Serializable{

	private static final long serialVersionUID = 8088539235914663819L;

	private Aqi aqi;//��������ָ��
	
	private Basic basic;//���л�����Ϣ
	
	private Daily daily;//�����������
	
	private Now now;//��ǰʱ������״��
	
	private Suggestion suggestion;//����ָ��

	public Aqi getAqi() {
		return aqi;
	}

	public void setAqi(Aqi aqi) {
		this.aqi = aqi;
	}

	public Basic getBasic() {
		return basic;
	}

	public void setBasic(Basic basic) {
		this.basic = basic;
	}

	public Daily getDaily() {
		return daily;
	}

	public void setDaily(Daily daily) {
		this.daily = daily;
	}

	public Now getNow() {
		return now;
	}

	public void setNow(Now now) {
		this.now = now;
	}

	public Suggestion getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(Suggestion suggestion) {
		this.suggestion = suggestion;
	}
	
	
	
	
}
