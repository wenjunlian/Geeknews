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
import com.example.greeknews.bean.DeliayBean;
import com.example.greeknews.bean.RiLiBean;
import com.example.greeknews.bean.StoriesBean;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class RlvAdapterDialy extends RecyclerView.Adapter {
    public String mDate;
    private int TYPE_BANNER = 0;
    private int TYPE_TEXT = 1;
    private int TYPE_ARTICAL = 2;
    private Context mContext;
    public ArrayList<DeliayBean.TopStoriesBean> mBanner;
    public ArrayList<StoriesBean> mlist;

    public RlvAdapterDialy(Context context, ArrayList<DeliayBean.TopStoriesBean> mbanner, ArrayList<StoriesBean> list, String date) {

        mContext = context;
        mBanner = mbanner;
        mlist = list;
        mDate = date;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (viewType == TYPE_BANNER) {
            View inflate = inflater.inflate(R.layout.layout_itemrizhi1, null);
            return new MyViewHolder1(inflate);
        } else if (viewType == TYPE_TEXT) {
            View inflate = inflater.inflate(R.layout.layout_itemrizhi2, null);
            return new MyViewHolder2(inflate);
        } else {
            View inflate = inflater.inflate(R.layout.layout_itemrizhi3, null);
            return new MyViewHolder3(inflate);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mBanner.size() > 0) {
            if (position == 0) {
                return TYPE_BANNER;
            } else if (position == 1) {
                return TYPE_TEXT;
            } else {
                return TYPE_ARTICAL;
            }
        } else {
            if (position == 0) {
                return TYPE_TEXT;
            }else {
            return TYPE_ARTICAL;
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == TYPE_BANNER) {
            final MyViewHolder1 holder1 = (MyViewHolder1) holder;
            holder1.mBan.setImages(mBanner).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    DeliayBean.TopStoriesBean bean = (DeliayBean.TopStoriesBean) path;
                    Glide.with(context).load(bean.getImage()).into(imageView);
                }
            }).start();
        }else if (itemViewType==TYPE_TEXT){
            MyViewHolder2 holder2= (MyViewHolder2) holder;
            holder2.mTv.setText(mDate);
        }else {
            int newposition=position-1;
            if (mBanner.size()>0){
                newposition -= 1;
            }
            MyViewHolder3 holder3= (MyViewHolder3) holder;
            StoriesBean bean = mlist.get(newposition);
            holder3.mTv.setText(bean.getTitle());
            List<String> images = bean.getImages();
            for (int i = 0; i < images.size(); i++) {
                Glide.with(mContext).load(images.get(i)).into(holder3.mIv);
            }

        }
    }

    @Override
    public int getItemCount() {
        if (mBanner.size() > 0 && mBanner != null) {
            return mlist.size() + 1 + 1;
        } else {
            return mlist.size();
        }
    }

    /*public void setData(RiLiBean bean) {
        mDate=bean.getDate();
    }*/

    class MyViewHolder1 extends RecyclerView.ViewHolder {

        private final Banner mBan;

        public MyViewHolder1(View itemView) {
            super(itemView);
            mBan = itemView.findViewById(R.id.ban);
        }
    }

    class MyViewHolder2 extends RecyclerView.ViewHolder {

        private final TextView mTv;

        public MyViewHolder2(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tv);
        }
    }

    class MyViewHolder3 extends RecyclerView.ViewHolder {

        private final ImageView mIv;
        private final TextView mTv;

        public MyViewHolder3(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv);
            mTv = itemView.findViewById(R.id.tv);
        }
    }
}

