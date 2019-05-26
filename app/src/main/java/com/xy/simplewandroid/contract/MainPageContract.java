package com.xy.simplewandroid.contract;

import com.xy.simplewandroid.base.view.AbstractView;
import com.xy.simplewandroid.bean.BannerData;
import com.xy.simplewandroid.bean.HomeArticleListData;
import com.xy.simplewandroid.base.prseneter.AbstractPresenter;

import java.util.List;

public interface MainPageContract {

    interface View extends AbstractView {
        /**
         * Show content
         *
         * @param feedArticleListData FeedArticleListData
         * @param isRefresh If refresh
         */
        void showArticleList(HomeArticleListData feedArticleListData, boolean isRefresh);

        void showBannerData(List<BannerData> data);
    }
    interface Presenter extends AbstractPresenter<View> {
        /**
         * Load main pager data
         */
        void loadMainPagerData();

        void autoRefresh();

        void loadMore();

        void getBannerData();

        void getHomeArticleList();

        void loadMoreData();
    }
}
