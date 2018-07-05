package com.example.lenovo.zhihudaily.module.main;

import com.example.lenovo.zhihudaily.bean.Before;
import com.example.lenovo.zhihudaily.bean.Latest;
import com.example.lenovo.zhihudaily.module.base.BasePresenter;
import com.example.lenovo.zhihudaily.module.base.BaseView;

/**
 * Coder : chenshuaiyu
 * Time : 2018/6/17 23:00
 */
public class MainContract {

    public interface Presenter extends BasePresenter{
        void getBeforeData(String currentDate);
        void getData();
    }

    public interface View extends BaseView<Presenter>{
        void showBeforeData(Before before);
        void showData(Latest latest);
    }

}
