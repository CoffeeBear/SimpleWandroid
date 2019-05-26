package com.xy.simplewandroid.fragment;

import android.os.Bundle;

import com.xy.simplewandroid.R;
import com.xy.simplewandroid.adapter.ProjectListAdapter;
import com.xy.simplewandroid.app.Constants;
import com.xy.simplewandroid.base.fragment.BaseFragment;
import com.xy.simplewandroid.bean.HomeArticleData;
import com.xy.simplewandroid.bean.ProjectListData;
import com.xy.simplewandroid.contract.ProjectListContract;
import com.xy.simplewandroid.presenter.ProjectListPresenter;
import com.xy.simplewandroid.util.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class ProjectListFragment extends BaseFragment<ProjectListPresenter> implements ProjectListContract.View {

    @BindView(R.id.project_list_recycler_view)
    RecyclerView mRecyclerView;
    //暂时没有超过1页的项目列表
    private int mCurrentPage = 1;
    private int cid;
    private ProjectListAdapter mAdapter;
    @Override
    public void reload() {

    }

    @Override
    protected void initView() {
       initRecyclerView();
    }

    private void initRecyclerView() {
        List<HomeArticleData> mDatas = new ArrayList<>();
        mAdapter = new ProjectListAdapter(R.layout.item_project_list, mDatas);
        mAdapter.setOnItemClickListener((adapter, view, position) -> startProjectPager(position));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void startProjectPager(int position) {
        ActivityUtils.startArticleDetailActivity(getActivity(),mAdapter.getData().get(position).getId(),
                mAdapter.getData().get(position).getTitle().trim(),
                mAdapter.getData().get(position).getLink().trim(),
                mAdapter.getData().get(position).isCollect(),
                false,
                true);
    }
    public static ProjectListFragment getInstance(int param1, String param2) {
        ProjectListFragment fragment = new ProjectListFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project_list;
    }

    @Override
    protected void initEventAndData() {
        Bundle bundle = getArguments();
        cid = bundle.getInt(Constants.ARG_PARAM1);
        mPresenter.getProjectListData(mCurrentPage, cid, true);
    }

    @Override
    public void showProjectListData(ProjectListData projectListData) {
        mAdapter.replaceData(projectListData.getDatas());
    }
}
