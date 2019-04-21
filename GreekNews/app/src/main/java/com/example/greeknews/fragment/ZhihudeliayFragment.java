package com.example.greeknews.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.greeknews.R;
import com.example.greeknews.adapter.VpAdapterZhuhu;
import com.example.greeknews.base.BaseFragment;
import com.example.greeknews.presenter.ZhihudeliayPresenter;
import com.example.greeknews.view.ZhihudeliayView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by 孤辟 on 2019/4/3.
 */

public class ZhihudeliayFragment extends BaseFragment<ZhihudeliayView, ZhihudeliayPresenter> {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private ArrayList<String> mtitle;
    private ArrayList<Fragment> fragments;
    private VpAdapterZhuhu adapterZhuhu;

    @Override
    protected ZhihudeliayPresenter initPresenter() {
        return new ZhihudeliayPresenter();
    }

    @Override
    protected int getlayoutid() {
        return R.layout.fragment_zhihu;
    }

    @Override
    protected void initData() {
        super.initData();
        mtitle = new ArrayList<>();
        mtitle.add("日志");
        mtitle.add("主题");
        mtitle.add("专栏");
        mtitle.add("最热");

        fragments = new ArrayList<>();
        fragments.add(new DilayFragment());
        fragments.add(new HomeFragment());
        fragments.add(new ZhuanlanFragment());
        fragments.add(new HotsFragment());

        adapterZhuhu = new VpAdapterZhuhu(getChildFragmentManager(),fragments,mtitle);
        vp.setAdapter(adapterZhuhu);
        tab.setupWithViewPager(vp);


    }
}
