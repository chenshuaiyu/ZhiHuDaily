package com.example.lenovo.zhihudaily.module.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.lenovo.zhihudaily.R;
import com.example.lenovo.zhihudaily.bean.StoriesBean;
import com.example.lenovo.zhihudaily.bean.TopStoriesBean;
import com.example.lenovo.zhihudaily.module.OnItemClickListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2018/4/30 7:56
 */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static int FLAG_DATE = -1;

    private static final String TAG = "MainAdapter";

    private final int TYPE_TOP_STORY = 1;
    private final int TYPE_TODAY_NEWS = 2;
    private final int TYPE_STORY = 3;
    private final int TYPE_DATE = 4;

    private List<StoriesBean> mStoriesBeanList;
    private List<TopStoriesBean> mTopStoriesBeanList;
    private Context mContext;

    public MainAdapter(Context context, List<TopStoriesBean> topStoriesBeanList, List<StoriesBean> storiesBeanList) {
        mStoriesBeanList = storiesBeanList;
        mTopStoriesBeanList = topStoriesBeanList;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_TOP_STORY;
        else if (position == 1)
            return TYPE_TODAY_NEWS;
        else if (FLAG_DATE == mStoriesBeanList.get(position).getId())
            return TYPE_DATE;
        else
            return TYPE_STORY;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            default:
            case TYPE_TOP_STORY:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top_story, parent, false);
                return new TopStoryViewHolder(view);
            case TYPE_TODAY_NEWS:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_today_news, parent, false);
                return new TodayNewsViewHolder(view);
            case TYPE_DATE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date, parent, false);
                return new DateViewHolder(view);
            case TYPE_STORY:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story, parent, false);
                return new StoryViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TopStoryViewHolder) {
            ((TopStoryViewHolder) holder).bind(position);
        } else if (holder instanceof TodayNewsViewHolder) {
            ((TodayNewsViewHolder) holder).bind(position);
        } else if (holder instanceof DateViewHolder) {
            ((DateViewHolder) holder).bind(position);
        } else if (holder instanceof StoryViewHolder) {
            ((StoryViewHolder) holder).bind(position);
        }

    }

    @Override
    public int getItemCount() {
        return mStoriesBeanList == null ? 0 : mStoriesBeanList.size();
    }

    public void setList(List<TopStoriesBean> topStoriesBeanList, List<StoriesBean> storiesBeanList) {
        mStoriesBeanList = storiesBeanList;
        mTopStoriesBeanList = topStoriesBeanList;
    }


    class StoryViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private ImageView mImageView;

        public StoryViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text_view);
            mImageView = itemView.findViewById(R.id.image_view);
        }

        public void bind(final int position) {
            mTextView.setText(mStoriesBeanList.get(position).getTitle());
            Glide.with(mContext).load(mStoriesBeanList.get(position).getImages().get(0)).into(mImageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(mStoriesBeanList, mStoriesBeanList.get(position).getId());
                }
            });
        }
    }

    class TopStoryViewHolder extends RecyclerView.ViewHolder {

        private SliderLayout backgroundImageView;

        public TopStoryViewHolder(View itemView) {
            super(itemView);
            backgroundImageView = itemView.findViewById(R.id.background_image_view);
        }

        public void bind(final int position) {
            for (final TopStoriesBean bean : mTopStoriesBeanList) {
                TextSliderView textSliderView = new TextSliderView(mContext);
                textSliderView
                        .description(bean.getTitle())
                        .image(bean.getImage())
                        .setScaleType(BaseSliderView.ScaleType.Fit);
                textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {
                        mOnItemClickListener.onClick(mTopStoriesBeanList, mTopStoriesBeanList.get(mTopStoriesBeanList.indexOf(bean)).getId());
                    }
                });
                backgroundImageView.addSlider(textSliderView);
            }
            backgroundImageView.setDuration(5000);
        }
    }

    class TodayNewsViewHolder extends RecyclerView.ViewHolder {

        public TodayNewsViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(int position) {

        }
    }

    class DateViewHolder extends RecyclerView.ViewHolder {

        private TextView dateTextView;

        public DateViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.date_text_view);
        }

        public void bind(int position) {
            String d="";
            int date = Integer.parseInt(mStoriesBeanList.get(position).getTitle());
            int year = date / 10000;
            int month = (date - year * 10000) / 100;
            int day = date - year * 10000 - month * 100;
            d+=year+"-"+month+"-"+day;
            SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
            String week="";
            try {
                week=getWeekOfDate(f.parse(d));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dateTextView.setText(month+" 月 "+day+" 日 "+week);
        }
    }

    public static String getWeekOfDate(Date date) {
        String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if(intWeek < 0)
            intWeek=0;
        return weekDaysName[intWeek];
    }

    OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

}
