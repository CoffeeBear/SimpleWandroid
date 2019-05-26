package com.xy.simplewandroid.presenter;

import com.xy.simplewandroid.base.prseneter.BasePresenter;
import com.xy.simplewandroid.bean.BaseResponse;
import com.xy.simplewandroid.bean.HomeArticleListData;
import com.xy.simplewandroid.contract.WxDetailContract;
import com.xy.simplewandroid.core.DataManager;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

public class WxDetailPresenter extends BasePresenter<WxDetailContract.View> implements WxDetailContract.Presenter {


    DataManager mDataManager;

    @Inject
    public WxDetailPresenter(DataManager dataManager) {
        super(dataManager);
        mDataManager = dataManager;
    }


    @Override
    public void getWxDetailData(int id, int page, boolean isShowError) {
        Observable<BaseResponse<HomeArticleListData>> wxDetailData = mDataManager.getWxDetailData(id, page);
        wxDetailData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DefaultObserver<BaseResponse<HomeArticleListData>>() {
            @Override
            public void onNext(BaseResponse<HomeArticleListData> homeArticleListDataBaseResponse) {
                mView.showWxDetailView(homeArticleListDataBaseResponse.getData());
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
