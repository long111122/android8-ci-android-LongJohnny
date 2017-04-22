package com.example.edgy.truyenratngan;

import android.app.Application;
import android.util.Log;

import com.example.edgy.truyenratngan.dal.StoryDatabase;
import com.example.edgy.truyenratngan.dal.model.Story;

/**
 * Created by EDGY on 4/18/2017.
 */

public class StoryApplication extends Application{
    private static final String TAG = "StoryApplication";
    private StoryDatabase storyDatabase;

    //instance
    private static StoryApplication instance;

    public static StoryApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        storyDatabase = new StoryDatabase(this);
        instance = this;
        super.onCreate();
//        Log.d(TAG, "onCreate : ");
    }

    public StoryDatabase getStoryDatabase() {
        return storyDatabase;
    }
}

