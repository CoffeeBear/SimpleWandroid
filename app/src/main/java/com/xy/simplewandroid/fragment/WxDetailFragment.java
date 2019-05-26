package com.xy.simplewandroid.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xy.simplewandroid.R;
import com.xy.simplewandroid.adapter.MainPageAdapter;
import com.xy.simplewandroid.app.Constants;
import com.xy.simplewandroid.base.fragment.BaseFragment;
import com.xy.simplewandroid.bean.HomeArticleListData;
import com.xy.simplewandroid.contract.WxDetailContract;
import com.xy.simplewandroid.presenter.WxDetailPresenter;
import com.xy.simplewandroid.util.ActivityUtils;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class WxDetailFragment extends BaseFragment<WxDetailPresenter> implements WxDetailContract.View {
    @BindView(R.id.we_detail_list_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;

    private int id;
    private String mAuthor;
    private int mCurrentPage = 1;
    private MainPageAdapter mAdapter;
    private boolean isRefresh = true;
    @Override
    public void reload() {

    }

    @Override
    protected void initView() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdapter = new MainPageAdapter(R.layout.item_article,null);
        mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(view, position));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void startArticleDetailPager(View view, int position) {
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() <= position) {
            return;
        }
        ActivityUtils.startArticleDetailActivity(getActivity(),
                mAdapter.getData().get(position).getId(),
                mAdapter.getData().get(position).getTitle().trim(),
                mAdapter.getData().get(position).getLink().trim(),
                mAdapter.getData().get(position).isCollect(),
                false,false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wx_detail;
    }

    public static WxDetailFragment getInstance(int param1, String param2) {
        WxDetailFragment fragment = new WxDetailFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initEventAndData() {
        setRefresh();
        Bundle bundle = getArguments();
        id = bundle.getInt(Constants.ARG_PARAM1, 0);
        if (id == 0) {
            return;
        }
        mAuthor = bundle.getString(Constants.ARG_PARAM2, "");
        mCurrentPage = 1;
        mPresenter.getWxDetailData(id, mCurrentPage, true);
    }

    @Override
    public void showWxDetailView(HomeArticleListData wxSumData) {
        if (isRefresh) {
            mAdapter.replaceData(wxSumData.getDatas());
        } else {
            if (wxSumData.getDatas().size() > 0) {
                mAdapter.addData(wxSumData.getDatas());
            } else {
                Toast.makeText(getActivity(),getString(R.string.load_more_no_data),Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setRefresh() {
        mRefreshLayout.setPrimaryColorsId(R.color.blue, R.color.white);
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mCurrentPage = 1;
            if (id != 0) {
                isRefresh = true;
                mPresenter.getWxDetailData(id, 0, false);
            }
            refreshLayout.finishRefresh(1000);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mCurrentPage++;
            if (id != 0) {
                isRefresh = false;
                mPresenter.getWxDetailData(id, mCurrentPage, false);

            }
            refreshLayout.finishLoadMore(1000);
        });
    }
}
