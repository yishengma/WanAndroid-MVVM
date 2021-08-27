package com.mayisheng.wanandroid.fragment.home;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class HomeInfo {

    @IntDef({Type.BANNER_INFO, Type.HOT_INFO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
        int BANNER_INFO = 0;
        int HOT_INFO = 1;
    }

    private List<BannerInfo> mBannerInfos;
    private int mType;

    public HomeInfo(List<BannerInfo> bannerInfos) {
        mBannerInfos = bannerInfos;
        mType = Type.BANNER_INFO;
    }

    public int getType() {
        return mType;
    }

    public List<BannerInfo> getBannerInfos() {
        return mBannerInfos;
    }
}
