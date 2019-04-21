package com.example.greeknews.fragment;

import com.example.greeknews.R;
import com.example.greeknews.base.BaseFragment;
import com.example.greeknews.presenter.AboutPresenter;
import com.example.greeknews.view.AboutView;


/**
 * Created by 孤辟 on 2019/4/3.
 */

public class AboutFragment extends BaseFragment<AboutView,AboutPresenter> {
    @Override
    protected AboutPresenter initPresenter() {
        return new AboutPresenter();
    }

    @Override
    protected int getlayoutid() {
        return R.layout.fragment_about;
    }
}
