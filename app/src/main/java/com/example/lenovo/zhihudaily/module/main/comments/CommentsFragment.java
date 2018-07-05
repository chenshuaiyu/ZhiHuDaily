package com.example.lenovo.zhihudaily.module.main.comments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.zhihudaily.R;
import com.example.lenovo.zhihudaily.bean.Comments;
import com.example.lenovo.zhihudaily.bean.CommentsBean;
import com.example.lenovo.zhihudaily.bean.StoryExtra;
import com.example.lenovo.zhihudaily.http.Api;
import com.example.lenovo.zhihudaily.module.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Coder : chenshuaiyu
 * Time : 2018/5/1 16:45
 */
public class CommentsFragment extends BaseFragment implements CommentsContract.View {

    private boolean FLAG_ARROW_IS_DOWN=true;

    private CommentsContract.Presenter mPresenter;

    private int numComments=0;

    public static final String KEY_ID="id";

    private String id;
    private List<CommentsBean> mLongCommentsBeanList;
    private List<CommentsBean> mShortCommentsBeanList;

    private CommentsAdapter mLongCommentsAdapter;
    private CommentsAdapter mShortCommentsAdapter;

    private Toolbar mToolbar;
    private ImageView mArrowImageView;
    private TextView mNumLongCommentsTextView;
    private TextView mNumShortCommentsTextView;
    private RecyclerView mLongCommentsRecyclerView;
    private RecyclerView mShortCommentsRecyclerView;
    private CardView mShortCommentsCardView;

    public static Fragment newInstance(String id) {
        CommentsFragment fragment = new CommentsFragment();
        Bundle args = new Bundle();
        args.putString(KEY_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_comments;
    }

    @Override
    public void createView(View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        mPresenter.loadTitleCommentsNumber(id);
        mPresenter.loadLongCommentsData(id);
        mPresenter.loadshortCommentsData(id);
    }

    private void initView(View view) {
        id=getArguments().getString(KEY_ID,"");
        mArrowImageView =view.findViewById(R.id.arrow_image_view);

        mPresenter=new CommentsPresenter(this);

        mToolbar=view.findViewById(R.id.tool_bar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        setHasOptionsMenu(true);

        mNumLongCommentsTextView=view.findViewById(R.id.num_long_comments);
        mNumShortCommentsTextView=view.findViewById(R.id.num_short_comments);

        mLongCommentsRecyclerView =view.findViewById(R.id.long_comments_recycler_view);
        mShortCommentsRecyclerView =view.findViewById(R.id.short_comments_recycler_view);
        mLongCommentsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mShortCommentsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLongCommentsBeanList=new ArrayList<>();
        mShortCommentsBeanList=new ArrayList<>();
        mLongCommentsAdapter=new CommentsAdapter(getActivity(),mLongCommentsBeanList);
        mShortCommentsAdapter=new CommentsAdapter(getActivity(),mShortCommentsBeanList);
        mLongCommentsRecyclerView.setAdapter(mLongCommentsAdapter);
        mShortCommentsRecyclerView.setAdapter(mShortCommentsAdapter);

        mShortCommentsCardView=view.findViewById(R.id.short_comments_card_view);
        mShortCommentsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrCloseShortComments();
            }
        });

    }

    public void openOrCloseShortComments() {
        if(FLAG_ARROW_IS_DOWN){
            mShortCommentsRecyclerView.setVisibility(View.VISIBLE);
            mArrowImageView.setImageResource(R.drawable.ic_up);
            FLAG_ARROW_IS_DOWN=false;
        }else{
            mShortCommentsRecyclerView.setVisibility(View.GONE);
            mArrowImageView.setImageResource(R.drawable.ic_down);
            FLAG_ARROW_IS_DOWN=true;
        }
    }

    @Override
    public void showTitle(int comments) {
        mToolbar.setTitle(comments+" 条点评");
    }

    @Override
    public void showShortCommentsData(List<CommentsBean> comments) {
        mNumShortCommentsTextView.setText(""+comments.size());
        numComments+=comments.size();
        mShortCommentsBeanList=comments;
        mShortCommentsAdapter.setCommentsList(mShortCommentsBeanList);
        mShortCommentsAdapter.notifyDataSetChanged();
        mToolbar.setTitle(numComments+" 条点评");
    }

    @Override
    public void showLongCommentsData(List<CommentsBean> comments) {
        mNumLongCommentsTextView.setText(""+comments.size());
        numComments+=comments.size();
        mLongCommentsBeanList=comments;
        mLongCommentsAdapter.setCommentsList(mLongCommentsBeanList);
        mLongCommentsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.comments_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                getActivity().finish();
                break;
            case R.id.edit_comment:
                break;
        }
        return true;
    }

    @Override
    public void setPresenter(CommentsContract.Presenter presenter) {
        mPresenter=presenter;
    }
}
