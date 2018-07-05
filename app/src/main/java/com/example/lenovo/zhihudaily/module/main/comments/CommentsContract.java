package com.example.lenovo.zhihudaily.module.main.comments;

import com.example.lenovo.zhihudaily.bean.CommentsBean;
import com.example.lenovo.zhihudaily.module.base.BasePresenter;
import com.example.lenovo.zhihudaily.module.base.BaseView;

import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2018/6/23 22:47
 */
public class CommentsContract {

    public interface Presenter extends BasePresenter{
        void loadshortCommentsData(String id);
        void loadLongCommentsData(String id);
        void loadTitleCommentsNumber(String id);
    }

    public interface View extends BaseView<Presenter>{
        void showTitle(int comments);
        void showShortCommentsData(List<CommentsBean> comments);
        void showLongCommentsData(List<CommentsBean> comments);
    }

}
