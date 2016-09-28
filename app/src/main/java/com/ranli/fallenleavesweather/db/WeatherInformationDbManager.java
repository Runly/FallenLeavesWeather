package com.ranli.fallenleavesweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.ranli.fallenleavesweather.model.WeatherInformation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2016/9/24.
 */
public class WeatherInformationDbManager {

    private static final String ASSETS_NAME = "weather_info.db";
    private static final String DB_NAME = "weather_info.db";
    private static final String FILE_NAME = "information_weather.ser";
    private static final int BUFFER_SIZE = 1024;

    private String DB_PATH;
    private String WEATHERINFO_PATH;
    private Context mContext;
    private static WeatherInformationDbManager weatherInformationDbManager;

    private WeatherInformationDbManager(Context context) {
        this.mContext = context;
        DB_PATH = File.separator + "data"
                + Environment.getDataDirectory().getAbsolutePath() + File.separator
                + context.getPackageName() + File.separator + "databases" + File.separator;

        WEATHERINFO_PATH = File.separator + "data"
                + Environment.getDataDirectory().getAbsolutePath() + File.separator
                + context.getPackageName() + File.separator + "weatherinfo" + File.separator;
    }

    public static synchronized WeatherInformationDbManager getInstance(Context context) {
        if(weatherInformationDbManager == null) {
            weatherInformationDbManager = new WeatherInformationDbManager(context);
        }
        return weatherInformationDbManager;
    }

    public void copyDBFile(){
        File dir = new File(DB_PATH);
        if (!dir.exists()){
            dir.mkdirs();
        }
        File dbFile = new File(DB_PATH + DB_NAME);
        if (!dbFile.exists()){
            InputStream is;
            OutputStream os;
            try {
                is = mContext.getResources().getAssets().open(ASSETS_NAME);
                os = new FileOutputStream(dbFile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int length;
                while ((length = is.read(buffer, 0, buffer.length)) > 0){
                    os.write(buffer, 0, length);
                }
                os.flush();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public SQLiteDatabase getDatabase() {
        File file = new File(DB_PATH + DB_NAME);
        return SQLiteDatabase.openOrCreateDatabase(file, null);
    }

    public void saveWeatherInformation(WeatherInformation weatherInformation) {
        File dir = new File(WEATHERINFO_PATH);
        if (!dir.exists()){
            dir.mkdirs();
        }

        File weatherFile = new File(WEATHERINFO_PATH + FILE_NAME);
        FileOutputStream fs;
        try {
            fs = new FileOutputStream(weatherFile);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(weatherInformation);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WeatherInformation loadWeatherInformation() {
        WeatherInformation weatherInformation = null;
        File weatherFile = new File(WEATHERINFO_PATH + FILE_NAME);
        if (weatherFile.exists()){
            FileInputStream fs;
            try {
                fs = new FileInputStream(weatherFile);
                ObjectInputStream is = new ObjectInputStream(fs);
                weatherInformation = (WeatherInformation) is.readObject();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return weatherInformation;
    }

}
