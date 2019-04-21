package com.example.greeknews.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greeknews.R;
import com.example.greeknews.adapter.RlvAdapterWeChat;
import com.example.greeknews.base.BaseFragment;
import com.example.greeknews.bean.WeChat;
import com.example.greeknews.presenter.WechatPresenter;
import com.example.greeknews.view.WechatView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by 孤辟 on 2019/4/3.
 */
//温俊连，H1808B
public class WechatFragment extends BaseFragment<WechatView, WechatPresenter> implements WechatView {
    private static final String TAG = "WechatFragment";
//key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=1

    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private String key = "52b7ec3471ac3bec6846577e79f20e4c";
    private String num = "10";
    private String page = "1";
    private ArrayList<WeChat.NewslistBean> mList;
    private RlvAdapterWeChat mAdapterWeChat;

    @Override
    protected WechatPresenter initPresenter() {
        return new WechatPresenter();
    }

    @Override
    protected int getlayoutid() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void initView() {
        super.initView();
        mPresenter.getData();

        mList = new ArrayList<>();
        mAdapterWeChat = new RlvAdapterWeChat(getContext(), mList);
        rlv.setAdapter(mAdapterWeChat);
        rlv.setLayoutManager(new LinearLayoutManager(getContext()));
        rlv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public String num() {
        return num;
    }

    @Override
    public String page() {
        return page;
    }

    //搜索栏查询
    @Override
    public void setData(WeChat bean) {
        Log.d(TAG, "setData: " + bean.toString());
        mList.addAll(bean.getNewslist());
        mAdapterWeChat.notifyDataSetChanged();
    }

    public void addData(String query) {
        mAdapterWeChat.mList.clear();
        if (query != null) {
            mPresenter.getWechatData(Integer.valueOf(page), query);
        }
    }
}
