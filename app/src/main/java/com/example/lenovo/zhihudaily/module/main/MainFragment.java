package com.example.lenovo.zhihudaily.module.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.lenovo.zhihudaily.R;
import com.example.lenovo.zhihudaily.bean.Before;
import com.example.lenovo.zhihudaily.bean.Latest;
import com.example.lenovo.zhihudaily.bean.StoriesBean;
import com.example.lenovo.zhihudaily.bean.TopStoriesBean;
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
 * Time : 2018/4/29 16:41
 */
public class MainFragment extends BaseFragment implements MainContract.View {
    private static final String TAG = "MainFragment";

    public static final int KEY_NOT_STORY_ITEM=-1;

    private boolean loading=false;
    private MainContract.Presenter mPresenter;
    private String currentDate;

    private List<StoriesBean> mStoriesBeanList;
    private List<TopStoriesBean> mTopStoriesBeanList;
    private MainAdapter mMainAdapter;

    private Toolbar mToolbar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    public static Fragment newInstance() {
        return new MainFragment();
    }
    
    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void createView(View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        mPresenter.getData();
    }

    @SuppressLint("NewApi")
    private void initView(final View view) {
        mPresenter=new MainPresenter(this);

        mSwipeRefreshLayout=view.findViewById(R.id.swipe_refresh);
        mRecyclerView=view.findViewById(R.id.recycler_view);
        mToolbar= view.findViewById(R.id.tool_bar);

        mStoriesBeanList=new ArrayList<>();
        mTopStoriesBeanList=new ArrayList<>();
        mMainAdapter=new MainAdapter(getActivity(),mTopStoriesBeanList,mStoriesBeanList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mMainAdapter);

        mToolbar.setNavigationIcon(R.drawable.ic_navigation);
        mToolbar.setTitle(R.string.home);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        setHasOptionsMenu(true);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    //监听RecyclerView是否滑到底的两种方法
                    //1
                    LinearLayoutManager manager= (LinearLayoutManager) recyclerView.getLayoutManager();
                    int visibleItemCount = manager.getChildCount();
                    int totalItemCount = manager.getItemCount();
                    int pastVisibleItem = manager.findFirstVisibleItemPosition();
                    if (!loading && pastVisibleItem + visibleItemCount >= totalItemCount ) {
                        loading=true;
                        mPresenter.getBeforeData(currentDate);
                    }
                    //2
//                    if (!loading && !mRecyclerView.canScrollVertically(1)) {
//                        loading=true;
//                        mPresenter.getBeforeData(currentDate);
//                    }
                }
            }
        });

        mSwipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getData();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        mMainAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(List list,int id) {
                Intent intent=StoryDetailActivity.newIntent(getActivity(),list,list.get(0).getClass().getName(),id);
                startActivity(intent);
            }
        });

    }

    @Override
    public void showBeforeData(Before before) {
        loading=false;
        currentDate=before.getDate();
        StoriesBean storiesBean=new StoriesBean();
        storiesBean.setId(MainAdapter.FLAG_DATE);
        storiesBean.setTitle(before.getDate());
        mStoriesBeanList.add(storiesBean);
        mStoriesBeanList.addAll(before.getStories());
        mMainAdapter.setList(mTopStoriesBeanList,mStoriesBeanList);
        mMainAdapter.notifyDataSetChanged();
    }

    @Override
    public void showData(Latest latest) {
        currentDate=latest.getDate();
        mTopStoriesBeanList=latest.getTop_stories();
        List<StoriesBean> l=new ArrayList<>();
        StoriesBean s1=new StoriesBean();s1.setId(KEY_NOT_STORY_ITEM);
        StoriesBean s2=new StoriesBean();s2.setId(KEY_NOT_STORY_ITEM);
        l.add(s1);
        l.add(s2);
        l.addAll(latest.getStories());
        mStoriesBeanList=l;
        mMainAdapter.setList(mTopStoriesBeanList,mStoriesBeanList);
        mMainAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //利用回调
                mCallbacks.openDrawerLayout();
                break;
            case R.id.night_mode:
                break;
            case R.id.setting_options:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
            case R.id.information:
                break;

        }
        return true;
    }


    Callbacks mCallbacks;
    void setCallbacks(Callbacks callbacks){
        mCallbacks=callbacks;
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter=presenter;
    }
}
