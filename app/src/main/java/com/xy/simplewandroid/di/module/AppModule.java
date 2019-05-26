package com.xy.simplewandroid.di.module;

import com.xy.simplewandroid.app.WandroidApp;
import com.xy.simplewandroid.core.DataManager;
import com.xy.simplewandroid.core.HttpHelper;
import com.xy.simplewandroid.core.HttpHelperImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final WandroidApp application;

    public AppModule(WandroidApp application) {
        this.application = application;
    }
    @Provides
    @Singleton
    WandroidApp provideApplicationContext() {
        return application;
    }
    @Provides
    @Singleton
    HttpHelper provideHttpHelper(HttpHelperImpl httpHelperImpl) {
        return httpHelperImpl;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper) {
        return new DataManager(httpHelper);
    }
}
