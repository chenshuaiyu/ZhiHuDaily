package com.example.lenovo.zhihudaily.module;

import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2018/5/13 19:24
 */
public interface OnItemClickListener<T> {
    void onClick(List<T> list, int id);
}
