package com.xy.simplewandroid.contract;

import com.xy.simplewandroid.base.prseneter.AbstractPresenter;
import com.xy.simplewandroid.base.view.AbstractView;
import com.xy.simplewandroid.bean.WxAuthor;

import java.util.List;

public interface WxArticleContract {

    interface View extends AbstractView{
        /**
         * 显示公众号作者列表
         *
         * @param wxAuthors 公众号作者列表
         */
        void showWxAuthorListView(List<WxAuthor> wxAuthors);
    }

    interface Presenter extends AbstractPresenter<View>{
        /**
         * 获取公众号作者列表
         */
        void getWxAuthorListData();
    }
}
