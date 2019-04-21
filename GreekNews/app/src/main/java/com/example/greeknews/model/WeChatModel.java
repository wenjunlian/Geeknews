package com.example.greeknews.model;

import com.example.greeknews.Callback;
import com.example.greeknews.api.MyServer;
import com.example.greeknews.base.BaseModel;
import com.example.greeknews.bean.WeChat;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeChatModel extends BaseModel {

    public void getArtical(HashMap<String, Object> map, final Callback<WeChat> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MyServer.wechat)
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Observable<WeChat> observable = myServer.wechat(map);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeChat>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WeChat weChat) {
                        callback.onSuccess(weChat);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getWechatArtical(int page, String query, final Callback<WeChat> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MyServer.wechat)
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Observable<WeChat> observable = myServer.wechatSo(page,query);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeChat>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WeChat weChat) {
                        callback.onSuccess(weChat);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
