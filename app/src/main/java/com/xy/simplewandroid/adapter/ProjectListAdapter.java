package com.xy.simplewandroid.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xy.simplewandroid.GlideApp;
import com.xy.simplewandroid.R;
import com.xy.simplewandroid.bean.HomeArticleData;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import androidx.annotation.Nullable;

public class ProjectListAdapter extends BaseQuickAdapter<HomeArticleData,BaseViewHolder> {

    public ProjectListAdapter(int layoutResId, @Nullable List<HomeArticleData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeArticleData item) {
        if (!TextUtils.isEmpty(item.getEnvelopePic())) {
            GlideApp.with(mContext).load(item.getEnvelopePic()).diskCacheStrategy(DiskCacheStrategy.DATA).into((ImageView) helper.getView(R.id.item_project_list_iv));
        }
        if (!TextUtils.isEmpty(item.getTitle())) {
            helper.setText(R.id.item_project_list_title_tv, item.getTitle());
        }
        if (!TextUtils.isEmpty(item.getDesc())) {
            helper.setText(R.id.item_project_list_content_tv, item.getDesc());
        }
        if (!TextUtils.isEmpty(item.getNiceDate())) {
            helper.setText(R.id.item_project_list_time_and_author_tv, item.getNiceDate()+"  "+item.getAuthor());
        }
    }
}
