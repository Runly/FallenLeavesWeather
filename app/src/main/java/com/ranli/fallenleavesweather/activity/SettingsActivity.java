package com.ranli.fallenleavesweather.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ranli.fallenleavesweather.R;

/**
 * Created by Administrator on 2016/9/29.
 */
public class SettingsActivity extends BaseActivity {
    private ImageView mBackImage;
    private ImageView mRefreshImage;
    private RelativeLayout mRelativeLayout;
    private boolean isRefresh;
    private SharedPreferences spf;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.settings_activity_layout);
        initUI();
    }

    private void initUI() {
        mBackImage = $(R.id.back);
        mBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        spf = getSharedPreferences("settings", MODE_PRIVATE);
        editor = spf.edit();
        isRefresh = spf.getBoolean("is_refresh", true);
        mRefreshImage = $(R.id.is_refresh_image);
        if (isRefresh) {
            mRefreshImage.setImageResource(R.drawable.on);
        } else {
            mRefreshImage.setImageResource(R.drawable.off);
        }
        mRefreshImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRefresh) {
                    mRefreshImage.setImageResource(R.drawable.off);
                    isRefresh = false;
                    editor.putBoolean("is_refresh", false);
                    editor.apply();
                } else {
                    mRefreshImage.setImageResource(R.drawable.on);
                    isRefresh = true;
                    editor.putBoolean("is_refresh", true);
                    editor.apply();
                }
            }
        });


        mRelativeLayout = $(R.id.refresh_interval);
        mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
