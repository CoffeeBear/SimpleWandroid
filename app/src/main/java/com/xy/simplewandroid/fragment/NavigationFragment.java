package com.xy.simplewandroid.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kunminx.linkage.LinkageRecyclerView;
import com.kunminx.linkage.adapter.viewholder.LinkagePrimaryViewHolder;
import com.kunminx.linkage.adapter.viewholder.LinkageSecondaryFooterViewHolder;
import com.kunminx.linkage.adapter.viewholder.LinkageSecondaryHeaderViewHolder;
import com.kunminx.linkage.adapter.viewholder.LinkageSecondaryViewHolder;
import com.kunminx.linkage.bean.BaseGroupedItem;
import com.kunminx.linkage.bean.DefaultGroupedItem;
import com.kunminx.linkage.contract.ILinkageSecondaryAdapterConfig;
import com.kunminx.linkage.defaults.DefaultLinkagePrimaryAdapterConfig;
import com.xy.simplewandroid.R;
import com.xy.simplewandroid.adapter.NavigationAdapter;
import com.xy.simplewandroid.app.Constants;
import com.xy.simplewandroid.base.fragment.BaseFragment;
import com.xy.simplewandroid.bean.HomeArticleData;
import com.xy.simplewandroid.bean.NavigationGroupItem;
import com.xy.simplewandroid.bean.NavigationListData;
import com.xy.simplewandroid.contract.NavigationContract;
import com.xy.simplewandroid.presenter.NavigationPresenter;
import com.xy.simplewandroid.util.ActivityUtils;
import com.xy.simplewandroid.util.LogHelper;
import com.zhy.view.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.View {
    @BindView(R.id.linkage)
    LinkageRecyclerView linkage;

//    @BindView(R.id.navigation_tab_layout)
//    VerticalTabLayout mTabLayout;
//    @BindView(R.id.navigation_divider)
//    View mDivider;
//    @BindView(R.id.navigation_RecyclerView)
//    RecyclerView mRecyclerView;
//    @BindView(R.id.normal_view)
//    LinearLayout mNavigationGroup;

//    private NavigationAdapter mNavigationAdapter;
//    private LinearLayoutManager mManager;

    @Override
    public void reload() {

    }

    public static NavigationFragment getInstance(String param1, String param2) {
        NavigationFragment fragment = new NavigationFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        initRecyclerView();
        LogHelper.e("NavigationFragment");
    }

    private void initRecyclerView() {
//        List<NavigationListData> navigationDataList = new ArrayList<>();
//        mNavigationAdapter = new NavigationAdapter(R.layout.item_navigation, navigationDataList);
//        mManager = new LinearLayoutManager(getActivity());
//        mRecyclerView.setLayoutManager(mManager);
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setAdapter(mNavigationAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getNavigationListData(true);
    }

    @Override
    public void showNavigationListData(List<NavigationListData> navigationDataList) {
        List<NavigationGroupItem> items1 = new ArrayList<>();
        List<NavigationGroupItem> items2 = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        for (NavigationListData data : navigationDataList){
            items2.add(new NavigationGroupItem(true,data.getArticles().get(0).getChapterName()));
            strings.add(data.getArticles().get(0).getChapterName());
            for (HomeArticleData articleData : data.getArticles()){
                items2.add(new NavigationGroupItem(new NavigationGroupItem.ItemInfo(articleData.getTitle(),articleData.getChapterName(),null,articleData.getLink(),articleData.getId())));

            }
        }
        LogHelper.e("NavigationFragment");

//        linkage.init(items1);
        linkage.init(items2,new DefaultLinkagePrimaryAdapterConfig(){
            @Override
            public void onBindViewHolder(LinkagePrimaryViewHolder holder, boolean selected, String title, int position) {
                super.onBindViewHolder(holder, selected, title, position);
                ((TextView) holder.mGroupTitle).setBackgroundColor(getActivity().getResources().getColor(selected ? R.color.blue:R.color.white));
            }
        },new NavigationSecondaryAdapterConfig());
//        linkage.getPrimaryAdapter().refreshList(strings);

//        mTabLayout.setTabAdapter(new TabAdapter() {
//            @Override
//            public int getCount() {
//                return navigationDataList == null ? 0 : navigationDataList.size();
//            }
//
//            @Override
//            public ITabView.TabBadge getBadge(int position) {
//                return null;
//            }
//
//            @Override
//            public ITabView.TabIcon getIcon(int position) {
//                return null;
//            }
//
//            @Override
//            public ITabView.TabTitle getTitle(int position) {
//                return new TabView.TabTitle.Builder()
//                        .setContent(navigationDataList.get(position).getName())
//                        .setTextColor(ContextCompat.getColor(getActivity(), R.color.blue),
//                                ContextCompat.getColor(getActivity(), R.color.shallow_grey))
//                        .build();
//            }
//
//            @Override
//            public int getBackground(int position) {
//                return -1;
//            }
//        });
//        mNavigationAdapter.replaceData(navigationDataList);
//        leftRightLinkage();
    }

    private void leftRightLinkage() {
//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });
    }

    private class NavigationSecondaryAdapterConfig implements
            ILinkageSecondaryAdapterConfig<NavigationGroupItem.ItemInfo> {

        private Context mContext;

        public void setContext(Context context) {
            mContext = context;
        }

        @Override
        public int getGridLayoutId() {
            return 0;
        }

        @Override
        public int getLinearLayoutId() {
            return R.layout.adapter_second_item;
        }

        @Override
        public int getHeaderLayoutId() {
            return R.layout.flow_layout_tv;
        }

        @Override
        public int getFooterLayoutId() {
            return 0;
        }

        @Override
        public int getHeaderTextViewId() {
            return R.id.commonItemTitle;
        }

        @Override
        public int getSpanCountOfGridMode() {
            return 1;
        }

        @Override
        public void onBindViewHolder(LinkageSecondaryViewHolder holder,
                                     BaseGroupedItem<NavigationGroupItem.ItemInfo> item, int position) {

            ((TextView) holder.getView(R.id.secondary_title_tv)).setText(item.info.getTitle());
            holder.getView(R.id.secondary_title_tv).setOnClickListener(v -> {
                startNavigationPager(item);
            });
        }

        @Override
        public void onBindHeaderViewHolder(LinkageSecondaryHeaderViewHolder holder,
                                           BaseGroupedItem<NavigationGroupItem.ItemInfo> item, int position) {

            ((TextView) holder.getView(R.id.commonItemTitle)).setText(item.header);
        }

        @Override
        public void onBindFooterViewHolder(LinkageSecondaryFooterViewHolder holder,
                                           BaseGroupedItem<NavigationGroupItem.ItemInfo> item, int position) {

        }
    }

    private void startNavigationPager(BaseGroupedItem<NavigationGroupItem.ItemInfo> item) {
        ActivityUtils.startArticleDetailActivity(getActivity(),
                item.info.getId(),
                item.info.getTitle(),
                item.info.getLink(),
                false,false,false);
    }


}
