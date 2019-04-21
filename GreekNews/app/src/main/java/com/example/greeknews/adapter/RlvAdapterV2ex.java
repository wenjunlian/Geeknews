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
import com.example.greeknews.bean.V2exBean;

import java.util.ArrayList;

public class RlvAdapterV2ex extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<V2exBean> mBeans;

    public RlvAdapterV2ex(Context context, ArrayList<V2exBean> beans) {

        mContext = context;
        mBeans = beans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_itemv2ex, null);
        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH holder1= (VH) holder;
        V2exBean bean = mBeans.get(position);
        holder1.mtv_author.setText(bean.author);
        holder1.mtv_c.setText(bean.href);
        holder1.mtv_num.setText(bean.nunber);
        holder1.mtv_text.setText(bean.topic);
        holder1.mtv_title.setText(bean.titles);
        Glide.with(mContext).load(bean.img).into(holder1.miv);
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }
    class VH extends RecyclerView.ViewHolder{

        private final TextView mtv_author;
        private final TextView mtv_text;
        private final TextView mtv_title;
        private final TextView mtv_num;
        private final TextView mtv_c;
        private final ImageView miv;

        public VH(View itemView) {
            super(itemView);
            mtv_author = itemView.findViewById(R.id.tv_author);
            mtv_text = itemView.findViewById(R.id.tv_text);
            mtv_title = itemView.findViewById(R.id.tv_title);
            mtv_num = itemView.findViewById(R.id.tv_num);
            mtv_c = itemView.findViewById(R.id.tv_c);
            miv = itemView.findViewById(R.id.iv);
        }
    }
}
