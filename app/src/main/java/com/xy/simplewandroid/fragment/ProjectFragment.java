package com.xy.simplewandroid.fragment;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.xy.simplewandroid.R;
import com.xy.simplewandroid.app.Constants;
import com.xy.simplewandroid.base.fragment.BaseFragment;
import com.xy.simplewandroid.bean.ProjectClassifyData;
import com.xy.simplewandroid.contract.ProjectContract;
import com.xy.simplewandroid.presenter.ProjectPresenter;
import com.xy.simplewandroid.util.LogHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import butterknife.BindView;

public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectContract.View {
    @BindView(R.id.project_tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.project_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.normal_view)
    LinearLayout normalView;

    private List<ProjectClassifyData> mData;
    private List<BaseFragment> mFragments = new ArrayList<>();
    @Override
    public void reload() {

    }

    public static ProjectFragment getInstance(String param1, String param2) {
        ProjectFragment fragment = new ProjectFragment();
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
        return R.layout.fragment_project;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getProjectClassifyData();
    }

    @Override
    public void showProjectClassifyData(List<ProjectClassifyData> projectClassifyDataList) {
        mData = projectClassifyDataList;
        initViewPagerAndTabLayout();
    }

    private void initViewPagerAndTabLayout() {
        for (ProjectClassifyData data : mData) {
            ProjectListFragment projectListFragment = ProjectListFragment.getInstance(data.getId(), null);
            mFragments.add(projectListFragment);
        }
        LogHelper.e(mData.size()+"   "+mFragments.size());
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mData == null? 0 : mData.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mData.get(position).getName();
            }
        });
        mTabLayout.setViewPager(mViewPager);
        mViewPager.setCurrentItem(Constants.TAB_ONE);
    }
}
