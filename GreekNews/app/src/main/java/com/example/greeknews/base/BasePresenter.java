package com.example.greeknews.base;

/**
 * Created by 孤辟 on 2019/4/3.
 */

public abstract class BasePresenter<V extends BaseViewmvp> {

    protected V mView;

    public void bind(V view) {

        mView = view;
    }

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void onDestroy() {
        mView=null;
    }
}
