package com.ranli.fallenleavesweather.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ranli.fallenleavesweather.R;

/**
 * Created by Administrator on 2016/9/30.
 */
public class AboutActivity extends BaseActivity {
    private TextView mAboutTitleText;
    private ImageView mBackImage;
    private RelativeLayout mUpdateLayout;
    private TextView mUpdateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);

        mAboutTitleText = $(R.id.common_title_bar_Text);
        mAboutTitleText.setText("关于");

        mBackImage = $(R.id.back);
        mBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mUpdateLayout = $(R.id.version_update);
        mUpdateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mUpdateText = $(R.id.version_update_text);

    }
}
