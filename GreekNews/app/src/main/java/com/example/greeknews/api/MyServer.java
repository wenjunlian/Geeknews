package com.example.greeknews.api;

import com.example.greeknews.bean.DeliayBean;
import com.example.greeknews.bean.HotsBean;
import com.example.greeknews.bean.RiLiBean;
import com.example.greeknews.bean.WeChat;
import com.example.greeknews.bean.ZhuanglanBean;
import com.example.greeknews.bean.Zhuanlan1;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

//温俊连,H1808B
public interface MyServer {
    public String url="http://news-at.zhihu.com/api/";

    public String wechat="http://api.tianapi.com/";
    @GET("{page}/news/latest")
    Observable<DeliayBean> deliay(@Path("page")int page);

    @GET("4/news/hot")
    Observable<HotsBean> hots();

    @GET("4/sections ")
    Observable<ZhuanglanBean> zhuanglan();

    @GET("4/section/{page} ")
    Observable<Zhuanlan1> zhuanglan1(@Path("page")int page);

    @GET("wxnew/?")//key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=1
    Observable<WeChat> wechat(@QueryMap HashMap<String,Object> map);

    @GET("wxnew/?key=52b7ec3471ac3bec6846577e79f20e4c&num=10")//&page=1
    Observable<WeChat> wechatSo(@Query("page") int page,@Query("word")String word);

    @GET("4/news/before/{date}")
    Observable<RiLiBean> deliayrili(@Path("date")String date);

}
