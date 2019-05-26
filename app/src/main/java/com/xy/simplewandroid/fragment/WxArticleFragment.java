package com.xy.simplewandroid.fragment;


import android.os.Bundle;
import android.util.Log;

import com.flyco.tablayout.SlidingTabLayout;
import com.xy.simplewandroid.R;
import com.xy.simplewandroid.app.Constants;
import com.xy.simplewandroid.base.fragment.BaseFragment;
import com.xy.simplewandroid.bean.WxAuthor;
import com.xy.simplewandroid.contract.WxArticleContract;
import com.xy.simplewandroid.presenter.WxArticlePresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

public class WxArticleFragment extends BaseFragment<WxArticlePresenter> implements WxArticleContract.View {

    @BindView(R.id.wx_detail_tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.wx_detail_viewpager)
    ViewPager mViewPager;

    private List<BaseFragment> mFragments = new ArrayList<>();
    @Override
    public void reload() {

    }

    public static WxArticleFragment getInstance(String param1, String param2) {
        WxArticleFragment fragment = new WxArticleFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wx_article;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getWxAuthorListData();
    }

    @Override
    public void showWxAuthorListView(List<WxAuthor> wxAuthors) {
        mFragments.clear();
        for (WxAuthor wxAuthor : wxAuthors) {
            mFragments.add(WxDetailFragment.getInstance(wxAuthor.getId(), wxAuthor.getName()));
            Log.e("wxAuther",""+wxAuthor.getId()+wxAuthor.getName());
        }
        initViewPagerAndTabLayout(wxAuthors);
    }

    private void initViewPagerAndTabLayout(List<WxAuthor> wxAuthors) {
            mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return mFragments.get(position);
                }

                @Override
                public int getCount() {
                    return mFragments == null? 0 : mFragments.size();
                }

                @Override
                public CharSequence getPageTitle(int position) {
                    return wxAuthors.get(position).getName();
                }
            });
            mTabLayout.setViewPager(mViewPager);

    }
}
