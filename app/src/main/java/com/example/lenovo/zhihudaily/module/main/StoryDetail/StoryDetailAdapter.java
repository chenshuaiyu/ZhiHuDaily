package com.example.lenovo.zhihudaily.module.main.StoryDetail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2018/4/30 15:43
 */
public class StoryDetailAdapter extends FragmentStatePagerAdapter {

    private List<StoryDetailFragment> mFragmentList;

    public StoryDetailAdapter(FragmentManager fm, List<StoryDetailFragment> fragmentList) {
        super(fm);
        mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
