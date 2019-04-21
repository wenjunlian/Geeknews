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

import java.util.ArrayList;

public class RlvAdapterZhuanglan extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<ZhuanglanBean.DataBean> list;
    private OnClickListener listener;

    public RlvAdapterZhuanglan(Context context, ArrayList<ZhuanglanBean.DataBean> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_itemzhuanglan, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
       MyViewHolder holder1= (MyViewHolder) holder;
        final ZhuanglanBean.DataBean dataBean = list.get(position);
        holder1.mtv.setText(dataBean.getName());
        holder1.mtv2.setText(dataBean.getDescription());
        Glide.with(context).load(dataBean.getThumbnail()).into(holder1.miv);
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.OnClick(position,dataBean);
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
        private final TextView mtv2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            miv = itemView.findViewById(R.id.iv);
            mtv = itemView.findViewById(R.id.tv);
            mtv2 = itemView.findViewById(R.id.tv2);
        }
    }
    public interface OnClickListener{
        void OnClick(int position,ZhuanglanBean.DataBean dataBean);
    }
    public void setOnClickListener(OnClickListener listener){

        this.listener = listener;
    }
}
