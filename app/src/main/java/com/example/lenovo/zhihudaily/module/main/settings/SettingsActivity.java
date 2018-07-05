package com.example.lenovo.zhihudaily.module.main.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.example.lenovo.zhihudaily.R;

public class SettingsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private CheckBox automaticOfflineDownloadCheckbox;
    private CheckBox graphFreePatternCheckbox;
    private CheckBox bigWordCheckbox;
    private CheckBox pushMessageCheckbox;
    private CheckBox commentOnWeiboCheckbox;
    private CheckBox clearCacheCheckbox;
    private CheckBox checkForUpdateCheckbox;
    private CheckBox feedbackCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mToolbar=findViewById(R.id.tool_bar);
        mToolbar.setTitle(R.string.settings);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_back);

        automaticOfflineDownloadCheckbox=findViewById(R.id.checkbox_automatic_offline_download);
        graphFreePatternCheckbox=findViewById(R.id.checkbox_graph_free_pattern);
        bigWordCheckbox=findViewById(R.id.checkbox_big_word);
        pushMessageCheckbox=findViewById(R.id.checkbox_push_message);
        commentOnWeiboCheckbox=findViewById(R.id.checkbox_comment_on_weibo);
        clearCacheCheckbox=findViewById(R.id.checkbox_clear_cache);
        checkForUpdateCheckbox=findViewById(R.id.checkbox_check_for_update);
        feedbackCheckbox=findViewById(R.id.checkbox_feedback);

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.automatic_offline_download:
                automaticOfflineDownloadCheckbox.setChecked(!automaticOfflineDownloadCheckbox.isChecked());
                break;
            case R.id.graph_free_pattern:
                graphFreePatternCheckbox.setChecked(!graphFreePatternCheckbox.isChecked());
                break;
            case R.id.big_word:
                bigWordCheckbox.setChecked(!bigWordCheckbox.isChecked());
                break;
            case R.id.push_message:
                pushMessageCheckbox.setChecked(!pushMessageCheckbox.isChecked());
                break;
            case R.id.comment_on_weibo:
                commentOnWeiboCheckbox.setChecked(!commentOnWeiboCheckbox.isChecked());
                break;
            case R.id.clear_cache:
                clearCacheCheckbox.setChecked(!clearCacheCheckbox.isChecked());
                break;
            case R.id.check_for_update:
                checkForUpdateCheckbox.setChecked(!checkForUpdateCheckbox.isChecked());
                break;
            case R.id.feedback:
                feedbackCheckbox.setChecked(!feedbackCheckbox.isChecked());
                break;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }

        return true;
    }
}
