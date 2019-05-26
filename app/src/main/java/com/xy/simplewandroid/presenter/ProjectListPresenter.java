package com.xy.simplewandroid.presenter;

import com.xy.simplewandroid.base.prseneter.BasePresenter;
import com.xy.simplewandroid.bean.BaseResponse;
import com.xy.simplewandroid.bean.ProjectListData;
import com.xy.simplewandroid.contract.ProjectContract;
import com.xy.simplewandroid.contract.ProjectListContract;
import com.xy.simplewandroid.core.DataManager;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

public class ProjectListPresenter extends BasePresenter<ProjectListContract.View> implements ProjectListContract.Presenter {

    DataManager mDataManager;

    @Inject
    public ProjectListPresenter(DataManager dataManager) {
        super(dataManager);
        mDataManager = dataManager;
    }

    @Override
    public void getProjectListData(int page, int cid, boolean isShowError) {
        Observable<BaseResponse<ProjectListData>> projectListData = mDataManager.getProjectListData(page, cid);
        projectListData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DefaultObserver<BaseResponse<ProjectListData>>() {
            @Override
            public void onNext(BaseResponse<ProjectListData> projectListDataBaseResponse) {
                mView.showProjectListData(projectListDataBaseResponse.getData());
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
