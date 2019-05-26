package com.xy.simplewandroid.contract;

import com.xy.simplewandroid.base.prseneter.AbstractPresenter;
import com.xy.simplewandroid.base.view.AbstractView;
import com.xy.simplewandroid.bean.KnowledgeHierarchyData;

import java.util.List;

public interface KnowledgeHierarchyContract {

    interface View extends AbstractView{
        /**
         * Show Knowledge Hierarchy Data
         *
         * @param knowledgeHierarchyDataList (List<KnowledgeHierarchyData>
         */
        void showKnowledgeHierarchyData(List<KnowledgeHierarchyData> knowledgeHierarchyDataList);
    }
    interface Presenter extends AbstractPresenter<View>{
        /**
         * 知识列表
         *
         * @param isShowError If show error
         */
        void getKnowledgeHierarchyData(boolean isShowError);
    }
}
