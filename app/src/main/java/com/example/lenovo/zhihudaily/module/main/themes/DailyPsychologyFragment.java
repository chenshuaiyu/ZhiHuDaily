package com.example.lenovo.zhihudaily.module.main.themes;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.lenovo.zhihudaily.R;
import com.example.lenovo.zhihudaily.bean.StoriesBean;
import com.example.lenovo.zhihudaily.bean.Theme;
import com.example.lenovo.zhihudaily.constant.ThemesId;
import com.example.lenovo.zhihudaily.http.Api;
import com.example.lenovo.zhihudaily.module.Callbacks;
import com.example.lenovo.zhihudaily.module.OnItemClickListener;
import com.example.lenovo.zhihudaily.module.base.BaseFragment;
import com.example.lenovo.zhihudaily.module.main.StoryDetail.StoryDetailActivity;
import com.example.lenovo.zhihudaily.module.main.settings.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Coder : chenshuaiyu
 * Time : 2018/5/2 12:25
 */
public class DailyPsychologyFragment extends BaseFragment {

    private ThemesAdapter mAdapter;

    private Toolbar mToolbar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    public static Fragment newInstance() {
        return new DailyPsychologyFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void createView(View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        loadData();
    }

    private void loadData() {
        Api.getZhihuService().getTheme(ThemesId.DailyPsychology)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Theme>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Theme value) {
                        showData(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void showData(Theme value) {
        List<StoriesBean> storiesBeanList=new ArrayList<>();
        storiesBeanList.add(new StoriesBean());
        storiesBeanList.add(new StoriesBean());
        storiesBeanList.addAll(value.getStories());
        mAdapter.setList(value,storiesBeanList, value.getEditors());
        mAdapter.notifyDataSetChanged();
    }

    private void initView(View view) {
        mSwipeRefreshLayout=view.findViewById(R.id.swipe_refresh);
        mRecyclerView=view.findViewById(R.id.recycler_view);
        mToolbar= view.findViewById(R.id.tool_bar);

        mAdapter=new ThemesAdapter(getActivity(),new Theme());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        mToolbar.setNavigationIcon(R.drawable.ic_navigation);
        mToolbar.setTitle(R.string.daily_psychology);
        mToolbar.setTitleTextColor(Color.WHITE);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        setHasOptionsMenu(true);

        mSwipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(List list, int id) {
                Intent intent= StoryDetailActivity.newIntent(getActivity(),list,list.get(0).getClass().getName(),id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.themes_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //利用回调
                mCallbacks.openDrawerLayout();
                break;
            case R.id.setting_options:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
        }
        return true;
    }

    Callbacks mCallbacks;
    public void setCallbacks(Callbacks callbacks){
        mCallbacks=callbacks;
    }

}
