package com.xy.simplewandroid.activity;

import android.os.Bundle;
import android.text.Html;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.xy.simplewandroid.R;
import com.xy.simplewandroid.app.Constants;
import com.xy.simplewandroid.base.activity.BaseActivity;
import com.xy.simplewandroid.contract.ArticleDetailContract;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;

public class ArticleDetailActivity extends BaseActivity implements ArticleDetailContract.View {


    @BindView(R.id.article_detail_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.article_detail_web_view)
    FrameLayout mWebContent;

    private Bundle bundle;
    private int articleId;
    private String articleLink;
    private String title;

    private boolean isCollect;
    private boolean isCommonSite;
    private boolean isCollectPage;
    private AgentWeb agentWeb;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_acticle_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        agentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        agentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }
    @Override
    public void onDestroy() {
        agentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    @Override
    protected void initView() {
        getBundleData();
        mToolbar.setTitle(Html.fromHtml(title));
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(v -> {
            supportFinishAfterTransition();
        });
    }

    @Override
    protected void initEventAndData() {
        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(mWebContent,new LinearLayout.LayoutParams(-1,-1))
                .useDefaultIndicator()
                .setMainFrameErrorView(R.layout.webview_error_view,-1)
                .createAgentWeb()
                .ready()
                .go(articleLink);
        WebView mWebView = agentWeb.getWebCreator().getWebView();
        WebSettings mSettings = mWebView.getSettings();

        mSettings.setJavaScriptEnabled(true);
        mSettings.setSupportZoom(true);
        mSettings.setBuiltInZoomControls(true);
        //不显示缩放按钮
        mSettings.setDisplayZoomControls(false);
        //设置自适应屏幕，两者合用
        //将图片调整到适合WebView的大小
        mSettings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        mSettings.setLoadWithOverviewMode(true);
        //自适应屏幕
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }

    private void getBundleData() {
        bundle = getIntent().getExtras();
        assert bundle != null;
        title = (String) bundle.get(Constants.ARTICLE_TITLE);
        articleLink = (String) bundle.get(Constants.ARTICLE_LINK);
        articleId = ((int) bundle.get(Constants.ARTICLE_ID));
        isCommonSite = ((boolean) bundle.get(Constants.IS_COMMON_SITE));
        isCollect = ((boolean) bundle.get(Constants.IS_COLLECT));
        isCollectPage = ((boolean) bundle.get(Constants.IS_COLLECT_PAGE));
    }

}
