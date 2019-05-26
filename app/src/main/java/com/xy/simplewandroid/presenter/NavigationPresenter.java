package com.xy.simplewandroid.presenter;

import com.xy.simplewandroid.base.prseneter.BasePresenter;
import com.xy.simplewandroid.bean.BaseResponse;
import com.xy.simplewandroid.bean.NavigationListData;
import com.xy.simplewandroid.contract.NavigationContract;
import com.xy.simplewandroid.core.DataManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

public class NavigationPresenter extends BasePresenter<NavigationContract.View> implements NavigationContract.Presenter {

    DataManager mDataManager;
    @Inject
    public NavigationPresenter(DataManager dataManager) {
        super(dataManager);
        mDataManager = dataManager;
    }

    @Override
    public void getNavigationListData(boolean isShowError) {
        Observable<BaseResponse<List<NavigationListData>>> navigationListData = mDataManager.getNavigationListData();
        navigationListData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DefaultObserver<BaseResponse<List<NavigationListData>>>() {
            @Override
            public void onNext(BaseResponse<List<NavigationListData>> listBaseResponse) {
                mView.showNavigationListData(listBaseResponse.getData());
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
