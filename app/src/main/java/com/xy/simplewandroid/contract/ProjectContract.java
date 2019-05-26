package com.xy.simplewandroid.contract;

import com.xy.simplewandroid.base.prseneter.AbstractPresenter;
import com.xy.simplewandroid.base.view.AbstractView;
import com.xy.simplewandroid.bean.ProjectClassifyData;

import java.util.List;

public interface ProjectContract {

    interface View extends AbstractView{
        /**
         * Show project classify data
         *
         * @param projectClassifyDataList List<ProjectClassifyData>
         */
        void showProjectClassifyData(List<ProjectClassifyData> projectClassifyDataList);
    }
    interface Presenter extends AbstractPresenter<View>{
        /**
         * Get project classify data
         */
        void getProjectClassifyData();
    }
}
