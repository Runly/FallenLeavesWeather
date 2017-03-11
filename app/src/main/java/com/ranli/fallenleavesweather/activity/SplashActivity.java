package com.ranli.fallenleavesweather.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ranli.fallenleavesweather.R;
import com.ranli.fallenleavesweather.db.DBManager;
import com.ranli.fallenleavesweather.db.WeatherInformationDbManager;
import com.ranli.fallenleavesweather.utils.ImageCompression;

import java.lang.ref.WeakReference;


/**
 * Created by Administrator on 2016/9/27.
 */
public class SplashActivity extends BaseActivity{
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_activity);
        mImageView = $(R.id.falling_leaves);
        showPhoto();

        final MyHandler handler = new MyHandler(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                initData();
                Message message = new Message();
                message.what = 1;
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendMessage(message);
            }
        }).start();
    }

    public void initData() {
        DBManager dbManager = new DBManager(this);
        dbManager.copyDBFile();
        WeatherInformationDbManager weatherDbManager = WeatherInformationDbManager.getInstance(this);
        weatherDbManager.copyDBFile(this);
    }

    public void showPhoto() {
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        final int width = dm.widthPixels; //获取屏幕宽度

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, R.mipmap.falling_leaves, options);
        //利用原图的高宽比，求得需要的高度 = 原图高度 / 原图宽度 * 屏幕宽度
        double temp = (double)options.outHeight / (double)options.outWidth * (double)width;
        int height = (int)temp;
        options.inSampleSize = ImageCompression.calculateInSampleSize(options, width, height);
        options.inJustDecodeBounds = false;
        Bitmap src = BitmapFactory.decodeResource(resources, R.mipmap.falling_leaves, options);
        Bitmap imageBitmap = ImageCompression.createScaleBitmap(src, width, height);
        mImageView.setImageBitmap(imageBitmap);
    }

    private static class MyHandler extends Handler {

        private final WeakReference<SplashActivity> mActivity;

        public MyHandler(SplashActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            SplashActivity activity = mActivity.get();
            if(activity != null) {
                switch (msg.what) {
                    case 1:
                        Intent mainIntent = new Intent(activity, WeatherActivity.class);
                        activity.startActivity(mainIntent);
                        activity.finish();
                        break;
                    default:
                        break;
                }
            }

        }
    }

}
