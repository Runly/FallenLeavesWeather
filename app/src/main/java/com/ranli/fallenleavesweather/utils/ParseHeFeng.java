package com.ranli.fallenleavesweather.utils;

import com.ranli.fallenleavesweather.model.Aqi;
import com.ranli.fallenleavesweather.model.Basic;
import com.ranli.fallenleavesweather.model.Daily;
import com.ranli.fallenleavesweather.model.Day;
import com.ranli.fallenleavesweather.model.Now;
import com.ranli.fallenleavesweather.model.Suggestion;
import com.ranli.fallenleavesweather.model.WeatherInformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseHeFeng {
	
	private WeatherInformation weatherInformation;

	public WeatherInformation parseResponse(String jsonData) {
		weatherInformation = new WeatherInformation();
		try {
			JSONObject jsonObject = new JSONObject(jsonData);
			JSONArray jsonArray = jsonObject.getJSONArray("HeWeather data service 3.0");
			JSONObject weatherInfo = jsonArray.getJSONObject(0);
			if (weatherInfo.has("aqi")) {
				JSONObject aqiJsonObj = weatherInfo.getJSONObject("aqi");
				parseAqi(aqiJsonObj);
			}
			JSONObject basicJsonObj = weatherInfo.getJSONObject("basic");
			JSONArray dailyJsonArray = weatherInfo.getJSONArray("daily_forecast");
			JSONObject nowJsonObj = weatherInfo.getJSONObject("now");
			parseBasic(basicJsonObj);
			parseDaily(dailyJsonArray);
			parseNow(nowJsonObj);
			if (weatherInfo.has("suggestion")) {
				JSONObject suggestionJsonObj = weatherInfo.getJSONObject("suggestion");
				parseSuggestion(suggestionJsonObj);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return weatherInformation;
	}
	
	//解析空气质量指数(aqi)?
	private void parseAqi(JSONObject aqiJsonObj) {
		Aqi aqi = new Aqi();
		try {
			JSONObject cityJsonObj = aqiJsonObj.getJSONObject("city");
			//空气质量指数
			if (cityJsonObj.has("aqi")) {
				aqi.setAqi(cityJsonObj.getString("aqi"));
			} else {
				aqi.setAqi("");
			}
			//一氧化碳1小时平均值(ug/m³)
			if (cityJsonObj.has("co")) {
				aqi.setCo(cityJsonObj.getString("co"));
			} else {
				aqi.setCo("");
			}
			//二氧化氮1小时平均值(ug/m³)
			if (cityJsonObj.has("no2")) {
				aqi.setNo2(cityJsonObj.getString("no2"));
			} else {
				aqi.setNo2("");
			}
			//臭氧1小时平均值(ug/m³)
			if (cityJsonObj.has("o3")) {
				aqi.setO3(cityJsonObj.getString("o3"));
			} else {
				aqi.setO3("");
			}
			//pm10 小时平均值(ug/m³)
			if (cityJsonObj.has("pm10")) {
				aqi.setPm10(cityJsonObj.getString("pm10"));
			} else {
				aqi.setPm10("");
			}
			//pm2.5 1小时平均值(ug/m³)
			if (cityJsonObj.has("pm25")) {
				aqi.setPm25(cityJsonObj.getString("pm25"));
			} else {
				aqi.setPm25("");
			}
			//空气质量类别
			if (cityJsonObj.has("qlty")) {
				aqi.setQlty(cityJsonObj.getString("qlty"));
			} else {
				aqi.setQlty("");
			}
			//二氧化硫1小时平均值(ug/m³)
			if (cityJsonObj.has("so2")) {
				aqi.setSo2(cityJsonObj.getString("so2"));
			} else {
				aqi.setSo2("");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		weatherInformation.setAqi(aqi);
	}
	
	//解析基本信息(basic)
	private void parseBasic(JSONObject basicJsonObj) {
		Basic basic = new Basic();
		try {
			basic.setCity(basicJsonObj.getString("city"));//城市名称
			basic.setId(basicJsonObj.getString("id"));//城市ID
			JSONObject updateJsonObj = basicJsonObj.getJSONObject("update");
			basic.setLoc(updateJsonObj.getString("loc"));//本地更新时间
		} catch (JSONException e) {
			e.printStackTrace();
		}
		weatherInformation.setBasic(basic);
	}
	
	//解析未来七天天气情况
	private void parseDaily(JSONArray dailyJsonArray) {
		JSONObject dayJsonObj;
		Daily daily = new Daily();
		int leng = dailyJsonArray.length();
		Day[] array = new Day[leng];
		try {
			for (int i = 0; i < leng; i++) {
				dayJsonObj = dailyJsonArray.getJSONObject(i);
				JSONObject astroJsonObj = dayJsonObj.getJSONObject("astro");
				Day day = new Day();
				day.setSr(astroJsonObj.getString("sr"));//日出时间
				day.setSs(astroJsonObj.getString("ss"));//日落时间
				JSONObject condJsonObj = dayJsonObj.getJSONObject("cond");
				day.setCode_d(condJsonObj.getString("code_d"));//白天天气代码
				day.setCode_n(condJsonObj.getString("code_n"));//夜晚天气代码
				day.setTxt_d(condJsonObj.getString("txt_d"));//白天天气描述
				day.setTxt_n(condJsonObj.getString("txt_n"));//夜晚天气描述
				day.setDate(dayJsonObj.getString("date"));//日期
				day.setHum(dayJsonObj.getString("hum"));//湿度
				day.setPcpn(dayJsonObj.getString("pcpn"));//降雨量(mm)
				day.setPop(dayJsonObj.getString("pop"));//降水概率
				day.setPres(dayJsonObj.getString("pres"));//气压
				JSONObject tmpJsonObj = dayJsonObj.getJSONObject("tmp");
				day.setMax(tmpJsonObj.getString("max"));//最高气温
				day.setMin(tmpJsonObj.getString("min"));//最低气温
				day.setVis(dayJsonObj.getString("vis"));//能见度
				JSONObject windJsonObj = dayJsonObj.getJSONObject("wind");
				day.setDir(windJsonObj.getString("dir"));//风向
				day.setSc(windJsonObj.getString("sc"));//风力等级
				day.setSpd(windJsonObj.getString("spd"));//风速
				array[i] = day;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		daily.setSevenDays(array);
		weatherInformation.setDaily(daily);
	}

	//解析当前天气情况
	private void parseNow(JSONObject nowJsonObj) {
		Now now = new Now();
		try {
			JSONObject condJsonObj = nowJsonObj.getJSONObject("cond");
			now.setCode(condJsonObj.getString("code"));//天气代码
			now.setTxt(condJsonObj.getString("txt"));//天气描述
			now.setTmp(nowJsonObj.getString("tmp"));//当前温度
			now.setFl(nowJsonObj.getString("fl"));//体感温度
			now.setHum(nowJsonObj.getString("hum"));//湿度
			now.setPcpn(nowJsonObj.getString("pcpn"));//降雨量
			if (nowJsonObj.has("pres")) {
				now.setPres(nowJsonObj.getString("pres"));//压强
			}
			if (nowJsonObj.has("vis")) {
				now.setVis(nowJsonObj.getString("vis"));//能见度
			}
			JSONObject windJsonObj = nowJsonObj.getJSONObject("wind");
			now.setDir(windJsonObj.getString("dir"));//风向
			now.setSc(windJsonObj.getString("sc"));//风力等级
			now.setSpd(windJsonObj.getString("spd"));//风速
		} catch (JSONException e) {
			e.printStackTrace();
		}

		weatherInformation.setNow(now);
	}
	
	//解析生活指数
	private void parseSuggestion(JSONObject suggestionJsonObj) {
		Suggestion suggestion = new Suggestion();
		try {
			//舒适度
			JSONObject comfJsonObj = suggestionJsonObj.getJSONObject("comf");
			suggestion.setComf(comfJsonObj.getString("brf") + "," + comfJsonObj.getString("txt"));
			//洗车指数
			JSONObject cwJsonObj = suggestionJsonObj.getJSONObject("cw");
			suggestion.setCw(cwJsonObj.getString("brf") + "," + cwJsonObj.getString("txt"));
			//穿衣指数
			JSONObject drsgJsonObj = suggestionJsonObj.getJSONObject("drsg");
			suggestion.setDrsg(drsgJsonObj.getString("brf") + "," + drsgJsonObj.getString("txt"));
			//感冒指数
			JSONObject fluJsonObj = suggestionJsonObj.getJSONObject("flu");
			suggestion.setFlu(fluJsonObj.getString("brf") + "," + fluJsonObj.getString("txt"));
			//运动指数
			JSONObject sportJsonObj = suggestionJsonObj.getJSONObject("sport");
			suggestion.setSport(sportJsonObj.getString("brf") + "," + sportJsonObj.getString("txt"));
			//旅游指数
			JSONObject travJsonObj = suggestionJsonObj.getJSONObject("trav");
			suggestion.setTrav(travJsonObj.getString("brf") + "," + travJsonObj.getString("txt"));
			//防晒指数
			JSONObject uvJsonObj = suggestionJsonObj.getJSONObject("uv");
			suggestion.setUv(uvJsonObj.getString("brf") + "," + uvJsonObj.getString("txt"));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		weatherInformation.setSuggestion(suggestion);
	}
}
