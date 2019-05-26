package com.xy.simplewandroid.di.module;

import com.xy.simplewandroid.di.component.BaseFragmentComponent;
import com.xy.simplewandroid.fragment.KnowledgeHierarchyFragment;
import com.xy.simplewandroid.fragment.MainPageFragment;
import com.xy.simplewandroid.fragment.NavigationFragment;
import com.xy.simplewandroid.fragment.ProjectFragment;
import com.xy.simplewandroid.fragment.ProjectListFragment;
import com.xy.simplewandroid.fragment.WxArticleFragment;
import com.xy.simplewandroid.fragment.WxDetailFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@Module(subcomponents = BaseFragmentComponent.class)
public abstract class AbstractAllFragmentModule {

    @ContributesAndroidInjector(modules= MainPageFragmentModule.class)
    abstract MainPageFragment contributesMainPagerFragmentInject();

    @ContributesAndroidInjector(modules= KnowledgeHierarchyModule.class)
    abstract KnowledgeHierarchyFragment contributesKnowledgeHierarchyFragment();

    @ContributesAndroidInjector(modules= WxArticleModule.class)
    abstract WxArticleFragment contributesWxArticleFragment();

    @ContributesAndroidInjector(modules= WxDetailModule.class)
    abstract WxDetailFragment contributesWxDetailFragment();

    @ContributesAndroidInjector(modules= NavigationModule.class)
    abstract NavigationFragment contributesNavigationFragment();

    @ContributesAndroidInjector(modules= ProjectModule.class)
    abstract ProjectFragment contributesProjectFragment();

    @ContributesAndroidInjector(modules= ProjectListModule.class)
    abstract ProjectListFragment contributesProjectListFragment();
}
