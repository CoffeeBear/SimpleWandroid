package com.xy.simplewandroid.presenter;

import com.xy.simplewandroid.base.prseneter.BasePresenter;
import com.xy.simplewandroid.bean.BaseResponse;
import com.xy.simplewandroid.bean.ProjectClassifyData;
import com.xy.simplewandroid.contract.ProjectContract;
import com.xy.simplewandroid.core.DataManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

public class ProjectPresenter extends BasePresenter<ProjectContract.View> implements ProjectContract.Presenter {
    DataManager mDataManager;
    @Inject
    public ProjectPresenter(DataManager dataManager) {
        super(dataManager);
        mDataManager = dataManager;
    }

    @Override
    public void getProjectClassifyData() {
        Observable<BaseResponse<List<ProjectClassifyData>>> projectClassifyData = mDataManager.getProjectClassifyData();
        projectClassifyData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DefaultObserver<BaseResponse<List<ProjectClassifyData>>>() {
            @Override
            public void onNext(BaseResponse<List<ProjectClassifyData>> listBaseResponse) {
                mView.showProjectClassifyData(listBaseResponse.getData());
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
