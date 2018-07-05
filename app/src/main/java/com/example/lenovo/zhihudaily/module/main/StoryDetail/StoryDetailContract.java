package com.example.lenovo.zhihudaily.module.main.StoryDetail;

import com.example.lenovo.zhihudaily.module.base.BasePresenter;
import com.example.lenovo.zhihudaily.module.base.BaseView;

/**
 * Coder : chenshuaiyu
 * Time : 2018/6/18 15:21
 */
public class StoryDetailContract {

    public interface Presenter extends BasePresenter{
        void loadShareUrl(String id);
    }

    public interface View extends BaseView<Presenter>{
        void setWebView(String url);
    }

}
