package com.xy.simplewandroid.di.component;

import com.xy.simplewandroid.app.WandroidApp;
import com.xy.simplewandroid.core.DataManager;
import com.xy.simplewandroid.di.module.AbstractAllActivityModule;
import com.xy.simplewandroid.di.module.AbstractAllFragmentModule;
import com.xy.simplewandroid.di.module.AppModule;
import com.xy.simplewandroid.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AbstractAllFragmentModule.class,
        AbstractAllActivityModule.class,
        AppModule.class,
        HttpModule.class})
public interface AppComponent {

    /**
     * 注入WanAndroidApp实例
     *
     * @param wanAndroidApp WanAndroidApp
     */
    void inject(WandroidApp wanAndroidApp);

    /**
     * 提供App的Context
     *
     * @return GeeksApp context
     */
    WandroidApp getContext();

    /**
     * 数据中心
     *
     * @return DataManager
     */
    DataManager getDataManager();
}
