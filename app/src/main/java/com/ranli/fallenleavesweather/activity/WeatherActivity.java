package com.ranli.fallenleavesweather.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.andview.refreshview.XRefreshView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.ranli.fallenleavesweather.R;
import com.ranli.fallenleavesweather.db.DBManager;
import com.ranli.fallenleavesweather.db.WeatherInformationDbManager;
import com.ranli.fallenleavesweather.dialog.CustomDialog;
import com.ranli.fallenleavesweather.interfaces.RequestCallback;
import com.ranli.fallenleavesweather.model.Aqi;
import com.ranli.fallenleavesweather.model.Day;
import com.ranli.fallenleavesweather.model.Now;
import com.ranli.fallenleavesweather.model.Suggestion;
import com.ranli.fallenleavesweather.model.WeatherInformation;
import com.ranli.fallenleavesweather.utils.ChooseIcon;
import com.ranli.fallenleavesweather.utils.HttpRequestUtils;
import com.ranli.fallenleavesweather.utils.ParseHeFeng;
import com.ranli.fallenleavesweather.utils.StringUtils;
import com.ranli.fallenleavesweather.view.SmileyHeaderView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import static android.R.attr.key;


/**
 * Created by Administrator on 2016/9/10.
 */
public class WeatherActivity extends BaseActivity {

    private static final String[] LOCATION_PERMISSIONS = new String[] {
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private static final int REQUEST_PERMISSION_LOCATION = 3;
    private static final int START_CITY_PICKER = 1;
    private static final int REQUEST_FINISHED = 2;
    private static final int HALF_HOUR = 30 * 60 * 1000;
    private static final String KEY = "b23a0a8f079147e1a1d809faa44c8b87";
    private static final int SPLASH_DISPLAY_LENGTH = 350; // 延迟350毫秒
    private static final int LIMITED_USAGE = 404;
    private DrawerLayout mDrawerLayout;
    private XRefreshView xRefreshView;
    private TextView mCityNameText;
    private TextView mDateOfWeather;
    private ImageView mNowImage;
    private TextView mNowWeatherTxt;
    private TextView mNowTemp;
    private TextView mNowTempFl;
    private TextView mNowHum;
    private TextView mNowAqiAndPm25;
    private LineChart mLineChart;
    private LinearLayout mDaysWeek;
    private LinearLayout mDaysWeatherIcon;
    private LinearLayout mDaysWeatherTxt;
    private LinearLayout mNightsWeatherIcon;
    private LinearLayout mNightsWeatherTxt;
    private LinearLayout mDaysDate;
    private LinearLayout mDaysWindDir;
    private LinearLayout mDaysWindSc;
    private LinearLayout mDaysRainPop;
    private TextView mShuShiTitle;
    private TextView mShuShiTxt;
    private TextView mChuanYiTile;
    private TextView mChuanYiTxt;
    private TextView mFangShaiTile;
    private TextView mFangShaiTxt;
    private TextView mYunDongTile;
    private TextView mYunDongTxt;
    private TextView mXiCheTile;
    private TextView mXiCheTxt;
    private TextView mLvYouTile;
    private TextView mLvYouTxt;
    private TextView mGanMaoTile;
    private TextView mGanMaoTxt;
    private CustomDialog mDialog;
    private WeatherInformation weatherInfo;
    private WeatherInformationDbManager dbManager;
    private AMapLocationClient mLocationClient;
    private String location = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.weather_layout);
        translucent();

        dbManager = WeatherInformationDbManager.getInstance(this);
        initUIComponents();
        weatherInfo = dbManager.loadWeatherInformation();
        long time = System.currentTimeMillis();
        SharedPreferences settings = getSharedPreferences("settings", MODE_PRIVATE);
        boolean isRefresh = settings.getBoolean("is_refresh", true);
        int interval = settings.getInt("refresh_interval", HALF_HOUR);
        if (weatherInfo != null && ((time - loadTime()) < interval)) {
                updateUI();
        } else if(isRefresh) {
                String cityID;
                cityID = loadCityID();
                if (!TextUtils.isEmpty(cityID)) {
                    showWeather(cityID);
                } else if (ContextCompat.checkSelfPermission(this, LOCATION_PERMISSIONS[0])
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(WeatherActivity.this, LOCATION_PERMISSIONS, REQUEST_PERMISSION_LOCATION);
                } else {
                    getLocationAndShowWeather();
                }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocationAndShowWeather();
            } else {
                final CustomDialog dialog = new CustomDialog(this);
                dialog.show();
                dialog.setCustomDialogText("当前没有选中的城市，请选择城市.");
                dialog.setCustomOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(WeatherActivity.this, CityPickerActivity.class);
                        startActivityForResult(intent, START_CITY_PICKER);
                        dialog.cancel();
                    }
                });
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case START_CITY_PICKER:
                    String cityId = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                    if (cityId != null) {
                        showWeather(cityId);
                    }
                    break;
            }
        }

    }

    private void translucent () {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void initUIComponents() {

        mDrawerLayout = $(R.id.drawer_layout);
        LinearLayout mLocationLinearLayout = $(R.id.navigation_location);
        mLocationLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawers();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Intent intent = new Intent(WeatherActivity.this, CityPickerActivity.class);
                        startActivityForResult(intent, START_CITY_PICKER);
                    }

                }, SPLASH_DISPLAY_LENGTH);

            }
        });
        LinearLayout mSettingsLinearLayout = $(R.id.navigation_settings);
        mSettingsLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawers();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Intent intent = new Intent(WeatherActivity.this, SettingsActivity.class);
                        startActivity(intent);
                    }

                }, SPLASH_DISPLAY_LENGTH);

            }
        });
        LinearLayout mAboutLinearLayout = $(R.id.navigation_about);
        mAboutLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawers();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Intent intent = new Intent(WeatherActivity.this, AboutActivity.class);
                        startActivity(intent);
                    }

                }, SPLASH_DISPLAY_LENGTH);
            }
        });



        xRefreshView = $(R.id.custom_view);
        xRefreshView.setCustomHeaderView(new SmileyHeaderView(this));
        xRefreshView.setPullRefreshEnable(true);
        xRefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                String cityID;
                cityID = loadCityID();
                if (!TextUtils.isEmpty(cityID)){
                    showWeather(cityID);
                }
                xRefreshView.stopRefresh();
            }

            @Override
            public void onLoadMore(boolean isSilence) {

            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });

        mCityNameText = $(R.id.county_name);
        mCityNameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherActivity.this, CityPickerActivity.class);
                startActivityForResult(intent, START_CITY_PICKER);
            }
        });

        ImageView mChooseCounty = $(R.id.choose_county);
        mChooseCounty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        ImageView mRefreshWeather = $(R.id.refresh_weather);
        mRefreshWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityID;
                cityID = loadCityID();
                if (!TextUtils.isEmpty(cityID)){
                    showWeather(cityID);
                }
            }
        });

        mDateOfWeather = $(R.id.date_of_weather);
        mNowImage = $(R.id.now_image);
        mNowWeatherTxt = $(R.id.now_weather_txt);
        mNowTemp = $(R.id.now_temp);
        mNowTempFl = $(R.id.now_temp_fl);
        mNowHum = $(R.id.now_hum);
        mNowAqiAndPm25 = $(R.id.now_aqi);
        mLineChart = $(R.id.line_chart);
        mDaysWeek = $(R.id.days_week);
        mDaysWeatherIcon = $(R.id.days_weather_icon);
        mDaysWeatherTxt = $(R.id.days_weather_txt);
        mNightsWeatherIcon = $(R.id.nights_weather_icon);
        mNightsWeatherTxt = $(R.id.nights_weather_txt);
        mDaysDate = $(R.id.days_date);
        mDaysWindDir = $(R.id.days_wind_dir);
        mDaysWindSc = $(R.id.days_wind_sc);
        mDaysRainPop = $(R.id.days_rain_pop);
        mShuShiTitle = $(R.id.shu_shi_title);
        mShuShiTxt = $(R.id.shu_shi);
        mChuanYiTile = $(R.id.chuan_yi_title);
        mChuanYiTxt = $(R.id.chuan_yi);
        mFangShaiTile = $(R.id.fang_shai_title);
        mFangShaiTxt = $(R.id.fang_sai);
        mYunDongTile = $(R.id.yun_dong_title);
        mYunDongTxt = $(R.id.yun_dong);
        mXiCheTile = $(R.id.xi_che_title);
        mXiCheTxt = $(R.id.xi_che);
        mLvYouTile = $(R.id.lv_you_title);
        mLvYouTxt = $(R.id.lv_you);
        mGanMaoTile = $(R.id.gan_mao_title);
        mGanMaoTxt = $(R.id.gan_mao);

    }

    private void getLocationAndShowWeather() {

            mLocationClient = new AMapLocationClient(this);
            //初始化定位参数
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位间隔,单位毫秒,默认为2000ms
            mLocationOption.setInterval(2000);
            //设置定位参数
            mLocationClient.setLocationOption(mLocationOption);

            mLocationClient.setLocationListener(new AMapLocationListener() {
                @Override
                public void onLocationChanged(AMapLocation aMapLocation) {
                    if (aMapLocation != null) {
                        if (aMapLocation.getErrorCode() == 0) {
                            //定位成功回调信息，设置相关消息
                            String city = aMapLocation.getCity();
                            String district = aMapLocation.getDistrict();
                            location = StringUtils.extractLocation(city, district);
                            if (!TextUtils.isEmpty(location)) {
                                String cityID = queryCityID(location);
                                if (!TextUtils.isEmpty(cityID)) {
                                    showWeather(cityID);
                                    mLocationClient.stopLocation();
                                }
                            } else {
                                showWeather("CN101010100");
                                mLocationClient.stopLocation();
                            }
                        } else {
                            //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                            Log.e("AmapError","location Error, ErrCode:"
                                    + aMapLocation.getErrorCode() + ", errInfo:"
                                    + aMapLocation.getErrorInfo());
                            final CustomDialog dialog = new CustomDialog(WeatherActivity.this);
                            dialog.show();
                            dialog.setCustomDialogText("很可惜，定位失败了，没关系，你可以手动选择城市~~~");
                            dialog.setCustomOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(WeatherActivity.this, CityPickerActivity.class);
                                    startActivityForResult(intent,START_CITY_PICKER);
                                    dialog.cancel();
                                }
                            });
                            mLocationClient.stopLocation();
                        }
                    }
                }
            });
            mLocationClient.startLocation();
    }

    private void showWeather(final String cityid) {
        saveCityID(cityid);

        if (mDialog != null) {
            mDialog.cancel();
        }
        if (isNetworkAvailable()) {
            String url = "https://api.heweather.com/x3/weather?cityid=" + cityid +"&key=" + KEY;
            HttpRequestUtils.getInstance().requestFromHeWeather(
                    url,
                    new RequestCallback() {
                        @Override
                        public void onSuccess(String response) {
                            ParseHeFeng parseHeFeng = new ParseHeFeng();
                            weatherInfo = parseHeFeng.parseResponse(response);
                            updateUI();
                        }

                        @Override
                        public void onFail(String error) {
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
                    }

            );
        } else {
            mDialog = new CustomDialog(this);
            mDialog.show();
            mDialog.setCustomDialogText("有网络吗？你如果不检查网络设置，我可能还会出现，返回键可以让暂时我消失~~~");
            mDialog.setCustomOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showWeather(cityid);
                }
            });
        }

//        Intent intent = new Intent(this, UpdateService.class);
//        startService(intent);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    private void updateUI() {
        SQLiteDatabase db = dbManager.getDatabase();
        ChooseIcon chooseIcon = ChooseIcon.getInstance();
        Map<String, Integer> iconMap = chooseIcon.getIconMap();

        //设置更新时间和城市
        String[] arr = weatherInfo.getBasic().getLoc().split(" ");
        String temp = arr[0] +"\n" + arr[1] + "更新";
        mDateOfWeather.setText(temp);
        mCityNameText.setText(weatherInfo.getBasic().getCity());

        //设置当前天气状况
        Now now = weatherInfo.getNow();
        Cursor cursorNow = db.query("weather", new String[] {"weather_code", "weather_icon"},
                "weather_code = ?", new String[] {now.getCode()}, null, null, null);
        if (cursorNow  != null && cursorNow.moveToFirst()) {
            String iconTxt = cursorNow.getString(cursorNow.getColumnIndex("weather_icon"));
            int src = iconMap.get(iconTxt);
            mNowImage.setImageResource(src);
            cursorNow.close();
        }
        mNowWeatherTxt.setText(now.getTxt());
        temp = now.getTmp() + "°";
        mNowTemp.setText(temp);
        temp = "体感" + now.getFl() + "°   ";
        mNowTempFl.setText(temp);
        temp = "   湿度" + now.getHum() + "%";
        mNowHum.setText(temp);

        //设置空气指数
        Aqi aqi = weatherInfo.getAqi();
        if (aqi != null) {
            temp = "空气：" + aqi.getQlty() + "      Pm2.5：" + aqi.getPm25();
        } else {
            temp = "空气：无数据" + "      Pm2.5：无数据" ;
        }
        mNowAqiAndPm25.setText(temp);

        //设置六天天气情况
        Day[] sevenDays = weatherInfo.getDaily().getSevenDays();
        //画出折线图
        LineData lineData = new LineData();
        List<Integer> temps = new ArrayList<>();
        for (int i =0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                if (i == 0) {
                    temps.add(Integer.valueOf(sevenDays[j].getMax()));
                } else if (i == 1) {
                    temps.add(Integer.valueOf(sevenDays[j].getMin()));
                }
            }
            LineDataSet lineDataSet = getLineDataSet(6, temps);
            lineData.addDataSet(lineDataSet);
            temps.clear();
        }
        showChart(mLineChart, lineData);

        int width = getAndroidScreenProperty();
        for(int i = 0; i < mDaysWeatherTxt.getChildCount(); i++) {
            //设置六天是星期几
            TextView weekTxt = (TextView) mDaysWeek.getChildAt(i);
            if (i == 0) {
                weekTxt.setText("今天");
            } else {
                weekTxt.setText(dayOfWeek(i));
            }
            //设置六天白天天气图标
            ImageView dayWeatherIcon = (ImageView) mDaysWeatherIcon.getChildAt(i);
            Cursor cursorDay = db.query("weather", new String[] {"weather_code", "weather_icon"},
                    "weather_code = ?", new String[] {sevenDays[i].getCode_d()}, null, null, null);
            if (cursorDay != null && cursorDay.moveToFirst()) {
                String iconTxt = cursorDay.getString(cursorDay.getColumnIndex("weather_icon"));
                int src = iconMap.get(iconTxt);
                dayWeatherIcon.setImageResource(src);
                cursorDay.close();
            }
            //设置六天白天的天气描述
            TextView dayWeatherTxt = (TextView) mDaysWeatherTxt.getChildAt(i);
            dayWeatherTxt.setWidth(width/6);
            dayWeatherTxt.setText(sevenDays[i].getTxt_d());

            //设置六天夜间天气图标
            ImageView nightWeatherIcon = (ImageView) mNightsWeatherIcon.getChildAt(i);
            Cursor cursorNight = db.query("weather", new String[] {"weather_code", "weather_icon"},
                    "weather_code = ?", new String[] {sevenDays[i].getCode_n()}, null, null, null);
            if (cursorNight != null && cursorNight.moveToFirst()) {
                String iconTxt = cursorNight.getString(cursorNight.getColumnIndex("weather_icon"));
                int src = iconMap.get(iconTxt);
                nightWeatherIcon.setImageResource(src);
                cursorNight.close();
            }
            //设置六天晚上的天气描述
            TextView nightWeatherTxt = (TextView) mNightsWeatherTxt.getChildAt(i);
            nightWeatherTxt.setWidth(width/6);
            nightWeatherTxt.setText(sevenDays[i].getTxt_n());
            //设置六天的日期
            String[] array = sevenDays[i].getDate().split("-");
            String date = array[1] + "-" + array[2];
            TextView dateTxt = (TextView) mDaysDate.getChildAt(i);
            dateTxt.setText(date);
            //设置六天的风向
            TextView windDir = (TextView) mDaysWindDir.getChildAt(i);
            windDir.setWidth(width/6);
            StringBuffer str = new StringBuffer(sevenDays[i].getDir());
            if (str.length() > 4) {
                str.insert(4, "\n");
            }
            windDir.setText(str);
            //设置六天的风力
            TextView windSc = (TextView) mDaysWindSc.getChildAt(i);
            windSc.setWidth(width/6);
            windSc.setText(sevenDays[i].getSc());
            //设置六天降水概率
            TextView rainPop = (TextView) mDaysRainPop.getChildAt(i);
            rainPop.setWidth(width/6);
            String pop = sevenDays[i].getPop() + "%";
            rainPop.setText(pop);
        }

        //设置生活指数
        Suggestion suggestion = weatherInfo.getSuggestion();
        if (suggestion != null) {
            //舒适指数
            arr = suggestion.getComf().split(",");
            temp = "舒适指数    " + arr[0];
            mShuShiTitle.setText(temp);
            mShuShiTxt.setText(arr[1]);
            //穿衣指数
            arr = suggestion.getDrsg().split(",");
            temp = "穿衣指数    " + arr[0];
            mChuanYiTile.setText(temp);
            mChuanYiTxt.setText(arr[1]);
            //防晒指数
            arr = suggestion.getUv().split(",");
            temp = "防晒指数    " + arr[0];
            mFangShaiTile.setText(temp);
            mFangShaiTxt.setText(arr[1]);
            //运动指数
            arr = suggestion.getSport().split(",");
            temp = "运动指数    " + arr[0];
            mYunDongTile.setText(temp);
            mYunDongTxt.setText(arr[1]);
            //洗车指数
            arr = suggestion.getCw().split(",");
            temp = "洗车指数    " + arr[0];
            mXiCheTile.setText(temp);
            mXiCheTxt.setText(arr[1]);
            //旅游指数
            arr = suggestion.getTrav().split(",");
            temp = "旅游指数    " + arr[0];
            mLvYouTile.setText(temp);
            mLvYouTxt.setText(arr[1]);
            //感冒指数
            arr = suggestion.getFlu().split(",");
            temp = "感冒指数    " + arr[0];
            mGanMaoTile.setText(temp);
            mGanMaoTxt.setText(arr[1]);
        } else {
            mShuShiTitle.setText("舒适指数    无数据");
            mShuShiTxt.setText("");
            mChuanYiTile.setText("穿衣指数   无数据");
            mChuanYiTxt.setText("");
            mFangShaiTile.setText("防晒指数    无数据");
            mFangShaiTxt.setText("");
            mXiCheTile.setText("洗车指数    无数据");
            mXiCheTxt.setText("");
            mYunDongTile.setText("运动指数    无数据");
            mYunDongTxt.setText("");
            mLvYouTile.setText("旅游指数    无数据");
            mLvYouTxt.setText("");
            mGanMaoTile.setText("感冒指数    无数据");
            mGanMaoTxt.setText("");
        }
        WeatherInformationDbManager.getInstance(this).saveWeatherInformation(weatherInfo);
    }

    private void showChart(LineChart lineChart, LineData lineData) {
        lineChart.setDrawBorders(false);  //是否在折线图上添加边框

        // no description text
        lineChart.setNoDataText(""); // this is the top line
        lineChart.setNoDataTextDescription("");
        lineChart.setDescription("");// 数据描述
        // 如果没有数据的时候，会显示这个，类似listview的emtpyview
        lineChart.setNoDataTextDescription("");
        lineChart.invalidate();
        lineChart.setVisibility(View.VISIBLE);

        // enable / disable grid background
        lineChart.setDrawGridBackground(false); // 是否显示表格颜色
        lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度

        // enable touch gestures
        lineChart.setTouchEnabled(false); // 设置是否可以触摸

        // enable scaling and dragging
        lineChart.setDragEnabled(false);// 是否可以拖拽
        lineChart.setScaleEnabled(false);// 是否可以缩放

        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(false);//


        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);

        //lineChart.setBackgroundColor(color);// 设置背景

        //是否显示x轴，以及与x轴平行的直线
        XAxis x = lineChart.getXAxis();
        x.setDrawGridLines(false);
        x.setDrawAxisLine(false);
        x.setDrawLabels(false);

        //是否显示y轴，以及与y轴平行的直线，y轴有左右两条
        YAxis yl = lineChart.getAxisLeft();
        yl.setDrawGridLines(false);
        yl.setDrawAxisLine(false);
        yl.setDrawLabels(false);

        YAxis yr = lineChart.getAxisRight();
        yr.setDrawGridLines(false);
        yr.setDrawAxisLine(false);
        yr.setDrawLabels(false);

        // add data
        lineChart.setData(lineData); // 设置数据

        lineChart.animateX(600); // 立即执行的动画,x轴
    }

    private LineDataSet getLineDataSet(int count, List<Integer> temps) {
        // y轴的数据
        ArrayList<Entry> entries = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float value = temps.get(i);
            entries.add(new Entry(i + 0.5f, value));
        }

        // create a dataset and give it a type
        // y轴的数据集合
        LineDataSet lineDataSet = new LineDataSet(entries, "" /*显示在比例图上*/);

        //用y轴的集合来设置参数
        lineDataSet.setLineWidth(2f); // 线宽
        lineDataSet.setColor(Color.WHITE);// 显示颜色
        lineDataSet.setCircleRadius(3f);// 显示的圆形大小
        lineDataSet.setCircleColor(Color.WHITE);// 圆形的颜色
        lineDataSet.setHighLightColor(Color.WHITE); // 高亮的线的颜色

        // create a data object with the datasets
        lineDataSet.setValueTextColor(Color.WHITE);
        lineDataSet.setValueTextSize(12f);
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        ValueFormatter valueFormatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex,
                                            ViewPortHandler viewPortHandler) {
                return String.valueOf((int) value) + "°";
            }
        };
        lineDataSet.setValueFormatter(valueFormatter);

        return lineDataSet;
    }

    private String dayOfWeek(int n){
        String mWay;
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        Integer integer = Integer.parseInt(mWay);
        integer = (integer + n) % 7;
        mWay = integer.toString();
        if("1".equals(mWay)){
            mWay ="日";
        }else if("2".equals(mWay)){
            mWay ="一";
        }else if("3".equals(mWay)){
            mWay ="二";
        }else if("4".equals(mWay)){
            mWay ="三";
        }else if("5".equals(mWay)){
            mWay ="四";
        }else if("6".equals(mWay)){
            mWay ="五";
        }else if("0".equals(mWay)){
            mWay ="六";
        }
        return "周" + mWay;
    }

    private int getAndroidScreenProperty(){
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;// 屏幕宽度（像素）
        //int height= dm.heightPixels; // 屏幕高度（像素）
        float density = dm.density;//屏幕密度（0.75 / 1.0 / 1.5）
        //int densityDpi = dm.densityDpi;//屏幕密度dpi（120 / 160 / 240）
        //屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        //int screenHeight = (int)(height/density);//屏幕高度(dp)
        return (int) (width/density);
    }

    private void saveCityID(String cityID) {
        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        editor.putString("cityID", cityID);
        long time = System.currentTimeMillis();
        editor.putLong("time", time);
        editor.apply();
    }

    private String loadCityID() {
        String cityID;
        SharedPreferences spf = getSharedPreferences("data", MODE_PRIVATE);
        cityID = spf.getString("cityID", "");
        return cityID;
    }

    private long loadTime() {
        long time;
        SharedPreferences spf = getSharedPreferences("data", MODE_PRIVATE);
        time = spf.getLong("time", 0);
        return time;
    }

    private String queryCityID(String city) {
        DBManager dbManager = new DBManager(this);
        dbManager.copyDBFile();
        SQLiteDatabase db = dbManager.getDatabase();
        String cityid = null;
        Cursor cursor = db.query("city", new String[] {"name", "cityid"}, "name = ?",
                new String[] {city}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            cityid = cursor.getString(cursor.getColumnIndex("cityid"));
            cursor.close();
        }
        return cityid;
    }
}
