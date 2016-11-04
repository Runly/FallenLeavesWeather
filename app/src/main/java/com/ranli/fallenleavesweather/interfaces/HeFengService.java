package com.ranli.fallenleavesweather.interfaces;

import com.ranli.fallenleavesweather.model.WeatherInformation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/11/4 0004.
 */

public interface HeFengService {
    @GET("weather")
    Call<WeatherInformation> response(@Query("cityid") String cityID, @Query("key") String key);
}
