package com.example.lenovo.zhihudaily.module.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.lenovo.zhihudaily.R;
import com.example.lenovo.zhihudaily.module.Callbacks;
import com.example.lenovo.zhihudaily.module.base.FragmentContainerActivity;
import com.example.lenovo.zhihudaily.module.main.themes.DailyPsychologyFragment;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends FragmentContainerActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String KEY_MAIN="main";
    private static final String KEY_DAILYPSYCHOLOGY ="dailypaychology";

    private Map<String,Fragment> mMap=new HashMap<>();

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    @Override
    public Fragment createFragment() {
        MainFragment fragment = (MainFragment) MainFragment.newInstance();
        fragment.setCallbacks(new Callbacks() {
            @Override
            public void openDrawerLayout() {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.fragment_container;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDrawerLayout=findViewById(R.id.drawer_layout);
        mNavigationView=findViewById(R.id.navigation_view);

        mDrawerLayout=findViewById(R.id.drawer_layout);
        mNavigationView.setNavigationItemSelectedListener(this);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                replaceFragment(getFragmentFromCathe(KEY_MAIN));
                break;
            case R.id.daily_psychology:
                replaceFragment(getFragmentFromCathe(KEY_DAILYPSYCHOLOGY));
                break;

        }
        mDrawerLayout.closeDrawers();
        return true;
    }

    public Fragment getFragmentFromCathe(String key){
        Fragment fragment=mMap.get(key);
        if(null == fragment){
            switch (key){
                case KEY_MAIN:
                    fragment=MainFragment.newInstance();
                    ((MainFragment)fragment).setCallbacks(new Callbacks() {
                        @Override
                        public void openDrawerLayout() {
                            mDrawerLayout.openDrawer(GravityCompat.START);
                        }
                    });
                    break;
                case KEY_DAILYPSYCHOLOGY:
                    fragment= DailyPsychologyFragment.newInstance();
                    ((DailyPsychologyFragment)fragment).setCallbacks(new Callbacks() {
                        @Override
                        public void openDrawerLayout() {
                            mDrawerLayout.openDrawer(GravityCompat.START);
                        }
                    });
                    break;
            }
            mMap.put(key, fragment);
        }

        return fragment;
    }

}
