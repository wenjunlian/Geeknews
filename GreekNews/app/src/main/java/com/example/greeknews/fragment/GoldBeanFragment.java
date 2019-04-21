package com.example.greeknews.fragment;


import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.greeknews.R;
import com.example.greeknews.base.BaseFragment;
import com.example.greeknews.presenter.EmptyP;
import com.example.greeknews.view.EmptyV;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoldBeanFragment extends BaseFragment<EmptyV, EmptyP> {


    @BindView(R.id.tv)
    TextView tv;


    public static GoldBeanFragment newInstance(String text) {
        GoldBeanFragment fragment = new GoldBeanFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SyncStateContract.Constants.DATA, text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getlayoutid() {
        return R.layout.fragment_gold_bean;
    }

    @Override
    protected void initData() {
        super.initData();
        Bundle arguments = getArguments();
        String data = arguments.getString(SyncStateContract.Constants.DATA);
        tv.setText(data);
    }
}
