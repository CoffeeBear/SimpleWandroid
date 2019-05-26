package com.xy.simplewandroid.core;

import com.xy.simplewandroid.bean.BannerData;
import com.xy.simplewandroid.bean.BaseResponse;
import com.xy.simplewandroid.bean.HomeArticleListData;
import com.xy.simplewandroid.bean.KnowledgeHierarchyData;
import com.xy.simplewandroid.bean.NavigationListData;
import com.xy.simplewandroid.bean.ProjectClassifyData;
import com.xy.simplewandroid.bean.ProjectListData;
import com.xy.simplewandroid.bean.WxAuthor;

import java.util.List;

import io.reactivex.Observable;

public class DataManager implements HttpHelper{

    private HttpHelper mHttpHelper;

    public DataManager(HttpHelper mHttpHelper) {
        this.mHttpHelper = mHttpHelper;
    }

    @Override
    public Observable<BaseResponse<HomeArticleListData>> getHomeArticleList(int pageNum) {
        return mHttpHelper.getHomeArticleList(pageNum);
    }

    @Override
    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return mHttpHelper.getBannerData();
    }

    @Override
    public Observable<BaseResponse<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyData() {
        return mHttpHelper.getKnowledgeHierarchyData();
    }
    @Override
    public Observable<BaseResponse<List<WxAuthor>>> getWxAuthorListData() {
        return mHttpHelper.getWxAuthorListData();
    }

    @Override
    public Observable<BaseResponse<HomeArticleListData>> getWxDetailData(int id, int page) {
        return mHttpHelper.getWxDetailData(id,page);
    }

    @Override
    public Observable<BaseResponse<List<NavigationListData>>> getNavigationListData() {
        return mHttpHelper.getNavigationListData();
    }

    public Observable<BaseResponse<List<ProjectClassifyData>>> getProjectClassifyData() {
        return mHttpHelper.getProjectClassifyData();
    }

    public Observable<BaseResponse<ProjectListData>> getProjectListData(int page, int cid) {
        return mHttpHelper.getProjectListData(page, cid);
    }
}
