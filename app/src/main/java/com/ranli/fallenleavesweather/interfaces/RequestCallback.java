package com.ranli.fallenleavesweather.interfaces;

/**
 * Created by Administrator on 2016/10/21 0021.
 */

public interface RequestCallback {
    void onSuccess(String response);
    void onFail(String error);
}
