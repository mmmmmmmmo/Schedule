package com.moon.libbase.dl.module;

import android.content.Context;

import com.moon.libbase.base.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class BaseAppModule {

    private BaseApplication application;

    public BaseAppModule(BaseApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context providesContext() {
        return application;
    }

    @Singleton
    @Provides
    public BaseApplication provideApplication() {
        return application;
    }
}
