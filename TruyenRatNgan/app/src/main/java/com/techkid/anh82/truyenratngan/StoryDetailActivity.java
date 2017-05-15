package com.techkid.anh82.truyenratngan;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.techkid.anh82.truyenratngan.adapter.ChapterAdapter;
import com.techkid.anh82.truyenratngan.databases.models.Story;

public class StoryDetailActivity extends AppCompatActivity {

    private static final String TAG = "StoryDetailActivity";
    private ViewPager vpChapter;
    private Story story;
    private Story storyUpdate;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra("storyId", story.getId());
        if(getIntent().hasExtra("position")) {
            intent.putExtra("position", getIntent().getStringExtra("position"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
        vpChapter = (ViewPager) findViewById(R.id.vp_chapter);
        getStory();
        setupUI();
    }

    private void setupUI() {
        vpChapter.setAdapter(
                new ChapterAdapter(getSupportFragmentManager())
                        .setStory(story));
        vpChapter.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                StoryApplication.getInstance().getStoryDatabase().setCurrentChapter(story, position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        storyUpdate = StoryApplication.getInstance().getStoryDatabase().storyById(story.getId());
        Log.d(TAG, "fk u : "+storyUpdate.toString());
        if(storyUpdate.getCurrentChapter() != -1){
            vpChapter.postDelayed(new Runnable() {
                @Override
                public void run() {
                    vpChapter.setCurrentItem(storyUpdate.getCurrentChapter());
                }
            }, 10);
        }
    }

    private void getStory() {
        Intent intent = getIntent();
        story = (Story) intent.getSerializableExtra("story");
    }
}
