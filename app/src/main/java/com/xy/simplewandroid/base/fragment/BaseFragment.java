package com.xy.simplewandroid.base.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import dagger.android.support.AndroidSupportInjection;

import android.view.View;

import com.xy.simplewandroid.base.view.AbstractView;
import com.xy.simplewandroid.base.prseneter.AbstractPresenter;

import javax.inject.Inject;

public abstract class BaseFragment<T extends AbstractPresenter> extends AbstractFragment implements AbstractView {

    @Inject
    protected T mPresenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    public abstract void reload();
}
