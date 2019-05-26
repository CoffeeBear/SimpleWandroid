package com.xy.simplewandroid.base.prseneter;

import com.xy.simplewandroid.base.view.AbstractView;

public interface AbstractPresenter<T extends AbstractView> {
    /**
     * 注入View
     *
     * @param view view
     */
    void attachView(T view);

}
