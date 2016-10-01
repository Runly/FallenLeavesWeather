package com.ranli.fallenleavesweather.util;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/10/1.
 */
public class VersinJsonUtil {

    public static String[] parseVersionJson(String versionJson) {
        String results[] = new String[2];
        try {
            JSONObject versionInfo = new JSONObject(versionJson);
            results[0] = versionInfo.getString("version");
            Log.d("parseVersionJson", results[0]);
            results[1] = versionInfo.getString("installUrl");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return results;
    }
}
