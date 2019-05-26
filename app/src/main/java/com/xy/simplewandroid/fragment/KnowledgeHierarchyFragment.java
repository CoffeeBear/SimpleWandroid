package com.xy.simplewandroid.fragment;

import android.os.Bundle;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xy.simplewandroid.R;
import com.xy.simplewandroid.adapter.KnowledgeHierarchyAdapter;
import com.xy.simplewandroid.app.Constants;
import com.xy.simplewandroid.base.fragment.BaseFragment;
import com.xy.simplewandroid.bean.KnowledgeHierarchyData;
import com.xy.simplewandroid.contract.KnowledgeHierarchyContract;
import com.xy.simplewandroid.presenter.KnowledgeHierarchyPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class KnowledgeHierarchyFragment extends BaseFragment<KnowledgeHierarchyPresenter> implements KnowledgeHierarchyContract.View {
    @BindView(R.id.knowledge_hierarchy_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;
    
    private List<KnowledgeHierarchyData> mKnowledgeHierarchyDataList;
    private KnowledgeHierarchyAdapter mAdapter;
    @Override
    public void reload() {

    }

    @Override
    protected void initView() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        mKnowledgeHierarchyDataList = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new KnowledgeHierarchyAdapter(R.layout.item_knowledge_hierarchy,mKnowledgeHierarchyDataList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge_hierarchy;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getKnowledgeHierarchyData(true);
    }

    public static KnowledgeHierarchyFragment getInstance(String param1, String param2) {
        KnowledgeHierarchyFragment fragment = new KnowledgeHierarchyFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showKnowledgeHierarchyData(List<KnowledgeHierarchyData> knowledgeHierarchyDataList) {
        mKnowledgeHierarchyDataList = knowledgeHierarchyDataList;
        mAdapter.replaceData(mKnowledgeHierarchyDataList);
    }
}
