package com.example.lenovo.zhihudaily.module.main.StoryDetail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.lenovo.zhihudaily.R;
import com.example.lenovo.zhihudaily.bean.StoriesBean;
import com.example.lenovo.zhihudaily.bean.TopStoriesBean;
import com.example.lenovo.zhihudaily.module.main.MainFragment;
import com.example.lenovo.zhihudaily.module.main.comments.CommentsActivity;
import com.example.lenovo.zhihudaily.utils.AppUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StoryDetailActivity extends AppCompatActivity {

    public static final int FLAG_STORIESBEAN=1;
    public static final int FLAG_TOPSTORIESBEAN=2;

    public static final String KEY_LIST="list";
    public static final String KEY_CLASS_NAME ="class_name";
    public static final String KEY_ID ="id";

    private int count = -1;

    public static int currentFlag;

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private FragmentManager mFragmentManager;
    private StoryDetailAdapter mDetailAdapter;
    private List<StoriesBean> mStoriesBeanList =new ArrayList<>();
    private List<StoryDetailFragment> mStoryDetailFragmentList =new ArrayList<>();


    public static Intent newIntent(Context context, List list, String className, int id){
        Intent intent=new Intent(context,StoryDetailActivity.class);
        intent.putExtra(KEY_LIST, (Serializable) list);
        intent.putExtra(KEY_CLASS_NAME, className);
        intent.putExtra(KEY_ID, id);
        return intent;
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        mToolbar=findViewById(R.id.tool_bar);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(mToolbar);

        Intent intent=getIntent();
        int id=intent.getIntExtra(KEY_ID, -1);
        String className=intent.getStringExtra(KEY_CLASS_NAME);

        if(className.equals(StoriesBean.class.getName())){
            currentFlag=FLAG_STORIESBEAN;
            mStoriesBeanList = (List<StoriesBean>) intent.getSerializableExtra(KEY_LIST);
            List<StoriesBean> temp=new ArrayList<>();
            for (int i = 0; i < mStoriesBeanList.size(); i++) {
                if(mStoriesBeanList.get(i).getId()!= MainFragment.KEY_NOT_STORY_ITEM){
                    temp.add(mStoriesBeanList.get(i));
                }
            }
            mStoriesBeanList =temp;
            for (int i = 0; i < mStoriesBeanList.size(); i++) {
                mStoryDetailFragmentList.add(StoryDetailFragment.newStoriesBeanInstance(mStoriesBeanList.get(i)));
                if(mStoriesBeanList.get(i).getId()==id){
                    count=i;
                }
            }
        }else if(className.equals(TopStoriesBean.class.getName())){
            currentFlag=FLAG_TOPSTORIESBEAN;
            List<TopStoriesBean> topStoriesBeanList= (List<TopStoriesBean>) intent.getSerializableExtra(KEY_LIST);
            for (int i = 0; i < topStoriesBeanList.size(); i++) {
                mStoryDetailFragmentList.add(StoryDetailFragment.newTopStoriesBeanInstance(topStoriesBeanList.get(i)));
                if(topStoriesBeanList.get(i).getId()==id){
                    count=i;
                }
            }
        }

        mViewPager=findViewById(R.id.view_pager);
        mFragmentManager=getSupportFragmentManager();
        mDetailAdapter=new StoryDetailAdapter(mFragmentManager, mStoryDetailFragmentList);
        mViewPager.setAdapter(mDetailAdapter);

        mViewPager.setCurrentItem(count);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.story_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.share:
                AppUtil.share(this, mStoriesBeanList.get(mViewPager.getCurrentItem()).getTitle());
                break;
            case R.id.collect:
                break;
            case R.id.comment:
                startActivity(CommentsActivity.newIntent(this,""+mStoriesBeanList .get(mViewPager.getCurrentItem()).getId()));
                break;
            case R.id.thumb_up:
                break;
        }
        return true;
    }

}
