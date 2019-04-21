package com.example.greeknews.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by 孤辟 on 2019/4/3.
 */

public abstract class BaseActivity<V extends BaseViewmvp,P extends BasePresenter> extends AppCompatActivity implements BaseViewmvp {

    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayoutid());
        ButterKnife.bind(this);
        mPresenter = initPresenter();
        if (mPresenter!=null){
            mPresenter.bind((V) this);
        }
        initData();
        initView();
        initListener();
    }

    protected void initData() {}

    protected  void initView(){}

    protected  void initListener(){}

    protected abstract P initPresenter();

    protected abstract int getlayoutid() ;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        mPresenter=null;
    }
}
