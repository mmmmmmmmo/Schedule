package com.moon.libbase.download.http;





import com.moon.libbase.BuildConfig;
import com.moon.libbase.download.DownloadListener;
import com.moon.libbase.download.DownloadResponseBody;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * by ry on 2018/11/23.
 */
public class ApiDownloadRetrofit {

    private final Retrofit mRetrofit;
    private OkHttpClient mClient;
    private ApiDownloadService mApiService;
    private DownloadListener downloadListener;

    private Interceptor downloadInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response response = chain.proceed(chain.request());
            return response.newBuilder().body(
                    new DownloadResponseBody(response.body(), downloadListener)).build();
        }
    };

    public ApiDownloadRetrofit(DownloadListener downloadListener) {
        this.downloadListener = downloadListener;
        mClient = new OkHttpClient.Builder()
                .addInterceptor(downloadInterceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//支持RxJava
                .client(mClient)
                .build();

        mApiService = mRetrofit.create(ApiDownloadService.class);
    }

    public ApiDownloadService getApiService() {
        return mApiService;
    }
}
