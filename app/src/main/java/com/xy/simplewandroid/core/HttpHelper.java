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

public  interface HttpHelper {


    /**
     * 获取首页文章列表
     *
     * @param pageNum 页数
     * @return feed文章列表数据
     */
    Observable<BaseResponse<HomeArticleListData>> getHomeArticleList(int pageNum);

    /**
     * 广告栏
     * http://www.wanandroid.com/banner/json
     *
     * @return 取消收藏页面站内文章数据
     */
    Observable<BaseResponse<List<BannerData>>> getBannerData();

    /**
     * 知识体系
     * http://www.wanandroid.com/tree/json
     *
     * @return 广告栏数据
     */
    Observable<BaseResponse<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyData();

    /**
     * 获取公众号列表
     * http://wanandroid.com/wxarticle/chapters/json
     *
     * @return 公众号列表数据
     */
    Observable<BaseResponse<List<WxAuthor>>> getWxAuthorListData();

    /**
     * http://wanandroid.com/wxarticle/list/405/1/json
     *
     * @param id
     * @param page
     * @return 获取当前公众号某页的数据
     */
    Observable<BaseResponse<HomeArticleListData>> getWxDetailData(int id, int page);

    /**
     * 导航
     * http://www.wanandroid.com/navi/json
     *
     * @return 知识体系feed文章数据
     */
    Observable<BaseResponse<List<NavigationListData>>> getNavigationListData();

    /**
     * 项目分类
     * http://www.wanandroid.com/project/tree/json
     *
     * @return 导航数据
     */
    Observable<BaseResponse<List<ProjectClassifyData>>> getProjectClassifyData();

    /**
     * 项目类别数据
     * http://www.wanandroid.com/project/list/1/json?cid=294
     *
     * @param page page num
     * @param cid second page id
     * @return 项目分类数据
     */
    Observable<BaseResponse<ProjectListData>> getProjectListData(int page, int cid);
}
