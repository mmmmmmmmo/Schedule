package com.moon.libbase.download.http;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * by ry on 2018/11/23.
 */
public interface ApiDownloadService {

    @Streaming
    @GET
    Observable<ResponseBody> downloadApk(@Url String url);
}
