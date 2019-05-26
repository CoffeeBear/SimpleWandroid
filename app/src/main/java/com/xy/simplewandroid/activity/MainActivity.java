package com.xy.simplewandroid.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.xy.simplewandroid.R;
import com.xy.simplewandroid.app.Constants;
import com.xy.simplewandroid.base.activity.BaseActivity;
import com.xy.simplewandroid.base.fragment.BaseFragment;
import com.xy.simplewandroid.contract.MainContract;
import com.xy.simplewandroid.fragment.KnowledgeHierarchyFragment;
import com.xy.simplewandroid.fragment.MainPageFragment;
import com.xy.simplewandroid.fragment.NavigationFragment;
import com.xy.simplewandroid.fragment.ProjectFragment;
import com.xy.simplewandroid.fragment.WxArticleFragment;
import com.xy.simplewandroid.presenter.MainPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainContract.View {


    @BindView(R.id.fragment_group)
    FrameLayout fragmentGroup;
    @BindView(R.id.main_floating_action_btn)
    FloatingActionButton mainFloatingActionBtn;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.common_toolbar_title_tv)
    TextView commonToolbarTitleTv;
    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;

    @Inject
    MainPresenter mainPresenter;

    private MainPageFragment mMainPagerFragment;
    private KnowledgeHierarchyFragment mKnowledgeHierarchyFragment;
    private WxArticleFragment mWxArticleFragment;
    private NavigationFragment mNavigationFragment;
    private ProjectFragment mProjectFragment;
    private ArrayList<BaseFragment> mFragments = new ArrayList<>();
    private int mLastFgIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initView() {
        initToolBar();
        initDrawerLayout();
        initBottomNavigationView();
        initFragment();

    }

    private void initFragment() {
        mMainPagerFragment = MainPageFragment.getInstance(false,null);
        mKnowledgeHierarchyFragment = KnowledgeHierarchyFragment.getInstance(null, null);
        mWxArticleFragment = WxArticleFragment.getInstance(null,null);
        mNavigationFragment = NavigationFragment.getInstance(null,null);
        mProjectFragment = ProjectFragment.getInstance(null,null);
        mFragments.add(mMainPagerFragment);
        mFragments.add(mKnowledgeHierarchyFragment);
        mFragments.add(mWxArticleFragment);
        mFragments.add(mNavigationFragment);
        mFragments.add(mProjectFragment);
        switchFragment(Constants.TYPE_MAIN_PAGER);
    }
    /**
     * 切换fragment
     *
     * @param position 要显示的fragment的下标
     */
    private void switchFragment(int position) {

        if (position >= mFragments.size()) {
            return;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment targetFg = mFragments.get(position);
        Fragment lastFg = mFragments.get(mLastFgIndex);
        mLastFgIndex = position;
        ft.hide(lastFg);
        if (!targetFg.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(targetFg).commitAllowingStateLoss();
            ft.add(R.id.fragment_group, targetFg);
        }
        ft.show(targetFg);
        ft.commitAllowingStateLoss();
    }

    private void initBottomNavigationView() {
//        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.tab_main_pager:
                    loadPager(getString(R.string.home_pager), 0,mMainPagerFragment, Constants.TYPE_MAIN_PAGER);
                    break;
                case R.id.tab_knowledge_hierarchy:
                    loadPager(getString(R.string.knowledge_hierarchy),1,mKnowledgeHierarchyFragment,Constants.TYPE_KNOWLEDGE);
                    break;
                case R.id.tab_wx_article:
                    loadPager(getString(R.string.wx_article),2,mWxArticleFragment,Constants.TYPE_WX_ARTICLE);
                    break;
                case R.id.tab_navigation:
                    loadPager(getString(R.string.navigation),3,mNavigationFragment,Constants.TYPE_NAVIGATION);
                    break;
                case R.id.tab_project:
                    loadPager(getString(R.string.project),4,mProjectFragment,Constants.TYPE_PROJECT);
                    break;
                default:
                    break;
            }
            return true;
        });
    }
    private void loadPager(String title, int position, BaseFragment mFragment, int pagerType) {
        commonToolbarTitleTv.setText(title);
        switchFragment(position);
        mFragment.reload();
        // TODO: 2019/5/24  setCurrentPage
        mainPresenter.setCurrentPage(pagerType);
    }
    private void initDrawerLayout() {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, commonToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取抽屉的view
                View mContent = drawerLayout.getChildAt(0);
                float scale = 1 - slideOffset;
                float endScale = 0.8f + scale * 0.2f;
                float startScale = 1 - 0.3f * scale;

                //设置左边菜单滑动后的占据屏幕大小
                drawerView.setScaleX(startScale);
                drawerView.setScaleY(startScale);
                //设置菜单透明度
                drawerView.setAlpha(0.6f + 0.4f * (1 - scale));

                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                mContent.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));
                //设置内容界面操作无效（比如有button就会点击无效）
                mContent.invalidate();
                //设置右边菜单滑动后的占据屏幕大小
                mContent.setScaleX(endScale);
                mContent.setScaleY(endScale);
            }
        };
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);
    }

    private void initToolBar() {
        setSupportActionBar(commonToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        commonToolbarTitleTv.setText(getString(R.string.home_pager));
        commonToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    private void onBackPressedSupport() {
        commonToolbarTitleTv.setText("飞机速度快");
        Toast.makeText(this, "飞机速度快", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void showSwitchProject() {

    }

    @Override
    public void showSwitchNavigation() {

    }
}
