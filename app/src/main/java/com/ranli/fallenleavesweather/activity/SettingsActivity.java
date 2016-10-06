package com.ranli.fallenleavesweather.activity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ranli.fallenleavesweather.R;

/**
 * Created by Administrator on 2016/9/29.
 */
public class SettingsActivity extends BaseActivity {
    public static final int IMMEDIATELY = 0;
    public static final int HALF_HOUR = 1800000;
    public static final int ONE_HOUR = 3600000;
    public static final int FIVE_HOURS = 18000000;
    public static final int TWELVE_HOURS = 43200000;
    private ImageView mIsRefreshImage;
    private ImageView mIsLocationImage;
    private TextView mIntervalText;
    private boolean isRefresh;
    private boolean isLocation;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.settings_activity_layout);
        initUI();
    }

    private void initUI() {
        ImageView mBackImage = $(R.id.back);
        mBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView mTitleText = $(R.id.common_title_bar_Text);
        mTitleText.setText("设置");

        SharedPreferences spf = getSharedPreferences("settings", MODE_PRIVATE);
        editor = spf.edit();

        isRefresh = spf.getBoolean("is_refresh", true);
        mIsRefreshImage = $(R.id.is_refresh_image);
        mIntervalText = $(R.id.interval_text);
        RelativeLayout mIntervalLayout = $(R.id.refresh_interval);
        if (isRefresh) {
            mIsRefreshImage.setImageResource(R.drawable.on);
        } else {
            mIsRefreshImage.setImageResource(R.drawable.off);
            mIntervalLayout.setClickable(false);
            mIntervalText.setText("");
        }
        mIsRefreshImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRefresh) {
                    mIsRefreshImage.setImageResource(R.drawable.off);
                    isRefresh = false;
                    editor.putBoolean("is_refresh", false);

                } else {
                    mIsRefreshImage.setImageResource(R.drawable.on);
                    isRefresh = true;
                    editor.putBoolean("is_refresh", true);
                }
            }
        });

        int interval = spf.getInt("refresh_interval", -1);
        switch (interval) {
            case IMMEDIATELY:
                mIntervalText.setText("立即刷新");
                break;
            case HALF_HOUR:
                mIntervalText.setText("三十分钟");
                break;
            case ONE_HOUR:
                mIntervalText.setText("一小时");
                break;
            case FIVE_HOURS:
                mIntervalText.setText("五小时");
                break;
            case TWELVE_HOURS:
                mIntervalText.setText("十二小时");
                break;
            default:
        }

        mIntervalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(SettingsActivity.this, R.style.Theme_Light_Dialog);
                View dialogView = LayoutInflater.from(SettingsActivity.this)
                        .inflate(R.layout.settings_dialog, null);
                //获得dialog的window窗口
                Window window = dialog.getWindow();
                //设置dialog在屏幕底部
                window.setGravity(Gravity.BOTTOM);
                //设置dialog弹出时的动画效果，从屏幕底部向上弹出
                window.setWindowAnimations(R.style.dialogStyle);
                window.getDecorView().setPadding(0, 0, 0, 0);
                //获得window窗口的属性
                android.view.WindowManager.LayoutParams lp = window.getAttributes();
                //设置窗口宽度为充满全屏
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                //设置窗口高度为包裹内容
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                //将设置好的属性set回去
                window.setAttributes(lp);
                //将自定义布局加载到dialog上
                dialog.setContentView(dialogView);

                Button mButtonImmediately = (Button) dialogView.findViewById(R.id.bt_immediately);
                mButtonImmediately.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editor.putInt("refresh_interval", IMMEDIATELY);
                        mIntervalText.setText("立即刷新");
                        dialog.cancel();
                    }
                });

                Button mButtonHalfHour = (Button) dialogView.findViewById(R.id.bt_half_hour);
                mButtonHalfHour.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editor.putInt("refresh_interval", HALF_HOUR);
                        mIntervalText.setText("三十分钟");
                        dialog.cancel();
                    }
                });

                Button mButtonOneHour = (Button) dialogView.findViewById(R.id.bt_one_hour);
                mButtonOneHour.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editor.putInt("refresh_interval", ONE_HOUR);
                        mIntervalText.setText("一小时");
                        dialog.cancel();
                    }
                });

                Button mButtonFiveHours = (Button) dialogView.findViewById(R.id.bt_five_hour);
                mButtonFiveHours.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editor.putInt("refresh_interval", FIVE_HOURS);
                        mIntervalText.setText("五小时");
                        dialog.cancel();
                    }
                });

                Button mButtonTwelveHours = (Button) dialogView.findViewById(R.id.bt_twelve_hour);
                mButtonTwelveHours.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editor.putInt("refresh_interval", TWELVE_HOURS);
                        mIntervalText.setText("十二小时");
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

        isLocation = spf.getBoolean("is_location", true);
        mIsLocationImage = $(R.id.is_location_image);
        if (isLocation) {
            mIsLocationImage.setImageResource(R.drawable.on);
        } else {
            mIsLocationImage.setImageResource(R.drawable.off);
        }
        mIsLocationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLocation) {
                    mIsLocationImage.setImageResource(R.drawable.off);
                    isLocation = false;
                    editor.putBoolean("is_location", false);

                } else {
                    mIsLocationImage.setImageResource(R.drawable.on);
                    isLocation = true;
                    editor.putBoolean("is_location", true);
                }
            }
        });
    }

    @Override
    public void finish() {
        editor.apply();
        super.finish();
    }
}
