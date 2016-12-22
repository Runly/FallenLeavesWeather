package com.ranli.fallenleavesweather.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ranli.fallenleavesweather.R;

/**
 * Created by ranly on 2016/12/22.
 */

public class DownloadDialog extends Dialog{
    private Context mContext;
    private TextView mText;

    public DownloadDialog(Context context) {
        super(context);
        mContext = context;
    }

    public DownloadDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    protected DownloadDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.download_progress, null);
        this.setContentView(layout);
        mText = (TextView) layout.findViewById(R.id.text_progress);
    }

    public void setProgress(String text) {
        mText.setText(text);
    }
}
