package com.xy.simplewandroid.presenter;

import com.xy.simplewandroid.contract.MainContract;
import com.xy.simplewandroid.core.DataManager;

import javax.inject.Inject;

public class MainPresenter implements MainContract.Presenter {

    private DataManager dataManager;
    @Inject
    MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void setCurrentPage(int page) {

    }
}
