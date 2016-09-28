package com.ranli.fallenleavesweather.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ranli.fallenleavesweather.R;

/**
 * Created by Administrator on 2016/9/26.
 */
public class CustomDialog extends Dialog {
    private Context mContext;
    private Button mButton;
    private TextView mText;

    public CustomDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.custom_dialog, null);
        this.setContentView(layout);
        mButton = (Button) layout.findViewById(R.id.dialog_button);
        mText = (TextView) layout.findViewById(R.id.dialog_text);
    }

    public void setCustomDialogText(String text) {
        mText.setText(text);
    }

    public void setCustomOnClickListener(View.OnClickListener listenner) {
        mButton.setOnClickListener(listenner);
    }
}
