package com.example.greeknews.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 孤辟 on 2019/4/3.
 */

public  abstract class BaseFragment<V extends BaseViewmvp,P extends BasePresenter> extends Fragment implements BaseViewmvp{

    private Unbinder mUnbinder;
    public P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(getlayoutid(), null);
        mUnbinder = ButterKnife.bind(this, inflate);
        mPresenter = initPresenter();

        if (mPresenter!=null){
            mPresenter.bind((V)this);
        }
        initData();
        initView();
        initListener();
        return inflate;
    }

    protected void initData() {

    }

    protected void initView() {

    }

    protected void initListener() {

    }

    protected abstract P initPresenter();

    protected abstract int getlayoutid() ;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        mPresenter.onDestroy();
        mPresenter=null;
    }
}
