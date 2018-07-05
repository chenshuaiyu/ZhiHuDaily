package com.example.lenovo.zhihudaily.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2018/4/30 7:32
 */
public class StoriesBean implements Serializable {
    /**
     * images : ["https://pic2.zhimg.com/v2-503cf62e8de2b8994087b469c338c9bd.jpg"]
     * type : 0
     * id : 9680707
     * ga_prefix : 042921
     * title : 这位姑娘，你可知你犯下了怎样的错误？
     * multipic : true
     */

    private int type;
    private int id;
    private String ga_prefix;
    private String title;
    private boolean multipic;
    private List<String> images;

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

    public boolean isMultipic() {
        return multipic;
    }

    public void setMultipic(boolean multipic) {
        this.multipic = multipic;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
