package com.xy.simplewandroid.contract;

import com.xy.simplewandroid.base.prseneter.AbstractPresenter;
import com.xy.simplewandroid.base.view.AbstractView;
import com.xy.simplewandroid.bean.ProjectListData;

public interface ProjectListContract {

    interface View extends AbstractView{
        /**
         * Show project list data
         *
         * @param projectListData ProjectListData
         */
        void showProjectListData(ProjectListData projectListData);
    }

    interface Presenter extends AbstractPresenter<View>{
        /**
         * Get project list data
         *
         * @param page page num
         * @param cid second page id
         * @param isShowError If show error
         */
        void getProjectListData(int page, int cid, boolean isShowError);
    }
}
