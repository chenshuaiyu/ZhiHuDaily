package com.example.lenovo.zhihudaily.module.main.StoryDetail;

import com.example.lenovo.zhihudaily.bean.StoryDetail;
import com.example.lenovo.zhihudaily.http.Api;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Coder : chenshuaiyu
 * Time : 2018/6/18 15:18
 */
public class StoryDetailPresenter implements StoryDetailContract.Presenter {

    private StoryDetailContract.View mView;

    public StoryDetailPresenter(StoryDetailContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void loadShareUrl(String id) {
        Api.getZhihuService().getStoryDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoryDetail>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(StoryDetail value) {
                        mView.setWebView(value.getShare_url());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {

    }
}
