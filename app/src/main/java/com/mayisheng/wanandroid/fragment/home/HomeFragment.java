package com.mayisheng.wanandroid.fragment.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mayisheng.wanandroid.R;
import com.mayisheng.wanandroid.Utils.OkHttpUtil;
import com.mayisheng.wanandroid.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";
    private RecyclerView mRecyclerView;
    private HomeRvAdapter mHomeRvAdapter;
    private List<HomeInfo> mHomeInfos;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    @Override
    public String getTitle() {
        return "首页";
    }

    private void initView(View rootView) {
        mRecyclerView = rootView.findViewById(R.id.recycler_view);
        mHomeInfos = new ArrayList<>();
        mHomeRvAdapter = new HomeRvAdapter(mHomeInfos);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        mRecyclerView.setAdapter(mHomeRvAdapter);
        OkHttpUtil.getInstance().httpGet("https://wanandroid.com/banner/json", new OkHttpUtil.ICallback() {
            @Override
            public void invoke(String string) {
                BannerInfoResponse response = new BannerInfoResponse(string);
                Log.i(TAG, "invoke: " + response.mData.size());
                HomeInfo homeInfo = new HomeInfo(response.mData);
                mHomeInfos.add(homeInfo);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mHomeRvAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}