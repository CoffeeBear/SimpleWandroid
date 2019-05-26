package com.xy.simplewandroid.contract;

import com.xy.simplewandroid.base.prseneter.AbstractPresenter;
import com.xy.simplewandroid.base.view.AbstractView;
import com.xy.simplewandroid.bean.NavigationListData;

import java.util.List;

public interface NavigationContract {

    interface View extends AbstractView{
        /**
         * Show navigation list data
         *
         * @param navigationDataList List<NavigationListData>
         */
        void showNavigationListData(List<NavigationListData> navigationDataList);
    }
    interface Presenter extends AbstractPresenter<View>{
        /**
         * Get navigation list data
         *
         * @param isShowError If show error
         */
        void getNavigationListData(boolean isShowError);
    }
}
