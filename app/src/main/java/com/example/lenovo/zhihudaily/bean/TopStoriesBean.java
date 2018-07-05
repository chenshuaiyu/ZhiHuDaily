package com.example.lenovo.zhihudaily.bean;

import java.io.Serializable;

/**
 * Coder : chenshuaiyu
 * Time : 2018/4/30 7:34
 */
public class TopStoriesBean implements Serializable {
    /**
     * image : https://pic2.zhimg.com/v2-141374d1cb00870ef0f4d7a69e7bd239.jpg
     * type : 0
     * id : 9680756
     * ga_prefix : 042907
     * title : 本周热门精选 · 人在职场
     */

    private String image;
    private int type;
    private int id;
    private String ga_prefix;
    private String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
