package com.example.lenovo.zhihudaily.module.main.comments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.lenovo.zhihudaily.R;
import com.example.lenovo.zhihudaily.module.base.FragmentContainerActivity;

public class CommentsActivity extends FragmentContainerActivity { ;

    public static final String KEY_ID="id";

    private String id;

    public static Intent newIntent(Context context, String id){
        Intent intent=new Intent(context, CommentsActivity.class);
        intent.putExtra(KEY_ID, id);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return CommentsFragment.newInstance(id);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_comments;
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.fragment_container;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        id=getIntent().getStringExtra(KEY_ID);
        super.onCreate(savedInstanceState);

    }
}
