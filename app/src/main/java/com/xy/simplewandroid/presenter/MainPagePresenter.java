package com.xy.simplewandroid.presenter;

import android.util.Log;

import com.xy.simplewandroid.base.prseneter.BasePresenter;
import com.xy.simplewandroid.bean.BannerData;
import com.xy.simplewandroid.bean.BaseResponse;
import com.xy.simplewandroid.bean.HomeArticleListData;
import com.xy.simplewandroid.contract.MainPageContract;
import com.xy.simplewandroid.core.DataManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPagePresenter extends BasePresenter<MainPageContract.View> implements MainPageContract.Presenter {
    private DataManager mDataManager;
    private boolean isRefresh = true;
    private int mCurrentPage;
    @Inject
    MainPagePresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(MainPageContract.View view) {
        super.attachView(view);
    }

    @Override
    public void loadMainPagerData() {
        Log.e("loadMainPagerData","MainPagePreseneter的loadData");
        Observable<BaseResponse<HomeArticleListData>> homeArticleList = mDataManager.getHomeArticleList(0);
        Observable<BaseResponse<List<BannerData>>> bannerData = mDataManager.getBannerData();
        homeArticleList.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BaseResponse<HomeArticleListData>>() {
            @Override
            public void onSubscribe(Disposable d) {
//                Log.e("onSubscribe","MainPagePreseneter的onSubscribe");
            }

            @Override
            public void onNext(BaseResponse<HomeArticleListData> homeArticleListDataBaseResponse) {
//                Log.e("onNext","MainPagePreseneter的onNext"+homeArticleListDataBaseResponse.getData().getDatas().get(0).getLink());
                mView.showArticleList(homeArticleListDataBaseResponse.getData(), isRefresh);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
//                Log.e("onError","MainPagePreseneter的onError"+e.toString());
            }

            @Override
            public void onComplete() {
                Log.e("onComplete","MainPagePreseneter的onComplete");
            }
        });
        bannerData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BaseResponse<List<BannerData>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponse<List<BannerData>> bannerResponse) {
                mView.showBannerData(bannerResponse.getData());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getBannerData() {
        Observable<BaseResponse<List<BannerData>>> bannerData = mDataManager.getBannerData();
        bannerData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DefaultObserver<BaseResponse<List<BannerData>>>() {
            @Override
            public void onNext(BaseResponse<List<BannerData>> bannerResponse) {
                mView.showBannerData(bannerResponse.getData());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getHomeArticleList() {
        Observable<BaseResponse<HomeArticleListData>> homeArticleList = mDataManager.getHomeArticleList(0);
        homeArticleList.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DefaultObserver<BaseResponse<HomeArticleListData>>() {
            @Override
            public void onNext(BaseResponse<HomeArticleListData> homeArticleListDataBaseResponse) {
                mView.showArticleList(homeArticleListDataBaseResponse.getData(), isRefresh);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void autoRefresh() {
        isRefresh = true;
        mCurrentPage = 0;
        getBannerData();
        getHomeArticleList();
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        mCurrentPage++;
        loadMoreData();
    }

    @Override
    public void loadMoreData() {
        Observable<BaseResponse<HomeArticleListData>> homeArticleList = mDataManager.getHomeArticleList(mCurrentPage);
        homeArticleList.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DefaultObserver<BaseResponse<HomeArticleListData>>() {
            @Override
            public void onNext(BaseResponse<HomeArticleListData> homeArticleListDataBaseResponse) {
                mView.showArticleList(homeArticleListDataBaseResponse.getData(), isRefresh);
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
