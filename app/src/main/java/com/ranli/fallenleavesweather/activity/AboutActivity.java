package com.ranli.fallenleavesweather.activity;

import android.Manifest;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ranli.fallenleavesweather.R;
import com.ranli.fallenleavesweather.utils.AsyncTaskUtil;
import com.ranli.fallenleavesweather.utils.VersionJsonUtil;

import im.fir.sdk.FIR;
import im.fir.sdk.VersionCheckCallback;

/**
 * Created by Administrator on 2016/9/30.
 */
public class AboutActivity extends BaseActivity {
    private static final String[] WRITE_PERMISSIONS = new String[] {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    public static final int REQUEST_PERMISSION_LOCATION = 4;
    private String versionCode;
    private String downloadUrl;
    private Handler mHandler = new Handler();
    private AsyncTaskUtil mDownloadAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);

        TextView mAboutTitleText = $(R.id.common_title_bar_Text);
        mAboutTitleText.setText("关于");

        ImageView mBackImage = $(R.id.back);
        mBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        PackageInfo packageInfo;
        try {
            packageInfo = getApplication().getPackageManager().getPackageInfo(getApplication().getPackageName(), 0);
            String str = "落叶天气\nv " + packageInfo.versionName;
            TextView mVersionText = $(R.id.version_text_view);
            mVersionText.setText(str);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        RelativeLayout mUpdateLayout = $(R.id.version_update);
        mUpdateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FIR.checkForUpdateInFIR("087171f5544f2737405ae4ec9f347631",
                        new VersionCheckCallback() {
                    @Override
                    public void onSuccess(String versionJson) {
                        String results[] = VersionJsonUtil.parseVersionJson(versionJson);
                        versionCode = results[0];
                        downloadUrl = results[1];
                        PackageInfo pi;
                        try {
                            pi = getApplication().getPackageManager().getPackageInfo(getApplication().getPackageName(), 0);
                            if (!TextUtils.isEmpty(versionCode) && pi != null) {
                                int localVersionCode = pi.versionCode;

                                //请求写入sdcard权限
                                if (Integer.valueOf(versionCode) > localVersionCode){
                                    if (ContextCompat.checkSelfPermission(AboutActivity.this, WRITE_PERMISSIONS[0])
                                            != PackageManager.PERMISSION_GRANTED) {
                                        ActivityCompat.requestPermissions(AboutActivity.this, WRITE_PERMISSIONS, REQUEST_PERMISSION_LOCATION);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "正在下载...", Toast.LENGTH_SHORT).show();
                                        mDownloadAsyncTask = new AsyncTaskUtil(AboutActivity.this, mHandler);
                                        mDownloadAsyncTask.execute(downloadUrl, "/落叶天气.apk");
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "已是最新版本", Toast.LENGTH_SHORT).show();
                                }

                            }
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(Exception exception) {
                        Toast.makeText(getApplicationContext(), "获取失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onStart() {
                        Toast.makeText(getApplicationContext(), "正在获取...", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFinish() {

                    }

                });
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mDownloadAsyncTask = new AsyncTaskUtil(AboutActivity.this, mHandler);
                mDownloadAsyncTask.execute(downloadUrl, "/落叶天气.apk");
            } else {
                Toast.makeText(this, "没有写入文件权限", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
