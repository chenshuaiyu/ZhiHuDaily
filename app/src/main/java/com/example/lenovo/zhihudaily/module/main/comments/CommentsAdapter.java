package com.example.lenovo.zhihudaily.module.main.comments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.zhihudaily.R;
import com.example.lenovo.zhihudaily.bean.CommentsBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2018/5/1 17:03
 */
public class CommentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CommentsBean> mCommentsBeanList;
    private Context mContext;

    public CommentsAdapter(Context context, List<CommentsBean> commentsBeanList) {
        mCommentsBeanList = commentsBeanList;
        mContext = context;
    }

    public void setCommentsList(List<CommentsBean> commentsBeanList) {
        mCommentsBeanList = commentsBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_comments, parent, false);
        return new CommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CommentsViewHolder) holder).bind(position);
    }

    @Override
    public int getItemCount() {
        return mCommentsBeanList.size();
    }


    class CommentsViewHolder extends RecyclerView.ViewHolder {

        private ImageView avatarImageView;
        private ImageView thumbUpImageView;
        private TextView contentTextView;
        private TextView authorTextView;
        private TextView likesTextView;
        private TextView timeTextView;

        public CommentsViewHolder(View itemView) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.avatar);
            thumbUpImageView = itemView.findViewById(R.id.thumb_up_image_view);
            contentTextView = itemView.findViewById(R.id.content_text_view);
            authorTextView = itemView.findViewById(R.id.author_text_view);
            likesTextView = itemView.findViewById(R.id.likes_text_view);
            timeTextView = itemView.findViewById(R.id.time_text_view);
        }

        public void bind(int position) {
            CommentsBean commentsBean = mCommentsBeanList.get(position);
            Glide.with(mContext).load(commentsBean.getAvatar()).into(avatarImageView);
            contentTextView.setText(commentsBean.getContent());
            authorTextView.setText(commentsBean.getAuthor());
            likesTextView.setText("" + commentsBean.getLikes());
            //利用毫秒值计算时间
            SimpleDateFormat f = new SimpleDateFormat("MM-dd HH:mm");
            Date date = new Date(commentsBean.getTime() * 1000);
            timeTextView.setText(f.format(date));
        }

    }


}
