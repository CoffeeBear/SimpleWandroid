package com.xy.simplewandroid.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xy.simplewandroid.R;
import com.xy.simplewandroid.bean.HomeArticleData;
import com.xy.simplewandroid.bean.NavigationListData;
import com.xy.simplewandroid.util.ActivityUtils;
import com.xy.simplewandroid.util.CommonUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import androidx.annotation.Nullable;

public class NavigationAdapter extends BaseQuickAdapter<NavigationListData,BaseViewHolder> {

    public NavigationAdapter(int layoutResId, @Nullable List<NavigationListData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationListData item) {
        if (!TextUtils.isEmpty(item.getName())) {
            helper.setText(R.id.item_navigation_tv, item.getName());
        }
        TagFlowLayout mTagFlowLayout = helper.getView(R.id.item_navigation_flow_layout);
        List<HomeArticleData> mArticles = item.getArticles();
        mTagFlowLayout.setAdapter(new TagAdapter<HomeArticleData>(mArticles) {
            @Override
            public View getView(FlowLayout parent, int position, HomeArticleData feedArticleData) {
                TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.flow_layout_tv,
                        mTagFlowLayout, false);
                if (feedArticleData == null) {
                    return null;
                }
                String name = feedArticleData.getTitle();
                tv.setPadding(CommonUtils.dp2px(10), CommonUtils.dp2px(10),
                        CommonUtils.dp2px(10), CommonUtils.dp2px(10));
                tv.setText(name);
                tv.setTextColor(CommonUtils.randomColor());
                mTagFlowLayout.setOnTagClickListener((view, position1, parent1) -> {
                    startNavigationPager(view, position1, parent, mArticles);
                    return true;
                });
                return tv;
            }
        });
    }

    private void startNavigationPager(View view, int position1, FlowLayout parent, List<HomeArticleData> mArticles) {
        ActivityUtils.startArticleDetailActivity(parent.getContext(),
                mArticles.get(position1).getId(),
                mArticles.get(position1).getTitle(),
                mArticles.get(position1).getLink(),
                mArticles.get(position1).isCollect(),false,false);
    }
}
