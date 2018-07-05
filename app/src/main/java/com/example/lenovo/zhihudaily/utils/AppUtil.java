package com.example.lenovo.zhihudaily.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Coder : chenshuaiyu
 * Time : 2018/7/5 17:58
 */
public class AppUtil {

    public static void share(Context context,String text){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.putExtra(Intent.EXTRA_SUBJECT, "好友分享");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, "分享 - 知乎日报"));
    }


}
