package com.mayisheng.wanandroid.fragment.home;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mayisheng.wanandroid.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;


public class BannerViewHolder extends RecyclerView.ViewHolder {
    private Banner mBanner;

    public BannerViewHolder(@NonNull View itemView) {
        super(itemView);
        mBanner = itemView.findViewById(R.id.banner);
        mBanner.setAdapter(new ImageAdapter(new ArrayList<>()));
    }

    public void setBannerInfos(List<BannerInfo> list) {
        mBanner.setDatas(list);
        mBanner.start();
    }


}
