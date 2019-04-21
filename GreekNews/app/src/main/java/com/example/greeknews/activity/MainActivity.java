package com.example.greeknews.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.greeknews.R;
import com.example.greeknews.base.BaseActivity;
import com.example.greeknews.fragment.AboutFragment;
import com.example.greeknews.fragment.CollextorFragment;
import com.example.greeknews.fragment.GankFragment;
import com.example.greeknews.fragment.GoldFragment;
import com.example.greeknews.fragment.SettingsFragment;
import com.example.greeknews.fragment.V2EXFragment;
import com.example.greeknews.fragment.WechatFragment;
import com.example.greeknews.fragment.ZhihudeliayFragment;
import com.example.greeknews.presenter.MainPresenter;
import com.example.greeknews.view.MainView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {
     @BindView(R.id.tv)
     TextView mTv;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.nv)
    NavigationView mNv;
    @BindView(R.id.dl)
    DrawerLayout mDl;
    @BindView(R.id.fl)
    FrameLayout mFl;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    @BindView(R.id.toolbar_container)
    FrameLayout toolbarContainer;
    private ArrayList<Fragment> mFragments;
    private ArrayList<Integer> mtitle;
    private static final int TYPE_ZHIHU = 0;
    private static final int TYPE_WECHAT = 1;
    private static final int TYPE_GANK = 2;
    private static final int TYPE_GOLD = 3;
    private static final int TYPE_V2EX = 4;
    private static final int TYPE_COLLEXTOR = 5;
    private static final int TYPE_SETTING = 6;
    private static final int TYPE_ABOUT = 7;
    private FragmentManager mManager;
    private int lastFragmentposition;
    private MenuItem mSearchitem;
    private WechatFragment mWechatFragment;
    private GankFragment mGankFragment;

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getlayoutid() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        mManager = getSupportFragmentManager();
        initTitle();
        initFragment();
        mToolbar.setTitle(" ");
        setSupportActionBar(mToolbar);
        mNv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId != R.id.zixun && itemId != R.id.select) {
                    item.setChecked(true);
                    switch (item.getItemId()) {
                        case R.id.zhihu:
                            switchFragment(TYPE_ZHIHU);
                            break;
                        case R.id.wechat:
                            switchFragment(TYPE_WECHAT);
                            mTv.setText(R.string.wechat);
                            break;
                        case R.id.gank:
                            switchFragment(TYPE_GANK);
                            mTv.setText(R.string.gank);
                            break;
                        case R.id.gold:
                            switchFragment(TYPE_GOLD);
                            mTv.setText(R.string.gold);
                            break;
                        case R.id.v2ex:
                            switchFragment(TYPE_V2EX);
                            mTv.setText(R.string.v2ex);
                            break;
                        case R.id.collector:
                            switchFragment(TYPE_COLLEXTOR);
                            mTv.setText(R.string.collector);
                            break;
                        case R.id.settings:
                            switchFragment(TYPE_SETTING);
                            mTv.setText(R.string.settings);
                            break;
                        case R.id.about:
                            switchFragment(TYPE_ABOUT);
                            mTv.setText(R.string.about);
                            break;

                    }
                    mDl.closeDrawer(Gravity.LEFT);
                } else {
                    item.setChecked(false);
                }
                return false;
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, mDl, mToolbar, R.string.about, R.string.about);
        mDl.addDrawerListener(toggle);
        toggle.syncState();
        mDl.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                float v = mDl.getWidth() * slideOffset;
                mLl.setX(v);
                super.onDrawerSlide(drawerView, slideOffset);
            }
        });

        //旋转开关设置成白色
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.write));

        //搜索栏
        MaterialSearchView searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //按下搜索或者提交的时候回调,
                if (mWechatFragment.isVisible()){
                    mWechatFragment.addData(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //搜索框内容发生改变的回调,
                return false;
            }
        });
    }

    private void switchFragment(int type) {
        //得到Fragment的类型，并显示显示一个Fragment
        Fragment fragment = mFragments.get(type);
        //如果点击下一个碎片，需要隐藏上一个被点击过的碎片
        Fragment hindFragment = mFragments.get(lastFragmentposition);

        FragmentTransaction transaction = mManager.beginTransaction();
        //判断碎片是否添加过
        if (!fragment.isAdded()) {
            transaction.add(R.id.fl, fragment);
        }
        //隐藏碎片要写在显示上边，防止第一个碎片直接被隐藏
        transaction.hide(hindFragment);
        transaction.show(fragment);
        transaction.commit();

        lastFragmentposition = type;

        //显示或隐藏搜索栏
        if (type==TYPE_WECHAT||type==TYPE_GANK){
            mSearchitem.setVisible(true);
        }else {
            mSearchitem.setVisible(false);
        }

    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new ZhihudeliayFragment());
        mWechatFragment = new WechatFragment();
        mFragments.add(mWechatFragment);
        mGankFragment = new GankFragment();
        mFragments.add(mGankFragment);
        mFragments.add(new GoldFragment());
        mFragments.add(new V2EXFragment());
        mFragments.add(new CollextorFragment());
        mFragments.add(new SettingsFragment());
        mFragments.add(new AboutFragment());

        //默认显示第一条
        FragmentTransaction transaction = mManager.beginTransaction();
        transaction.add(R.id.fl, mFragments.get(0));
        transaction.commit();

        //mToolbar.setTitle(mtitle.get(0));
        mTv.setText(mtitle.get(0));

    }

    private void initTitle() {
        mtitle = new ArrayList<>();
        mtitle.add(R.string.zhihu);
        mtitle.add(R.string.wechat);
        mtitle.add(R.string.gank);
        mtitle.add(R.string.gold);
        mtitle.add(R.string.v2ex);
        mtitle.add(R.string.settings);
        mtitle.add(R.string.about);
        mtitle.add(R.string.collector);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        mSearchitem = menu.findItem(R.id.action_search);
        //隐藏搜索栏
        mSearchitem.setVisible(false);
        searchView.setMenuItem(mSearchitem);

        return true;
    }

    /**
     * 回退键点击回调
     */
    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}
