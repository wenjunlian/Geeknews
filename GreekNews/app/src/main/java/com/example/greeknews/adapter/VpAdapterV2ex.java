package com.example.greeknews.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.greeknews.bean.V2exBeanTabs;

import java.util.ArrayList;

public class VpAdapterV2ex extends FragmentPagerAdapter{
    private ArrayList<Fragment> mFragments;
    private ArrayList<V2exBeanTabs> mMtitles;

    public VpAdapterV2ex(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<V2exBeanTabs> mtitles) {
        super(fm);
        mFragments = fragments;
        mMtitles = mtitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mMtitles.get(position).tabs;
    }
}
