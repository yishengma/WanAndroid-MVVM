package com.mayisheng.wanandroid.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.mayisheng.wanandroid.R;
import com.mayisheng.wanandroid.fragment.base.BaseFragment;
import com.mayisheng.wanandroid.fragment.home.HomeFragment;
import com.mayisheng.wanandroid.fragment.mine.MineFragment;
import com.mayisheng.wanandroid.fragment.officialaccounts.OfficialAccountFragment;
import com.mayisheng.wanandroid.fragment.project.ProjectFragment;
import com.mayisheng.wanandroid.fragment.square.SquareFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    private ViewPager2 mViewPager;
    private TabLayout mTabLayout;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
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
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initView(view);
        return view;
    }

    private void initView(View rootView) {
        List<BaseFragment> fragments = getFragment();
        mTabLayout = rootView.findViewById(R.id.tab_layout);
        initTabLayout(fragments);
        mViewPager = rootView.findViewById(R.id.view_page);
        mViewPager.setAdapter(new FragmentAdapter(this, fragments));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mViewPager.setUserInputEnabled(false);

    }

    private List<BaseFragment> getFragment() {
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance());
        fragments.add(ProjectFragment.newInstance());
        fragments.add(SquareFragment.newInstance());
        fragments.add(OfficialAccountFragment.newInstance());
        fragments.add(MineFragment.newInstance());
        return fragments;
    }

    private void initTabLayout(List<BaseFragment> fragments) {
        for (BaseFragment f : fragments) {
            mTabLayout.addTab(mTabLayout.newTab().setText(f.getTitle()));
        }
    }


}