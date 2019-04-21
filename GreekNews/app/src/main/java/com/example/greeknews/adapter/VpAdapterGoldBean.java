package com.example.greeknews.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.greeknews.bean.GoldBean;

import java.util.ArrayList;

public class VpAdapterGoldBean extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> mFragments;
    private ArrayList<GoldBean> mTitles;
    private ArrayList<String> mNewTitles=new ArrayList<String>();

    public VpAdapterGoldBean(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<GoldBean> titles) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;
        for (int i = 0; i < mTitles.size(); i++) {
            GoldBean goldBean = mTitles.get(i);
            if (goldBean.isChecked){
               mNewTitles.add(goldBean.title);
            }
        }
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mNewTitles.get(position);
    }
}
