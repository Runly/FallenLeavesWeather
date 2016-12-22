package com.ranli.fallenleavesweather.interfaces;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by ranly on 2016/12/22.
 */

public interface FirService {
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);
}
