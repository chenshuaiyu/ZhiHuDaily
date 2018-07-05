package com.example.lenovo.zhihudaily.module.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.lenovo.zhihudaily.module.main.StoryDetail.StoryDetailFragment;
import com.example.lenovo.zhihudaily.module.main.comments.CommentsFragment;

/**
 * Coder : chenshuaiyu
 * Time : 2018/4/29 16:42
 */
public abstract class FragmentContainerActivity extends AppCompatActivity {

    private FragmentTransaction mTransaction;
    private FragmentManager mManager;
    private Fragment mCurrentFragment;

    public abstract Fragment createFragment();

    @LayoutRes
    public abstract int getLayoutResId();

    public abstract int getFragmentContainerId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        mManager = getSupportFragmentManager();

        mCurrentFragment= mManager.findFragmentById(getFragmentContainerId());
        if(null == mCurrentFragment){
            mCurrentFragment=createFragment();
            mManager.beginTransaction()
                    .add(getFragmentContainerId(), mCurrentFragment)
                    .commit();
        }
    }

    public void replaceFragment(Fragment fragment){
        if(mCurrentFragment==null
                || !mCurrentFragment.getClass().getName().equals(fragment.getClass().getName())){
            mCurrentFragment=fragment;
            mManager.beginTransaction()
                    .replace(getFragmentContainerId(), mCurrentFragment)
                    .commit();
        }
    }

}
