package com.ranli.fallenleavesweather.utils;

import com.ranli.fallenleavesweather.interfaces.HeFengService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/11/7 0007.
 */

public class HttpUtils {
    private static final String baseUrl = "https://api.heweather.com/v5/";
    private static final int DEFAULT_TIMEOUT = 5;

    private static HttpUtils httpUtils;
    private static HeFengService heFengService;

    //构造方法私有
    private HttpUtils() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();

        heFengService = retrofit.create(HeFengService.class);
    }

    //在访问HttpUtils时创建单例
    public static synchronized HeFengService getHeFengService() {
        if (httpUtils == null) {
            httpUtils = new HttpUtils();
        }
        return heFengService;
    }
}
