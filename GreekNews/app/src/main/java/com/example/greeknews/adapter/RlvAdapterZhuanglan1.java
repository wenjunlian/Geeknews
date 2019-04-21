package com.example.greeknews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.greeknews.R;
import com.example.greeknews.bean.ZhuanglanBean;
import com.example.greeknews.bean.Zhuanlan1;

import java.util.ArrayList;
import java.util.List;

public class RlvAdapterZhuanglan1 extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<Zhuanlan1.StoriesBean> list;
    private OnClickListener listener;

    public RlvAdapterZhuanglan1(Context context, ArrayList<Zhuanlan1.StoriesBean> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_itemrizhi3, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
       MyViewHolder holder1= (MyViewHolder) holder;
        final Zhuanlan1.StoriesBean bean = list.get(position);
        holder1.mtv.setText(bean.getTitle());
        List<String> images = bean.getImages();
        for (int i = 0; i < images.size(); i++) {
            Glide.with(context).load(images.get(i)).into(holder1.miv);
        }
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.OnClick(position,bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView miv;
        private final TextView mtv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            miv = itemView.findViewById(R.id.iv);
            mtv = itemView.findViewById(R.id.tv);
        }
    }
    public interface OnClickListener{
        void OnClick(int position, Zhuanlan1.StoriesBean dataBean);
    }
    public void setOnClickListener(OnClickListener listener){

        this.listener = listener;
    }
}
