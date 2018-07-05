package com.example.lenovo.zhihudaily.module.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Coder : chenshuaiyu
 * Time : 2018/4/29 16:41
 */
public abstract class BaseFragment extends Fragment {

    View mView;

    public abstract int getLayoutId();

    public abstract void createView(View view,@Nullable Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        createView(mView, savedInstanceState);
        return mView;
    }
}
