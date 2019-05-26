package com.xy.simplewandroid.di.module;

import com.xy.simplewandroid.activity.ArticleDetailActivity;
import com.xy.simplewandroid.activity.MainActivity;
import com.xy.simplewandroid.di.component.BaseActivityComponent;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author quchao
 * @date 2018/5/3
 */

@Module(subcomponents = {BaseActivityComponent.class})
public abstract class AbstractAllActivityModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributesMainActivityInjector();

    @ContributesAndroidInjector(modules = ArticleDetailModule.class)
    abstract ArticleDetailActivity contributesArticleDetailActivityInjector();


}
