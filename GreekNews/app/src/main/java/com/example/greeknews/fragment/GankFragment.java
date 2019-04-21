package com.example.greeknews.fragment;

import com.example.greeknews.R;
import com.example.greeknews.base.BaseFragment;
import com.example.greeknews.presenter.GankPresenter;
import com.example.greeknews.view.GankView;


/**
 * Created by 孤辟 on 2019/4/3.
 */

public class GankFragment extends BaseFragment<GankView,GankPresenter> {
    @Override
    protected GankPresenter initPresenter() {
        return new GankPresenter();
    }

    @Override
    protected int getlayoutid() {
        return R.layout.fragment_gank;
    }
}
