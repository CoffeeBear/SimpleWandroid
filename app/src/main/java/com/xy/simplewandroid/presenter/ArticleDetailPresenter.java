package com.xy.simplewandroid.presenter;

import com.xy.simplewandroid.base.prseneter.BasePresenter;
import com.xy.simplewandroid.contract.ArticleDetailContract;
import com.xy.simplewandroid.core.DataManager;

import javax.inject.Inject;

public class ArticleDetailPresenter extends BasePresenter<ArticleDetailContract.View> implements ArticleDetailContract.Preseneter {

    @Inject
    public ArticleDetailPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void attachView(ArticleDetailContract.View view) {

    }
}
