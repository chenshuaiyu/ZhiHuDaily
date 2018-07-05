package com.example.lenovo.zhihudaily.http;

import com.example.lenovo.zhihudaily.constant.URL;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Coder : chenshuaiyu
 * Time : 2018/4/30 7:36
 */
public class Api {

    private static ZhihuService sZhihuService;
    private static OkHttpClient sOkHttpClient=new OkHttpClient();
    private static Converter.Factory sGsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory sRxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();

    public static ZhihuService getZhihuService(){
        if(null == sZhihuService){
            Retrofit retrofit=new Retrofit.Builder()
                    .client(sOkHttpClient)
                    .baseUrl(URL.BASEURL)
                    .addConverterFactory(sGsonConverterFactory)
                    .addCallAdapterFactory(sRxJavaCallAdapterFactory)
                    .build();
            sZhihuService=retrofit.create(ZhihuService.class);
        }
        return sZhihuService;
    }

}
