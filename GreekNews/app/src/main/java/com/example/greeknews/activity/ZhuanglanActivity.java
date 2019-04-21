package com.example.greeknews.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.greeknews.R;
import com.example.greeknews.adapter.RlvAdapterZhuanglan1;
import com.example.greeknews.api.MyServer;
import com.example.greeknews.bean.Zhuanlan1;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZhuanglanActivity extends AppCompatActivity {

    private static final String TAG = "ZhuanglanActivity";
    private int id;
    private RecyclerView mRlv;
    private ArrayList<Zhuanlan1.StoriesBean> list;
    private RlvAdapterZhuanglan1 adapterZhuanglan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuanglan);
        id = getIntent().getIntExtra("id", 0);
        initView();
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Observable<Zhuanlan1> observable = myServer.zhuanglan1(id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Zhuanlan1>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Zhuanlan1 zhuanlan1) {
                        Log.d(TAG, "onNext: "+zhuanlan1);
                            list.addAll(zhuanlan1.getStories());
                            adapterZhuanglan.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        list = new ArrayList<>();
        adapterZhuanglan = new RlvAdapterZhuanglan1(ZhuanglanActivity.this,list);
        mRlv.setAdapter(adapterZhuanglan);
        mRlv.setLayoutManager(new LinearLayoutManager(ZhuanglanActivity.this));
    }
}
