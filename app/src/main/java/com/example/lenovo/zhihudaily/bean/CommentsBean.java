package com.example.lenovo.zhihudaily.bean;

/**
 * Coder : chenshuaiyu
 * Time : 2018/5/1 15:59
 */
public class CommentsBean {
    /**
     * author : 李典杰
     * content : 我举个简单的栗子，数据不用太具体，大致符合楼主的比例就行。所有女性里面有100人喜欢175的男生，有50人喜欢180的男生，然后一共有200名175的男生，50名180的男生。那么问题来了，哪个身高的男生比较抢手？
     * avatar : http://pic1.zhimg.com/d0fc6d9a4_im.jpg
     * time : 1525159125
     * id : 31612692
     * likes : 0
     * reply_to : {"content":"其他条件一样，一个175和一个185的男生，你问妹子会选哪个。妹子不是不想选高的，而是条件只能允许自己选175的。就像我选女生，我还想选新垣结衣长泽雅美呢。","status":0,"id":31611236,"author":"团长大人"}
     */

    private String author;
    private String content;
    private String avatar;
    private int time;
    private int id;
    private int likes;
    private ReplyToBean reply_to;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public ReplyToBean getReply_to() {
        return reply_to;
    }

    public void setReply_to(ReplyToBean reply_to) {
        this.reply_to = reply_to;
    }

}
