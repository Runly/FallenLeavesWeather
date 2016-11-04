---
#落叶天气
------------
###应用介绍
这是一个界面简洁，体积小巧的，可以快捷的查询国内城市天气的App，“落叶天气”得名于它完成于秋天这个落叶的时节，在写这个项目的时候，我一直不知道该给它起个什么样的名字，散步时看到了路边的落叶，于是有个这个名字，天气渐凉，多多关注天气哦~~~

###关于权限
落叶天气界面十分简洁，没有什么花哨的效果，也没有除了看天气以外的其他功能。
在权限上，尽量少的使用不需要的权限。目前的权限有：

	<!--网络定位的权限-->
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>

    <!--读取手机当前状态的权限-->
    <uses-permission android:name="android.permission.READ_PHONE_STATE"/>

    <!--获取手机当前的网络状况-->
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

    <!--用于访问wifi网络信息-->
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>

    <!--获取wifi状态的改变-->
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>

    <!--访问网络-->
    <uses-permission android:name="android.permission.INTERNET"/>

    <!--写入扩展存储，向扩展卡写入数据，用于检查更新是下载安装包-->
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>

###关于网络请求
网络请求部分，到目前为止已经修改过两次：

- **最开始的直接使用HttpURLConnection，这很蠢**

1、每次都new Thread，new Handler消耗过大

2、没有异常处理机制

3、没有缓存机制

4、没有完善的API(请求头,参数,编码,拦截器等)与调试模式

5、没有Https

- **改用Volley进行网络请求**

1、Volley在Android 2.3及以上版本，是封装的HttpURLConnection，而在Android 2.2及以下版本，是封装的HttpClient，另外Volley对OkHttp也是支持的

2、Volley存在一个缓存线程，一个网络请求线程池(默认4个线程)

3、但是，直接使用Volley开发效率会比较低，实际项目中往往需要二次封装

- **改用Retrofit进行网络请求**

1、Retrofit极大的简化了网络请求的操作

2、Retrofit是直接使用OKHttp进行网络请求并不影响你对OkHttp进行配置

```java

	String baseUrl = "https://api.heweather.com/x3/";
            
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    
    HeFengService heFengService = retrofit.create(HeFengService.class);
    
    Call<WeatherInformation> model = heFengService.response(cityid, KEY);
    
    model.enqueue(new Callback<WeatherInformation>() {
        @Override
        public void onResponse(Call<WeatherInformation> call, Response<WeatherInformation> response) {
            weatherInfo = response.body().list.get(0);
            updateUI();
        }

        @Override
        public void onFailure(Call<WeatherInformation> call, Throwable t) {
            final CustomDialog dialog = new CustomDialog(WeatherActivity.this);
            dialog.show();
            dialog.setCustomDialogText("非常抱歉，获取天气数据失败");
            dialog.setCustomOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });
        }
    });


```

###应用截图

<img src="https://github.com/Runly/FallenLeavesWeather/blob/master/screenshot/Screenshot_20161001-013501.png" width = "200" height = "355.6" align=center />
<img src="https://github.com/Runly/FallenLeavesWeather/blob/master/screenshot/Screenshot_20161001-013511.png" width = "200" height = "355.6" align=center />
<img src="https://github.com/Runly/FallenLeavesWeather/blob/master/screenshot/Screenshot_20161001-013535.png" width = "200" height = "355.6" align=center />
<img src="https://github.com/Runly/FallenLeavesWeather/blob/master/screenshot/Screenshot_20161001-013544.png" width = "200" height = "355.6" align=center />



<img src="https://github.com/Runly/FallenLeavesWeather/blob/master/screenshot/Screenshot_20161001-013554.png" width = "200" height = "355.6" align=center />
<img src="https://github.com/Runly/FallenLeavesWeather/blob/master/screenshot/Screenshot_20161001-013606.png" width = "200" height = "355.6" align=center />
<img src="https://github.com/Runly/FallenLeavesWeather/blob/master/screenshot/Screenshot_20161001-013621.png" width = "200" height = "355.6" align=center />
<img src="https://github.com/Runly/FallenLeavesWeather/blob/master/screenshot/Screenshot_20161001-165636.png" width = "200" height = "355.6" align=center />

###版本信息
v 1.4.0 (Build 7)

- 不再使用org.json,改用Gson进行Json解析

- 不再使用Volley进行网络请求，改用Retrofit

- 删除不再使用的Model，重构部分代码

v 1.3.1 (Build 6)

- 不直接使用HttpURLConnection，改用Volley进行网络请求

- 修复当前城市温度可能为null的bug

v 1.3.0 (Build 5)

- UI上做了一下细微的调节

- 修复一个bug

- 重构部分代码

v 1.2.0 (Build 4)

- 修复更新功能的bug

v 1.1.1 (Build 3)

- 添加应用内更新功能

v 1.1.0 (Build 1)

- 添加了设置

v 1.0.0 (Build 1)

- 第一版

下载地址： http://fir.im/leavesweather

###鸣谢
**感谢以下开源项目的支持**

Retrofit -- https://github.com/square/retrofit

Gson -- https://github.com/google/gson

MPAndroidChart -- https://github.com/PhilJay/MPAndroidChart

XRefreshView -- https://github.com/huxq17/XRefreshView

CityPicker -- https://github.com/zaaach/CityPicker

以及众多大神博主们的知识分享

**感谢以下资源的支持**

阿里矢量图标库iconfont -- http://iconfont.cn

高德地图定位 -- http://lbs.amap.com/api

和风天气接口服务 -- http://heweather.com

fir.im应用托管分发服务的平台 -- http://fir.im

###版权
应用图标图片资源据来自网络公开资源，如有侵权，联系ranly314@gmail.com








