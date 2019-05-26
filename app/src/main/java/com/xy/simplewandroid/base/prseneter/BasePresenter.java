package com.xy.simplewandroid.base.prseneter;

import com.xy.simplewandroid.base.view.AbstractView;
import com.xy.simplewandroid.core.DataManager;

public class BasePresenter<T extends AbstractView> implements AbstractPresenter<T>{
    protected T mView;
    private DataManager mDataManager;

    public BasePresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(T view) {
        mView = view;
    }



}
