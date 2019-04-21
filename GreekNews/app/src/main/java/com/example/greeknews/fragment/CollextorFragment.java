package com.example.greeknews.fragment;

import com.example.greeknews.R;
import com.example.greeknews.base.BaseFragment;
import com.example.greeknews.presenter.CollectorPresenter;
import com.example.greeknews.view.CollectorView;


/**
 * Created by 孤辟 on 2019/4/3.
 */

public class CollextorFragment extends BaseFragment<CollectorView,CollectorPresenter> {
    @Override
    protected CollectorPresenter initPresenter() {
        return new CollectorPresenter();
    }

    @Override
    protected int getlayoutid() {
        return R.layout.fragment_collector;
    }
}
