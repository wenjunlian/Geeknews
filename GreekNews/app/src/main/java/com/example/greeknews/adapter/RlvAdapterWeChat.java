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
import com.example.greeknews.bean.WeChat;

import java.util.ArrayList;

public class RlvAdapterWeChat extends RecyclerView.Adapter {
    private Context mContext;
    public ArrayList<WeChat.NewslistBean> mList;

    public RlvAdapterWeChat(Context context, ArrayList<WeChat.NewslistBean> list) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_wechat, null);
        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        VH holder= (VH) viewHolder;
        WeChat.NewslistBean bean = mList.get(i);
        holder.mTv.setText(bean.getTitle());
        holder.mTv1.setText(bean.getDescription());
        holder.mTv2.setText(bean.getCtime());
        Glide.with(mContext).load(bean.getPicUrl()).into(holder.mIv);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    class VH extends RecyclerView.ViewHolder{

        private final ImageView mIv;
        private final TextView mTv;
        private final TextView mTv1;
        private final TextView mTv2;

        public VH(@NonNull View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv);
            mTv = itemView.findViewById(R.id.tv);
            mTv1 = itemView.findViewById(R.id.tv1);
            mTv2 = itemView.findViewById(R.id.tv2);
        }
    }
}
