package com.example.greeknews.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greeknews.R;
import com.example.greeknews.adapter.RlvAdapterHots;
import com.example.greeknews.adapter.RlvAdapterZhuanglan;
import com.example.greeknews.api.MyServer;
import com.example.greeknews.bean.HotsBean;
import com.example.greeknews.bean.ZhuanglanBean;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotsFragment extends Fragment {

    private View view;
    /**
     * Hello blank fragment
     */
    private RecyclerView mRlv;
    private ArrayList<HotsBean.RecentBean> list;
    private RlvAdapterHots adapterZhuanglan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_hots, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Observable<HotsBean> observable = myServer.hots();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotsBean hotsBean) {
                        list.addAll(hotsBean.getRecent());
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

    private void initView(View inflate) {
        mRlv = (RecyclerView) inflate.findViewById(R.id.rlv);

        list = new ArrayList<>();
        adapterZhuanglan = new RlvAdapterHots(getContext(),list);
        mRlv.setAdapter(adapterZhuanglan);
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}
