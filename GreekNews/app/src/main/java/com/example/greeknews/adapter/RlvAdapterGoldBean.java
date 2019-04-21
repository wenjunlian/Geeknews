package com.example.greeknews.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.greeknews.R;
import com.example.greeknews.bean.GoldBean;
import com.example.greeknews.weight.TouchCallback;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.Collections;

public class RlvAdapterGoldBean extends RecyclerView.Adapter implements TouchCallback {
    private ArrayList<GoldBean> mList;

    public RlvAdapterGoldBean(ArrayList<GoldBean> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_goldbean, null);
        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        VH holder = (VH) viewHolder;
        final GoldBean bean = mList.get(i);
        holder.mTv.setText(bean.title);
        holder.mSc.setChecked(bean.isChecked);
        holder.mSc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bean.isChecked=isChecked;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onItemSwitch(int formPosition, int toPosition) {
        Collections.swap(mList,formPosition,toPosition);
        notifyItemMoved(formPosition,toPosition);
    }

    @Override
    public void inItemDelete(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    class VH extends RecyclerView.ViewHolder {

        private final TextView mTv;
        private final SwitchCompat mSc;

        public VH(@NonNull View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tv);
            mSc = itemView.findViewById(R.id.sc);
        }
    }
}
