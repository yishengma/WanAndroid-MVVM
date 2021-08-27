package com.mayisheng.wanandroid.fragment.home;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BannerInfoResponse {
    public List<BannerInfo> mData;
    public int mErrorCode;
    public String mErrorMsg;

    public BannerInfoResponse(String json) {
        try {
            parseJson(json);
        }catch (Exception e) {

        }
    }

    private void parseJson(String json) throws Exception{
        JSONObject jsonObject = new JSONObject(json);
        mErrorCode = jsonObject.optInt("errorCode");
        mErrorMsg = jsonObject.optString("errorMsg");
        JSONArray jsonArray = jsonObject.optJSONArray("data");
        mData = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject dataJsonObj = jsonArray.getJSONObject(i);
            BannerInfo bannerInfo = new BannerInfo();
            bannerInfo.setDesc(dataJsonObj.optString("desc"));
            bannerInfo.setImagePath(dataJsonObj.optString("imagePath"));
            bannerInfo.setTitle(dataJsonObj.optString("title"));
            bannerInfo.setUrl(dataJsonObj.optString("url"));
            mData.add(bannerInfo);
        }
    }
}
