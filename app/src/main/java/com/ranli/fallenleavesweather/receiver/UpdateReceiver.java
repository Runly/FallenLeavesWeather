package com.ranli.fallenleavesweather.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ranli.fallenleavesweather.service.UpdateService;

/**
 * Created by Administrator on 2016/9/19.
 */
public class UpdateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, UpdateService.class);
        context.startService(i);
    }

}
