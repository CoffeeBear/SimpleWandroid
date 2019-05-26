package com.xy.simplewandroid.contract;

import com.xy.simplewandroid.base.prseneter.AbstractPresenter;
import com.xy.simplewandroid.base.view.AbstractView;
import com.xy.simplewandroid.bean.HomeArticleListData;

public interface WxDetailContract {

    interface View extends AbstractView{
        /**
         * Show wx detail view
         *
         * @param wxSumData wx detail data
         */
        void showWxDetailView(HomeArticleListData wxSumData);
    }

    interface Presenter extends AbstractPresenter<View>{
        /**
         * Get wx detail data
         *
         * @param id wx id
         * @param page page number
         * @param isShowError if show error
         */
        void getWxDetailData(int id, int page, boolean isShowError);
    }
}
