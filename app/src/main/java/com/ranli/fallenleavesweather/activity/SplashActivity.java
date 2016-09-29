package com.ranli.fallenleavesweather.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ranli.fallenleavesweather.R;
import com.ranli.fallenleavesweather.util.ImageCompression;


/**
 * Created by Administrator on 2016/9/27.
 */
public class SplashActivity extends BaseActivity{
    private final int SPLASH_DISPLAY_LENGH = 1500; // 延迟1500毫秒
    private ImageView mImageView;
    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_activity_layout);
        mImageView = $(R.id.falling_leaves);
        showPhoto();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this,
                        WeatherActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }

        }, SPLASH_DISPLAY_LENGH);

    }

    public void showPhoto() {
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        final int width = dm.widthPixels; //获取屏幕宽度

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, R.drawable.falling_leaves, options);
        //利用原图的高宽比，求得需要的高度 = 原图高度 / 原图宽度 * 屏幕宽度
        double temp = (double)options.outHeight / (double)options.outWidth * (double)width;
        int height = (int)temp;
        options.inSampleSize = ImageCompression.calculateInSampleSize(options, width, height);
        options.inJustDecodeBounds = false;
        Bitmap src = BitmapFactory.decodeResource(resources, R.drawable.falling_leaves, options);
        imageBitmap = ImageCompression.createScaleBitmap(src, width, height);
        mImageView.setImageBitmap(imageBitmap);
    }

}
