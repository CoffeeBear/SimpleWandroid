package com.xy.simplewandroid.fragment;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xy.simplewandroid.R;
import com.xy.simplewandroid.adapter.MainPageAdapter;
import com.xy.simplewandroid.app.Constants;
import com.xy.simplewandroid.base.fragment.BaseFragment;
import com.xy.simplewandroid.bean.BannerData;
import com.xy.simplewandroid.bean.HomeArticleData;
import com.xy.simplewandroid.bean.HomeArticleListData;
import com.xy.simplewandroid.contract.MainPageContract;
import com.xy.simplewandroid.presenter.MainPagePresenter;
import com.xy.simplewandroid.util.ActivityUtils;
import com.xy.simplewandroid.util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPageFragment extends BaseFragment<MainPagePresenter> implements MainPageContract.View {

    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.main_pager_recycler_view)
    RecyclerView mainPagerRecyclerView;

    private List<HomeArticleData> mHomeArticleDataList = new ArrayList<>();
    private MainPageAdapter mainPageAdapter;
    private List<String> mBannerTitleList;
    private List<String> mBannerUrlList;
    private Banner mBanner;

    @Override
    protected void initView() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        mainPageAdapter = new MainPageAdapter(R.layout.item_article, mHomeArticleDataList);
        mainPageAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPage(view, position));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mainPagerRecyclerView.setLayoutManager(layoutManager);
        mainPagerRecyclerView.setHasFixedSize(true);
        //add head banner
        LinearLayout mHeaderGroup = ((LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.head_banner, null));
        mBanner = mHeaderGroup.findViewById(R.id.head_banner);
        mHeaderGroup.removeView(mBanner);
        mainPageAdapter.addHeaderView(mBanner);
        mainPagerRecyclerView.setAdapter(mainPageAdapter);
    }

    private void startArticleDetailPage(View view, int position) {

        if (mainPageAdapter.getData().size() <= 0 || mainPageAdapter.getData().size() < position) {
            return;
        }

        ActivityUtils.startArticleDetailActivity(getActivity(), mainPageAdapter.getData().get(position).getId(),
                mainPageAdapter.getData().get(position).getTitle(),
                mainPageAdapter.getData().get(position).getLink(),
                mainPageAdapter.getData().get(position).isCollect(), false, false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_page;
    }

    @Override
    protected void initEventAndData() {
        setRefresh();
        mPresenter.loadMainPagerData();
    }
    public static MainPageFragment getInstance(boolean param1, String param2) {
        MainPageFragment fragment = new MainPageFragment();
        Bundle args = new Bundle();
        args.putBoolean(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void showArticleList(HomeArticleListData homeArticleListData, boolean isRefresh) {
        if (mainPageAdapter == null) {
            return;
        }
        if (isRefresh) {
            mHomeArticleDataList = homeArticleListData.getDatas();
            mainPageAdapter.replaceData(homeArticleListData.getDatas());
        } else {
            mHomeArticleDataList.addAll(homeArticleListData.getDatas());
            mainPageAdapter.addData(homeArticleListData.getDatas());
        }
    }

    @Override
    public void showBannerData(List<BannerData> bannerDataList) {
        mBannerTitleList = new ArrayList<>();
        List<String> bannerImageList = new ArrayList<>();
        mBannerUrlList = new ArrayList<>();
        for (BannerData bannerData : bannerDataList) {
            mBannerTitleList.add(bannerData.getTitle());
            bannerImageList.add(bannerData.getImagePath());
            mBannerUrlList.add(bannerData.getUrl());
        }
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(bannerImageList);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(mBannerTitleList);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(bannerDataList.size() * 400);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);

        mBanner.setOnBannerListener(i -> ActivityUtils.startArticleDetailActivity(getActivity(), 0, mBannerTitleList.get(i), mBannerUrlList.get(i),
                false, false, true));
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBanner != null) {
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBanner != null) {
            mBanner.stopAutoPlay();
        }
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.autoRefresh();
            refreshLayout.finishRefresh(1000);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.loadMore();
            refreshLayout.finishLoadMore(1000);
        });
    }

    @Override
    public void reload() {
        if (mRefreshLayout != null && mPresenter != null
                && mainPagerRecyclerView.getVisibility() == View.INVISIBLE) {
            mRefreshLayout.autoRefresh();
        }
    }
}
