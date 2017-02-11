package com.ranli.fallenleavesweather.interfaces;


import com.ranli.fallenleavesweather.model.WeatherInformation;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/4 0004.
 */

public interface
HeFengService {
    @GET("weather")
    Observable<WeatherInformation> getWeatherInfo(@Query("city") String cityID, @Query("key") String key);
}
