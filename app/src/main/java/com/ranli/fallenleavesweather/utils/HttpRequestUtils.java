package com.ranli.fallenleavesweather.utils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ranli.fallenleavesweather.activity.MyApplication;
import com.ranli.fallenleavesweather.interfaces.RequestCallback;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/21 0021.
 */

public class HttpRequestUtils {
    private static HttpRequestUtils instance;
    private RequestQueue queue;

    private HttpRequestUtils() {
        this.queue = Volley.newRequestQueue(MyApplication.getContext());
    }

    public static synchronized HttpRequestUtils getInstance() {
        if (instance == null) {
            instance = new HttpRequestUtils();
        }
        return instance;
    }

    public void requestFromHeWeather(String url, final RequestCallback requestCallback) {
        Request request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        requestCallback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        requestCallback.onFail(error.toString());
                    }
                }
        );
        queue.add(request);
    }


}
