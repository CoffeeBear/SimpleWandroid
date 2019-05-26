package com.xy.simplewandroid.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xy.simplewandroid.R;
import com.xy.simplewandroid.bean.HomeArticleData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainPageAdapter extends BaseQuickAdapter<HomeArticleData,BaseViewHolder> {


    public MainPageAdapter(int layoutResId, @Nullable List<HomeArticleData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeArticleData article) {
        if (!TextUtils.isEmpty(article.getTitle())) {
            helper.setText(R.id.item_search_pager_title, Html.fromHtml(article.getTitle()));
        }
        if (article.isCollect()) {
            helper.setImageResource(R.id.item_search_pager_like_iv, R.drawable.icon_like);
        } else {
            helper.setImageResource(R.id.item_search_pager_like_iv, R.drawable.icon_like_article_not_selected);
        }
        if (!TextUtils.isEmpty(article.getAuthor())) {
            helper.setText(R.id.item_search_pager_author, article.getAuthor());
        }
        if (!TextUtils.isEmpty(article.getChapterName())) {
            String classifyName = article.getSuperChapterName() + " / " + article.getChapterName();
            helper.setText(R.id.item_search_pager_chapterName, classifyName);
        }
        if (!TextUtils.isEmpty(article.getNiceDate())) {
            helper.setText(R.id.item_search_pager_niceDate, article.getNiceDate());
        }
    }
}
