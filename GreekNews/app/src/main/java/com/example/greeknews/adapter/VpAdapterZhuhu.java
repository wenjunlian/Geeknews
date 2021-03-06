package com.example.greeknews.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class VpAdapterZhuhu extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mtitle;

    public VpAdapterZhuhu(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> mtitle) {
        super(fm);
        mFragments = fragments;
        this.mtitle = mtitle;
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
        return mtitle.get(position);
    }
}
