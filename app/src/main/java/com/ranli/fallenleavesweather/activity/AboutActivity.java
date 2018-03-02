package com.ranli.fallenleavesweather.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ranli.fallenleavesweather.R;
import com.ranli.fallenleavesweather.application.app;
import com.ranli.fallenleavesweather.model.Fir;
import com.ranli.fallenleavesweather.utils.HttpUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//import im.fir.sdk.FIR;
//import im.fir.sdk.VersionCheckCallback;
import okhttp3.ResponseBody;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/30.
 */
public class AboutActivity extends BaseActivity {
    public static final int REQUEST_PERMISSION_LOCATION = 4;

    private static final String FIR_TOKEN = "087171f5544f2737405ae4ec9f347631";
    private static final String[] WRITE_PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private String versionCode;
    private String downloadUrl;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);

        TextView mAboutTitleText = $(R.id.common_title_bar_Text);
        mAboutTitleText.setText("关于");

        ImageView mBackImage = $(R.id.back);
        mBackImage.setOnClickListener(v -> finish());

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
//        mUpdateLayout.setOnClickListener(v -> FIR.checkForUpdateInFIR(FIR_TOKEN, getVersionCheckCallback()));
    }

//    private VersionCheckCallback getVersionCheckCallback() {
//        return new VersionCheckCallback() {
//            @Override
//            public void onSuccess(String versionJson) {
//                Fir fir = new Gson().fromJson(versionJson, Fir.class);
//                versionCode = fir.version;
//                downloadUrl = fir.direct_install_url;
//
//                PackageInfo pi = getPackageInfo();
//                if (!TextUtils.isEmpty(versionCode) && pi != null) {
//                    int localVersionCode = pi.versionCode;
//                    //请求写入sdcard权限
//                    if (Integer.valueOf(versionCode) > localVersionCode) {
//                        if (ContextCompat.checkSelfPermission(AboutActivity.this, WRITE_PERMISSIONS[0]) != PackageManager.PERMISSION_GRANTED) {
//                            ActivityCompat.requestPermissions(AboutActivity.this, WRITE_PERMISSIONS, REQUEST_PERMISSION_LOCATION);
//                        } else {
//                            downloadFile();
//                        }
//                    } else {
//                        Toast.makeText(getApplicationContext(), "已是最新版本", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onFail(Exception exception) {
//                Toast.makeText(getApplicationContext(), "获取更新失败", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onStart() {
//                Toast.makeText(getApplicationContext(), "正在获取更新...", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        };
//    }

    private void downloadFile() {
        mProgressDialog = new ProgressDialog(AboutActivity.this);
        mProgressDialog.setProgress(0);
//        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setMax(100);
        mProgressDialog.show();

        HttpUtils.getFirService("http://download.fir.im/v2/app/install/")
                .downloadFile(downloadUrl)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        this::writeResponseBodyToDisk,
                        error -> runOnUiThread(() -> {
                            error.printStackTrace();
                            Toast.makeText(AboutActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
                        }),
                        () -> runOnUiThread(() -> {
                            mProgressDialog.cancel();
                            Toast.makeText(AboutActivity.this, "已下载到目录\\Download", Toast.LENGTH_LONG).show();
                            openFile(new File(Environment.getExternalStorageDirectory().toString() + "/Download/落叶天气.apk"));
                        })
                );
    }

    private boolean writeResponseBodyToDisk(ResponseBody body) {
        File futureStudioIconFile = new File(Environment.getExternalStorageDirectory().toString() + "/Download/落叶天气.apk");

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            byte[] fileReader = new byte[2048];

            long fileSize = body.contentLength();
            long fileSizeDownloaded = 0;

            inputStream = body.byteStream();
            outputStream = new FileOutputStream(futureStudioIconFile);

            while (true) {
                int read = inputStream.read(fileReader);

                if (read == -1) {
                    break;
                }

                outputStream.write(fileReader, 0, read);

                fileSizeDownloaded += read;

                Log.d("WriteToDisk", "file download: " + fileSizeDownloaded + " of " + fileSize);
                long finalFileSizeDownloaded = fileSizeDownloaded;
                runOnUiThread(() -> mProgressDialog.setProgress((int) ((double) finalFileSizeDownloaded / (double) fileSize * 100)));
            }

            outputStream.flush();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                downloadFile();
            } else {
                Toast.makeText(this, "没有写入文件权限", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void openFile(File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        app.getContext().startActivity(intent);
    }

    private PackageInfo getPackageInfo() {
        PackageInfo pi = null;
        try {
            pi = getApplication().getPackageManager().getPackageInfo(getApplication().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pi;
    }

}
