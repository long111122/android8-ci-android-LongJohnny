package com.example.edgy.truyenratngan.dal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.edgy.truyenratngan.dal.model.Story;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EDGY on 4/18/2017.
 */

public class StoryDatabase extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "story.db";
    private static final int DATABASE_VERSION = 1;

    private static final String STORY_IMAGE = "image";
    private static final String STORY_TITLE = "title";
    private static final String STORY_DESCRIPTION = "description";
    private static final String STORY_IS_FAVORITE = "is_favorite";
    private List<Story> storyList;

    private static final String[] STORY_ALL_COLUMNS = new String[]{
            STORY_IMAGE,
            STORY_TITLE,
            STORY_DESCRIPTION,
            STORY_IS_FAVORITE
    };
    private static final String TAG = "StoryActivity";

    public StoryDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<Story> loadAllStory(){
        storyList = new ArrayList<>();
        // Get readable database
        SQLiteDatabase db = getReadableDatabase();

        // Query => Cursor
        Cursor cursor =  db.query("story_tbl", STORY_ALL_COLUMNS, null, null, null, null, null);

        // Go through rows
        while(cursor.moveToNext()){
            String image = cursor.getString(cursor.getColumnIndex(STORY_IMAGE));
            String title = cursor.getString(cursor.getColumnIndex(STORY_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(STORY_DESCRIPTION));
            boolean isFavorite = cursor.getInt(cursor.getColumnIndex(STORY_IS_FAVORITE)) != 0;
            storyList.add(new Story(image, title, description, isFavorite));
        }
        cursor.close();
        db.close();
        return storyList;
    }
}
