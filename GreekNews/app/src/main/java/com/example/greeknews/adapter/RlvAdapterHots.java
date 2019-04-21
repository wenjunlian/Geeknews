package com.example.greeknews.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.greeknews.R;
import com.example.greeknews.bean.HotsBean;

import java.util.ArrayList;
import java.util.Random;

public class RlvAdapterHots extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<HotsBean.RecentBean> list;

    public RlvAdapterHots(Context context, ArrayList<HotsBean.RecentBean> list) {

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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            MyViewHolder holder= (MyViewHolder) viewHolder;
        HotsBean.RecentBean bean = list.get(i);
        String[] colors = {"#E8355C","#1599FA","#02A4A7","#FDD80F","#FE8542","#A2398D"};
        Random random = new Random();
        int c = random.nextInt(5);
        holder.mtv.setTextColor(Color.parseColor(colors[c]));
        holder.mtv.setText(bean.getTitle());
        Glide.with(context).load(bean.getThumbnail()).into(holder.miv);
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
}
