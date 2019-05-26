package com.xy.simplewandroid.app;

import android.app.Activity;
import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.xy.simplewandroid.BuildConfig;
import com.xy.simplewandroid.R;
import com.xy.simplewandroid.di.component.AppComponent;
import com.xy.simplewandroid.di.component.DaggerAppComponent;
import com.xy.simplewandroid.di.module.AppModule;
import com.xy.simplewandroid.di.module.HttpModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class WandroidApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> mAndroidInjector;
    private static volatile AppComponent appComponent;

    private static WandroidApp instance;

    public static synchronized WandroidApp getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(instance))
                .httpModule(new HttpModule())
                .build();

        appComponent.inject(this);

        initLogger();
    }

    private void initLogger() {
        //DEBUG版本才打控制台log
        if (BuildConfig.DEBUG) {
            Logger.addLogAdapter(new AndroidLogAdapter(PrettyFormatStrategy.newBuilder().
                    tag(getString(R.string.app_name)).build()));
        }
    }

    public static synchronized AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mAndroidInjector;
    }
}
