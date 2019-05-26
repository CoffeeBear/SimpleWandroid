package com.xy.simplewandroid.util;


import android.content.Context;
import android.content.Intent;


import com.xy.simplewandroid.activity.ArticleDetailActivity;
import com.xy.simplewandroid.app.Constants;

public class ActivityUtils {

    public static void startArticleDetailActivity(Context mActivity,int id, String articleTitle,
                                                  String articleLink, boolean isCollect,
                                                  boolean isCollectPage, boolean isCommonSite) {
        Intent intent = new Intent(mActivity, ArticleDetailActivity.class);
        intent.putExtra(Constants.ARTICLE_ID, id);
        intent.putExtra(Constants.ARTICLE_TITLE, articleTitle);
        intent.putExtra(Constants.ARTICLE_LINK, articleLink);
        intent.putExtra(Constants.IS_COLLECT, isCollect);
        intent.putExtra(Constants.IS_COLLECT_PAGE, isCollectPage);
        intent.putExtra(Constants.IS_COMMON_SITE, isCommonSite);
         mActivity.startActivity(intent);

    }
}
