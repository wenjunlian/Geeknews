package com.example.greeknews.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.greeknews.R;
import com.example.greeknews.activity.DeilyActivity;
import com.example.greeknews.adapter.RlvAdapterDialy;
import com.example.greeknews.base.BaseFragment;
import com.example.greeknews.bean.DeliayBean;
import com.example.greeknews.bean.RiLiBean;
import com.example.greeknews.bean.StoriesBean;
import com.example.greeknews.presenter.ZhihudeliayPresenter;
import com.example.greeknews.view.ZhihudeliayView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DilayFragment extends BaseFragment<ZhihudeliayView, ZhihudeliayPresenter> implements ZhihudeliayView {

    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
   /* @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;*/
    private ArrayList<StoriesBean> list;

    private ArrayList<DeliayBean.TopStoriesBean> mbanner;
    private int page = 4;
    private RlvAdapterDialy mAdapterDialy;
    private String mDate;
    private String mYear;
    private String mMonth;
    private String mDay;
    private ArrayList<String> mBeans;

    @Override
    protected ZhihudeliayPresenter initPresenter() {
        return new ZhihudeliayPresenter();
    }

    @Override
    protected int getlayoutid() {
        return R.layout.fragment_dilay;
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getArtical();
    }

    @Override
    public void setData(DeliayBean bean) {
        Log.d("000", "setData: " + bean);
        list = new ArrayList<>();
        mbanner = new ArrayList<>();
        list.addAll(bean.getStories());
        mDate = bean.getDate();
        mbanner.addAll(bean.getTop_stories());
        mAdapterDialy = new RlvAdapterDialy(getContext(), mbanner, list,mDate);

        rlv.setAdapter(mAdapterDialy);
        rlv.setLayoutManager(new LinearLayoutManager(getContext()));
        rlv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mAdapterDialy.notifyDataSetChanged();
    }

    @Override
    public int getpage() {
        return page;
    }


    @Override
    public void setDataRili(RiLiBean bean) {
        mAdapterDialy.mlist.clear();
        mAdapterDialy.mBanner.clear();
        mAdapterDialy.mDate = bean.getDate();

        list.addAll(bean.getStories());
        mAdapterDialy.notifyDataSetChanged();
       }

    @OnClick(R.id.fab_add)
    public void chick(){
        Intent intent = new Intent(getContext(), DeilyActivity.class);
        startActivityForResult(intent,100);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100&&resultCode==200){
            String date = data.getStringExtra("date");

            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
            String format = sf.format(new Date());
            if (date.equals(format)){
                mPresenter.getArtical();
            }else {
                int intData = Integer.valueOf(date);
                intData+=1;
                mPresenter.getrili(String.valueOf(intData));
            }

        }
    }
}
