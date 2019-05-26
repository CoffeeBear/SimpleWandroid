package com.xy.simplewandroid.presenter;

import com.xy.simplewandroid.base.prseneter.BasePresenter;
import com.xy.simplewandroid.bean.BaseResponse;
import com.xy.simplewandroid.bean.KnowledgeHierarchyData;
import com.xy.simplewandroid.contract.KnowledgeHierarchyContract;
import com.xy.simplewandroid.core.DataManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

public class KnowledgeHierarchyPresenter extends BasePresenter<KnowledgeHierarchyContract.View> implements KnowledgeHierarchyContract.Presenter {

    private DataManager mDataManager;
    @Inject
    public KnowledgeHierarchyPresenter(DataManager dataManager) {
        super(dataManager);
        mDataManager = dataManager;
    }

    @Override
    public void attachView(KnowledgeHierarchyContract.View view) {
        super.attachView(view);
    }

    @Override
    public void getKnowledgeHierarchyData(boolean isShowError) {
        Observable<BaseResponse<List<KnowledgeHierarchyData>>> knowledgeHierarchyData = mDataManager.getKnowledgeHierarchyData();
        knowledgeHierarchyData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DefaultObserver<BaseResponse<List<KnowledgeHierarchyData>>>() {
            @Override
            public void onNext(BaseResponse<List<KnowledgeHierarchyData>> listBaseResponse) {
                mView.showKnowledgeHierarchyData(listBaseResponse.getData());
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
