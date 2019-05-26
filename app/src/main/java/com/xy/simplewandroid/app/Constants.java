package com.xy.simplewandroid.app;

import android.graphics.Color;

public interface Constants {

    String ARTICLE_LINK = "article_link";

    String ARTICLE_TITLE = "article_title";

    String ARTICLE_ID = "article_id";

    String IS_COLLECT = "is_collect";

    String IS_COMMON_SITE = "is_common_site";

    String IS_COLLECT_PAGE = "is_collect_page";

    /**
     * Tag fragment classify
     */
    int TYPE_MAIN_PAGER = 0;

    int TYPE_KNOWLEDGE = 1;

    int TYPE_WX_ARTICLE = 2;

    int TYPE_NAVIGATION = 3;

    int TYPE_PROJECT = 4;

    int TYPE_COLLECT = 5;

    int TYPE_SETTING = 6;

    /**
     * Intent params
     */
    String ARG_PARAM1 = "param1";

    String ARG_PARAM2 = "param2";

    /**
     * Tab colors
     */
    int[] TAB_COLORS = new int[]{
            Color.parseColor("#90C5F0"),
            Color.parseColor("#91CED5"),
            Color.parseColor("#F88F55"),
            Color.parseColor("#C0AFD0"),
            Color.parseColor("#E78F8F"),
            Color.parseColor("#67CCB7"),
            Color.parseColor("#F6BC7E")
    };

    /**
     * Bottom Navigation tab classify
     */
    int TAB_ONE = 0;
}
