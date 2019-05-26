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

import javax.inject.Inject;

import io.reactivex.Observable;

public class HttpHelperImpl implements HttpHelper {

    private GeeksApis mGeeksApis;

    @Inject
    HttpHelperImpl(GeeksApis mGeeksApis) {
        this.mGeeksApis = mGeeksApis;
    }

    @Override
    public Observable<BaseResponse<HomeArticleListData>> getHomeArticleList(int pageNum) {
        return mGeeksApis.getHomeArticleList(pageNum);
    }

    @Override
    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return mGeeksApis.getBannerData();
    }

    @Override
    public Observable<BaseResponse<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyData() {
        return mGeeksApis.getKnowledgeHierarchyData();
    }

    @Override
    public Observable<BaseResponse<List<WxAuthor>>> getWxAuthorListData() {
        return mGeeksApis.getWxAuthorListData();
    }

    @Override
    public Observable<BaseResponse<HomeArticleListData>> getWxDetailData(int id, int page) {
        return mGeeksApis.getWxDetailData(id,page);
    }

    @Override
    public Observable<BaseResponse<List<NavigationListData>>> getNavigationListData() {
        return mGeeksApis.getNavigationListData();
    }

    @Override
    public Observable<BaseResponse<List<ProjectClassifyData>>> getProjectClassifyData() {
        return mGeeksApis.getProjectClassifyData();
    }

    @Override
    public Observable<BaseResponse<ProjectListData>> getProjectListData(int page, int cid) {
        return mGeeksApis.getProjectListData(page, cid);
    }
}
