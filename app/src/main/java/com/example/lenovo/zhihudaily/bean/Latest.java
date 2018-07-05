package com.example.lenovo.zhihudaily.bean;

import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2018/4/29 21:52
 */
public class Latest {

    /**
     * date : 20180429
     * stories : [{"images":["https://pic2.zhimg.com/v2-503cf62e8de2b8994087b469c338c9bd.jpg"],"type":0,"id":9680707,"ga_prefix":"042921","title":"这位姑娘，你可知你犯下了怎样的错误？"},{"title":"在家如何制作好吃的牛肉干？","ga_prefix":"042919","images":["https://pic1.zhimg.com/v2-b37379f8e3d96ad54bcadd4a088bac18.jpg"],"multipic":true,"type":0,"id":9679855},{"images":["https://pic1.zhimg.com/v2-8d45b5108f1fd38da07726a1c1e91c74.jpg"],"type":0,"id":9677735,"ga_prefix":"042917","title":"学校是如何扼杀我们的创造力的？"},{"images":["https://pic3.zhimg.com/v2-e7712ae37910a873f40da80cdd70c11e.jpg"],"type":0,"id":9679796,"ga_prefix":"042916","title":"止痛药副作用大、会上瘾，你听谁说的？"},{"images":["https://pic2.zhimg.com/v2-839b5b780cfd8dbc13dd64dd041f1435.jpg"],"type":0,"id":9679594,"ga_prefix":"042915","title":"在疾控中心工作，谈谈狂犬疫苗的副作用"},{"images":["https://pic3.zhimg.com/v2-2a79937922ba8ce0be80212dc0a29c6a.jpg"],"type":0,"id":9680028,"ga_prefix":"042913","title":"养孩子最大的问题，就是父母们太把它当「问题」了"},{"images":["https://pic3.zhimg.com/v2-e977dc98d2218b2e45b19453c39dc676.jpg"],"type":0,"id":9678343,"ga_prefix":"042912","title":"大误 · 一勺老干妈"},{"images":["https://pic2.zhimg.com/v2-6e7f57ff4725e0eee0658bfa5548b491.jpg"],"type":0,"id":9680396,"ga_prefix":"042910","title":"A 股区块链第一股真的要来了"},{"title":"零差评雪碧煮鸡爪，就爱这口酸酸辣辣甜甜","ga_prefix":"042909","images":["https://pic4.zhimg.com/v2-9c48e3c5220e43233bf6765a100b6fbf.jpg"],"multipic":true,"type":0,"id":9680688},{"title":"亲爱的刘看山 · 看山和朋友们","ga_prefix":"042908","images":["https://pic4.zhimg.com/v2-58f758e7b5c8b7f141075b14cf5ebcf7.jpg"],"multipic":true,"type":0,"id":9680713},{"images":["https://pic2.zhimg.com/v2-9aafbdec2682f4ff00eecee76c680cc5.jpg"],"type":0,"id":9679752,"ga_prefix":"042908","title":"城市青年生活故事 · 那么蓝的天那么大的滇池"},{"images":["https://pic1.zhimg.com/v2-01b0cf14a016b1fc49863a863af60de8.jpg"],"type":0,"id":9679935,"ga_prefix":"042908","title":"看人家猩猩袋鼠，不做力量训练肌肉也很发达，再看看你\u2026\u2026"},{"images":["https://pic4.zhimg.com/v2-55447e90bd6daecf56352030ea93efdb.jpg"],"type":0,"id":9680756,"ga_prefix":"042907","title":"本周热门精选 · 人在职场"},{"images":["https://pic3.zhimg.com/v2-4af90413376fdd80aef018c58b241a3e.jpg"],"type":0,"id":9678990,"ga_prefix":"042907","title":"如果你一直找不到女朋友，不如试试改个名字"},{"images":["https://pic3.zhimg.com/v2-ccbe056ae488abaabb87270f7a48a442.jpg"],"type":0,"id":9678749,"ga_prefix":"042907","title":"别给你的领导加那么大「光环」，职场上用不着战战兢兢"},{"images":["https://pic3.zhimg.com/v2-315d7908461aee2eb9baf9017833f36e.jpg"],"type":0,"id":9680511,"ga_prefix":"042906","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic2.zhimg.com/v2-141374d1cb00870ef0f4d7a69e7bd239.jpg","type":0,"id":9680756,"ga_prefix":"042907","title":"本周热门精选 · 人在职场"},{"image":"https://pic3.zhimg.com/v2-5b7380a302a25a96ea8c3d92bc4d7266.jpg","type":0,"id":9680713,"ga_prefix":"042908","title":"亲爱的刘看山 · 看山和朋友们"},{"image":"https://pic1.zhimg.com/v2-5f9166d45e9195447481ed46f05018c0.jpg","type":0,"id":9680709,"ga_prefix":"042818","title":"刘爸刘妈的日常 · 甜蜜的烦恼"},{"image":"https://pic4.zhimg.com/v2-818b5a41534e08f573c231bd00fb364b.jpg","type":0,"id":9680396,"ga_prefix":"042910","title":"A 股区块链第一股真的要来了"},{"image":"https://pic1.zhimg.com/v2-fd6fdc3dca89c9d9d1c4f8ef230f10cc.jpg","type":0,"id":9679594,"ga_prefix":"042915","title":"在疾控中心工作，谈谈狂犬疫苗的副作用"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

}
