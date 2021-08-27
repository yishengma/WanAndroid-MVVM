package com.mayisheng.wanandroid.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.mayisheng.wanandroid.fragment.base.BaseFragment;

import java.util.List;

public class FragmentAdapter extends FragmentStateAdapter {
    private List<BaseFragment> mFragments;

    public FragmentAdapter(@NonNull Fragment fragment, List<BaseFragment> fragments) {
        super(fragment);
        mFragments = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragments.size();
    }
}
