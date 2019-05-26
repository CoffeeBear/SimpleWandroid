package com.xy.simplewandroid.contract;

import com.xy.simplewandroid.base.view.AbstractView;

public interface MainContract {

    interface View extends AbstractView {
        /**
         * Show switch project
         */
        void showSwitchProject();

        /**
         * Show switch navigation
         */
        void showSwitchNavigation();
    }

    interface Presenter{
        /**
         * Set current page
         * @param page
         */
        void setCurrentPage(int page);
    }
}

