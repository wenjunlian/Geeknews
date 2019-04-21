package com.example.greeknews.fragment;

import com.example.greeknews.R;
import com.example.greeknews.base.BaseFragment;
import com.example.greeknews.presenter.SettingPresenter;
import com.example.greeknews.view.SettingView;


/**
 * Created by 孤辟 on 2019/4/3.
 */

public class SettingsFragment extends BaseFragment<SettingView,SettingPresenter> {
    @Override
    protected SettingPresenter initPresenter() {
        return new SettingPresenter();
    }

    @Override
    protected int getlayoutid() {
        return R.layout.fragment_settings;
    }
}
