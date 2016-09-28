package com.ranli.fallenleavesweather.activity;

import android.app.Activity;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2016/9/23.
 */
public class BaseActivity extends Activity {

    @SuppressWarnings("unchecked")
    public final <E extends View> E $(int id) {
        try {
            return (E) findViewById(id);
        } catch (ClassCastException ex) {
            Log.e("TAG", "Could not cast View to concrete class.", ex);
            throw ex;
        }
    }

}
