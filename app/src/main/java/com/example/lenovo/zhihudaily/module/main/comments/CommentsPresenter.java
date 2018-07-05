package com.example.lenovo.zhihudaily.module.main.comments;

import com.example.lenovo.zhihudaily.bean.Comments;
import com.example.lenovo.zhihudaily.bean.StoryExtra;
import com.example.lenovo.zhihudaily.http.Api;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Coder : chenshuaiyu
 * Time : 2018/6/23 22:51
 */
public class CommentsPresenter implements CommentsContract.Presenter {

    private CommentsContract.View mView;

    public CommentsPresenter(CommentsContract.View view) {
        mView = view;
    }

    @Override
    public void loadshortCommentsData(String id) {
        Api.getZhihuService().getShortComments(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Comments>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Comments value) {
                        mView.showShortCommentsData(value.getComments());
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
    public void loadLongCommentsData(String id) {
        Api.getZhihuService().getLongComments(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Comments>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Comments value) {
                        mView.showLongCommentsData(value.getComments());
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
    public void loadTitleCommentsNumber(String id) {
        Api.getZhihuService().getCommentsNumber(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoryExtra>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(StoryExtra value) {
                        mView.showTitle(value.getComments());
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
