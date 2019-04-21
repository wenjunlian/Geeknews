package com.example.greeknews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.greeknews.R;
import com.example.greeknews.adapter.RlvAdapterGoldBean;
import com.example.greeknews.bean.GoldBean;
import com.example.greeknews.weight.SimpleTouchHelperCallback;
import com.example.greeknews.weight.TouchCallback;

import java.util.ArrayList;

public class GoldBeanActivity extends AppCompatActivity {

    private ImageView mIv;
    private RecyclerView mRlv;
    private ArrayList<GoldBean> mList;
    private RlvAdapterGoldBean mAdapterGoldBean;
    /**
     * 首页特别展示
     */
    private TextView mTv;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gold_bean);
        initView();
    }

    private void initView() {
        mIv = (ImageView) findViewById(R.id.iv);
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        mTv = (TextView) findViewById(R.id.tv);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(" ");
        setSupportActionBar(mToolbar);

        mList = (ArrayList<GoldBean>) getIntent().getSerializableExtra(SyncStateContract.Constants.DATA);
        mAdapterGoldBean = new RlvAdapterGoldBean(mList);
        mRlv.setAdapter(mAdapterGoldBean);
        mRlv.setLayoutManager(new LinearLayoutManager(GoldBeanActivity.this));
        mAdapterGoldBean.notifyDataSetChanged();
        mRlv.addItemDecoration(new DividerItemDecoration(GoldBeanActivity.this, DividerItemDecoration.VERTICAL));

        mIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAct();
            }
        });

        //拖拽移动和侧滑删除的功能
        SimpleTouchHelperCallback simpleTouchHelperCallback
                =new SimpleTouchHelperCallback( mAdapterGoldBean);
        simpleTouchHelperCallback.setSwipeEnable(false);//false禁用删除，true使用删除
        ItemTouchHelper helper=new ItemTouchHelper(simpleTouchHelperCallback);
        helper.attachToRecyclerView(mRlv);
    }
    private void finishAct() {
        Intent intent = new Intent();
        intent.putExtra(SyncStateContract.Constants.DATA,mList);
        setResult(200,intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finishAct();
    }

}
