package com.mayisheng.wanandroid.fragment.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mayisheng.wanandroid.R;

import java.util.List;

public class HomeRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HomeInfo> mHomeInfos;

    public HomeRvAdapter(List<HomeInfo> homeInfos) {
        mHomeInfos = homeInfos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case HomeInfo.Type.BANNER_INFO:
                viewHolder = new BannerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_banner, parent, false));
                break;
            case HomeInfo.Type.HOT_INFO:
                break;
            default:
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder) {
            ((BannerViewHolder) holder).setBannerInfos(mHomeInfos.get(position).getBannerInfos());
        }
    }

    @Override
    public int getItemCount() {
        return mHomeInfos.size();
    }
}
