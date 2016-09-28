package com.ranli.fallenleavesweather.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ranli.fallenleavesweather.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;


/**
 * Created by Administrator on 2016/9/27.
 */
public class SplashActivity extends BaseActivity{
    private final int SPLASH_DISPLAY_LENGHT = 1500; // 延迟六秒
    private ImageView mImageView;
    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        translucent();
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

        }, SPLASH_DISPLAY_LENGHT);

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
        options.inSampleSize = calculateInSampleSize(options, width, height);
        options.inJustDecodeBounds = false;
        Bitmap src = BitmapFactory.decodeResource(resources, R.drawable.falling_leaves, options);
        imageBitmap = createScaleBitmap(src, width, height);
        mImageView.setImageBitmap(imageBitmap);
    }

    @TargetApi(19)
    private void translucent () {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    private  Bitmap createScaleBitmap(Bitmap src, int dstWidth, int dstHeight) {
        // 如果是放大图片，filter决定是否平滑，如果是缩小图片，filter无影响，我们这里是缩小图片，所以直接设置为false
        Bitmap dst = Bitmap.createScaledBitmap(src, dstWidth, dstHeight, false);
        if (src != dst) { // 如果没有缩放，那么不回收
            src.recycle(); // 释放Bitmap的native像素数组
        }
        return dst;
    }

}
