package com.example.edgy.truyenratngan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.edgy.truyenratngan.dal.StoryDatabase;
import com.example.edgy.truyenratngan.dal.model.Story;

import java.util.List;

public class StoryActivity extends AppCompatActivity {

    private static final String TAG = "StoryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        StoryDatabase storyDatabase = new StoryDatabase(this);
        StoryDatabase storyDatabase = StoryApplication.getInstance().getStoryDatabase();
        List<Story> storyList = storyDatabase.loadAllStory();
        for (Story story : storyList){
            Log.d(TAG,story.toString());
        }
    }
}
