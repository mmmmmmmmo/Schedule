package com.moon.libbase.dl.module;

import com.google.gson.Gson;
import com.moon.libbase.BuildConfig;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


@Module
public class HttpClientModule {
    private static final int HTTP_TIME_OUT = 20;
    @Singleton
    @Provides
    public OkHttpClient provideHttpClient(){
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG){
            logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        }else{
            logger.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return new OkHttpClient.Builder()
                .connectTimeout(HTTP_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(HTTP_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(HTTP_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(logger)
                .build();
    }

    @Singleton
    @Provides
    public Gson provideGson() {
        return new Gson();
    }

}
