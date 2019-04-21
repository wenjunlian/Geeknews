package com.example.greeknews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.greeknews.R;
import com.example.greeknews.activity.GoldBeanActivity;
import com.example.greeknews.adapter.VpAdapterGoldBean;
import com.example.greeknews.base.BaseFragment;
import com.example.greeknews.bean.GoldBean;
import com.example.greeknews.presenter.GoldPresenter;
import com.example.greeknews.view.GoldView;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by 孤辟 on 2019/4/3.
 */

public class GoldFragment extends BaseFragment<GoldView, GoldPresenter> {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.iv)
    ImageView iv;
    private ArrayList<GoldBean> mTitles;
    private ArrayList<Fragment> mFragments;
    private VpAdapterGoldBean mAdapterGoldBean;


    @Override
    protected GoldPresenter initPresenter() {
        return new GoldPresenter();
    }

    @Override
    protected int getlayoutid() {
        return R.layout.fragment_gold;
    }

    @Override
    protected void initView() {
        super.initView();
        initTiele();
        initFragment();
        setFragment();
    }

    private void setFragment() {
        initFragment();
        mAdapterGoldBean = new VpAdapterGoldBean(getChildFragmentManager(), mFragments, mTitles);
        vp.setAdapter(mAdapterGoldBean);
        tab.setupWithViewPager(vp);
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.size(); i++) {
            GoldBean bean = mTitles.get(i);
            if (bean.isChecked) {
                mFragments.add(GoldBeanFragment.newInstance((bean.title)));
            }
        }
    }

    private void initTiele() {
        mTitles = new ArrayList<>();
        mTitles.add(new GoldBean("Android", true));
        mTitles.add(new GoldBean("工具资源", true));
        mTitles.add(new GoldBean("iOS", true));
        mTitles.add(new GoldBean("设计", true));
        mTitles.add(new GoldBean("产品", true));
        mTitles.add(new GoldBean("阅读", true));
        mTitles.add(new GoldBean("前端", true));
        mTitles.add(new GoldBean("后端", true));


    }

    @OnClick(R.id.iv)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), GoldBeanActivity.class);
        intent.putExtra(SyncStateContract.Constants.DATA, mTitles);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == 100 && resultCode == 200) {
                mTitles = (ArrayList<GoldBean>) data.getSerializableExtra(SyncStateContract.Constants.DATA);
                setFragment();
            }
        }
    }

}
