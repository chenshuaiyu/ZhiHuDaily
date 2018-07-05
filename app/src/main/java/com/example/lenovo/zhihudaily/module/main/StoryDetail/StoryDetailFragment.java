package com.example.lenovo.zhihudaily.module.main.StoryDetail;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.lenovo.zhihudaily.R;
import com.example.lenovo.zhihudaily.bean.StoriesBean;
import com.example.lenovo.zhihudaily.bean.StoryDetail;
import com.example.lenovo.zhihudaily.bean.TopStoriesBean;
import com.example.lenovo.zhihudaily.http.Api;
import com.example.lenovo.zhihudaily.module.base.BaseFragment;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Coder : chenshuaiyu
 * Time : 2018/4/30 15:35
 */
public class StoryDetailFragment extends BaseFragment implements StoryDetailContract.View {

    private StoryDetailContract.Presenter mPresenter;

    private String title;
    private String image;
    private String id;

    public static final String KEY_STORIESBEAN="StoriesBean";
    public static final String KEY_TOPSTORIESBEAN="TopStoriesBean";

    private WebView mWebView;
    private ProgressBar mProgressBar;

    public static StoryDetailFragment newStoriesBeanInstance(StoriesBean storiesBean) {
        StoryDetailFragment fragment = new StoryDetailFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable(KEY_STORIESBEAN, storiesBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static StoryDetailFragment newTopStoriesBeanInstance(TopStoriesBean topStoriesBean) {
        StoryDetailFragment fragment = new StoryDetailFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable(KEY_TOPSTORIESBEAN, topStoriesBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_story_detail;
    }

    @Override
    public void createView(View view, @Nullable Bundle savedInstanceState) {
        mPresenter=new StoryDetailPresenter(this);

        mProgressBar=view.findViewById(R.id.progress_bar);

        if(StoryDetailActivity.currentFlag== StoryDetailActivity.FLAG_TOPSTORIESBEAN){
            TopStoriesBean topStoriesBean= (TopStoriesBean) getArguments().getSerializable(KEY_TOPSTORIESBEAN);
            title=topStoriesBean.getTitle();
//            image=topStoriesBean.getImage();
            id=""+topStoriesBean.getId();
        }else if(StoryDetailActivity.currentFlag== StoryDetailActivity.FLAG_STORIESBEAN){
            StoriesBean storiesBean= (StoriesBean) getArguments().getSerializable(KEY_STORIESBEAN);
            title=storiesBean.getTitle();
//            image=storiesBean.getImages().get(0);
            id=""+storiesBean.getId();
        }

        mPresenter.loadShareUrl(id);

        mWebView=view.findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);

    }

    @Override
    public void setPresenter(StoryDetailContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void setWebView(String url) {
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgressBar.setVisibility(View.VISIBLE);
                mWebView.setVisibility(View.GONE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.GONE);
                mWebView.setVisibility(View.VISIBLE);
            }
        });
        mWebView.loadUrl(url);
    }

}
