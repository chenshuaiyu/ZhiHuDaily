package com.example.lenovo.zhihudaily.module.main.themes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.zhihudaily.R;
import com.example.lenovo.zhihudaily.bean.EditorsBean;
import com.example.lenovo.zhihudaily.bean.StoriesBean;
import com.example.lenovo.zhihudaily.bean.Theme;
import com.example.lenovo.zhihudaily.module.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2018/5/2 12:50
 */
public class ThemesAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private Theme mTheme;
    private List<StoriesBean> mStoriesBeanList=new ArrayList<>();
    private List<EditorsBean> mEditorsBeanList;

    private int TYPE_TOP=0;
    private int TYPE_AUTHOR=1;
    private int TYPE_OTHRESBEAN=2;

    public ThemesAdapter(Context context, Theme theme) {
        mContext = context;
        mTheme = theme;
    }

    public void setList(Theme theme, List<StoriesBean> storiesBeanList, List<EditorsBean> editorsBeanList) {
        mTheme=theme;
        mStoriesBeanList = storiesBeanList;
        mEditorsBeanList = editorsBeanList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return TYPE_TOP;
            case 1:
                return TYPE_AUTHOR;
            default:
                return TYPE_OTHRESBEAN;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType==TYPE_TOP){
            view= LayoutInflater.from(mContext).inflate(R.layout.item_themes_top_story, parent,false);
            return new TopViewHolder(view);
        }else if(viewType==TYPE_AUTHOR){
            view= LayoutInflater.from(mContext).inflate(R.layout.item_author, parent,false);
            return new AuthorViewHolder(view);
        }else{
            view= LayoutInflater.from(mContext).inflate(R.layout.item_story, parent,false);
            return new OthersBeanViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
         if(holder instanceof TopViewHolder){
             ((TopViewHolder)holder).bind();
         }else if(holder instanceof AuthorViewHolder){
             ((AuthorViewHolder)holder).bind();
         }else if(holder instanceof OthersBeanViewHolder){
             ((OthersBeanViewHolder)holder).bind(position);
         }
    }

    @Override
    public int getItemCount() {
        return mStoriesBeanList.size();
    }

    class TopViewHolder extends RecyclerView.ViewHolder {

        private ImageView backgroundImageView;
        private TextView mTextView;

        public TopViewHolder(View itemView) {
            super(itemView);
            backgroundImageView=itemView.findViewById(R.id.background_image_view);
            mTextView=itemView.findViewById(R.id.text_view);
        }
        public void bind(){
            mTextView.setText(mTheme.getDescription());
            Glide.with(mContext).load(mTheme.getBackground()).into(backgroundImageView);
        }
    }

    class AuthorViewHolder extends RecyclerView.ViewHolder {

        private ImageView avatarImageView;

        public AuthorViewHolder(View itemView) {
            super(itemView);
            avatarImageView=itemView.findViewById(R.id.avatar_image_view);
        }
        public void bind(){
            Glide.with(mContext).load(mEditorsBeanList.get(0).getAvatar()).into(avatarImageView);
        }
    }

    class OthersBeanViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private ImageView mImageView;

        public OthersBeanViewHolder(View itemView) {
            super(itemView);
            mTextView=itemView.findViewById(R.id.text_view);
            mImageView=itemView.findViewById(R.id.image_view);
        }
        public void bind(final int position){
            mTextView.setText(mStoriesBeanList.get(position).getTitle());
            if(null != mStoriesBeanList.get(position).getImages()){
                Glide.with(mContext).load(mStoriesBeanList.get(position).getImages().get(0)).into(mImageView);
            }else{
                mImageView.setVisibility(View.GONE);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(mStoriesBeanList,mStoriesBeanList.get(position).getId());
                }
            });
        }
    }

    OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mOnItemClickListener=onItemClickListener;
    }

}
