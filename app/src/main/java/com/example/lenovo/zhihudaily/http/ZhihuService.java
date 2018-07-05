package com.example.lenovo.zhihudaily.http;

import com.example.lenovo.zhihudaily.bean.Before;
import com.example.lenovo.zhihudaily.bean.Comments;
import com.example.lenovo.zhihudaily.bean.Latest;
import com.example.lenovo.zhihudaily.bean.StoryDetail;
import com.example.lenovo.zhihudaily.bean.StoryExtra;
import com.example.lenovo.zhihudaily.bean.Theme;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Coder : chenshuaiyu
 * Time : 2018/4/30 7:38
 */
public interface ZhihuService {

    @GET("news/{latest}")
    Observable<Latest> getLatest(@Path("latest") String latest);

    @GET("news/before/{date}")
    Observable<Before> getBefore(@Path("date") String date);

    @GET("news/{id}")
    Observable<StoryDetail> getStoryDetail(@Path("id") String id);

    @GET("story/{id}/long-comments")
    Observable<Comments> getLongComments(@Path("id") String id);

    @GET("story/{id}/short-comments")
    Observable<Comments> getShortComments(@Path("id") String id);

    @GET("theme/{id}")
    Observable<Theme> getTheme(@Path("id") String id);

    @GET("story-extra/{id}")
    Observable<StoryExtra> getCommentsNumber(@Path("id") String id);



}
