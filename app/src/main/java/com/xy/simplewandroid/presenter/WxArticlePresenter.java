package com.xy.simplewandroid.presenter;

import com.xy.simplewandroid.base.prseneter.BasePresenter;
import com.xy.simplewandroid.bean.BaseResponse;
import com.xy.simplewandroid.bean.WxAuthor;
import com.xy.simplewandroid.contract.WxArticleContract;
import com.xy.simplewandroid.core.DataManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

public class WxArticlePresenter extends BasePresenter<WxArticleContract.View> implements WxArticleContract.Presenter {
    DataManager mDataManager;

    @Inject
    public WxArticlePresenter(DataManager dataManager) {
        super(dataManager);
        mDataManager = dataManager;
    }

    @Override
    public void getWxAuthorListData() {
        Observable<BaseResponse<List<WxAuthor>>> wxAuthorListData = mDataManager.getWxAuthorListData();
        wxAuthorListData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DefaultObserver<BaseResponse<List<WxAuthor>>>() {
            @Override
            public void onNext(BaseResponse<List<WxAuthor>> listBaseResponse) {
                mView.showWxAuthorListView(listBaseResponse.getData());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
