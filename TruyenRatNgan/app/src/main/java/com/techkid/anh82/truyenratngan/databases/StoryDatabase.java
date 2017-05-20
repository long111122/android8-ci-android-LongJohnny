package com.techkid.anh82.truyenratngan.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.techkid.anh82.truyenratngan.databases.models.Chapter;
import com.techkid.anh82.truyenratngan.databases.models.Story;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anh82 on 4/18/2017.
 */

public class StoryDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "story.db";
    private static final int DATABASE_VERSION = 1;

    private static final String STORY_ID = "id";
    private static final String STORY_IMAGE = "image";
    private static final String STORY_TITLE = "title";
    private static final String STORY_DESCRIPTION = "description";
    private static final String STORY_LAST_CHAPTER = "last_chapter_no";
    private static final String STORY_IS_FAVORITE= "is_favorite";

    private  static final String CHAPTER_ID = "id";
    private  static final String CHAPTER_TITLE = "title";
    private  static final String CHAPTER_CONTENT = "content";

    private static final String[] CHAPTER_ALL_COLUMNS = new String[]{
            CHAPTER_ID,
            CHAPTER_TITLE,
            CHAPTER_CONTENT
    };

    private static final String[] STORY_ALL_COLUMNS = new String[]{
            STORY_ID,
            STORY_IMAGE,
            STORY_TITLE,
            STORY_DESCRIPTION,
            STORY_LAST_CHAPTER,
            STORY_IS_FAVORITE
    };

    public StoryDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<Story> loadAllStories(){
        // get readable database , lay quyen dc doc
        List<Story> stories = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        //query ==> cursor
        Cursor cursor = db.query("tbl_story",STORY_ALL_COLUMNS, null, null, null,null, null);

        // Go through rows

        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(STORY_ID));
            String image = cursor.getString(cursor.getColumnIndex(STORY_IMAGE));
            String title = cursor.getString(cursor.getColumnIndex(STORY_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(STORY_DESCRIPTION));
            int currentChapter = cursor.getInt(cursor.getColumnIndex(STORY_LAST_CHAPTER));
            boolean is_favorite = cursor.getInt(cursor.getColumnIndex(STORY_IS_FAVORITE)) != 0;
            stories.add(new Story(id, image,title,description, currentChapter,is_favorite));
//            Log.d("image", image);
//            Log.d("title", title);
//            Log.d("description", description);
//            Log.d("is_favorite", is_favorite + "");
        }
        cursor.close();
        db.close();
        return stories;
    }

    public Story storyById(int storyId){
        SQLiteDatabase db = getReadableDatabase();
        //query ==> cursor
        Cursor cursor = db.query("tbl_story",STORY_ALL_COLUMNS, null, null, null,null, null);
        // Go through rows
        Story story = null;
        cursor.moveToFirst();
        int id = cursor.getInt(cursor.getColumnIndex(STORY_ID));
        String image = cursor.getString(cursor.getColumnIndex(STORY_IMAGE));
        String title = cursor.getString(cursor.getColumnIndex(STORY_TITLE));
        String description = cursor.getString(cursor.getColumnIndex(STORY_DESCRIPTION));
        int currentChapter = cursor.getInt(cursor.getColumnIndex(STORY_LAST_CHAPTER));
        boolean is_favorite = cursor.getInt(cursor.getColumnIndex(STORY_IS_FAVORITE)) != 0;
        story = new Story(id, image, title, description, currentChapter, is_favorite);
        cursor.close();
        db.close();
        return story;
    }

    public int getCountChapter(Story story){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(id) FROM tbl_chapter WHERE novel_id = ?",new String[] {
                ((Integer)(story.getId())).toString()
        });
        cursor.moveToFirst();
        int chapterCount = cursor.getInt(0);
        cursor.close();
        return chapterCount;
    }

    public List<Integer> getChapterIds(Story story){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM tbl_chapter WHERE novel_id = ?", new String[] {
                ((Integer)story.getId()).toString()
        });
        List<Integer> chapterIds = new ArrayList<>();

        while(cursor.moveToNext()){
            chapterIds.add(cursor.getInt(0));
        }

        cursor.close();
        return chapterIds;
    }

    public Chapter getChapter(int chapterId){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("tbl_chapter",
                CHAPTER_ALL_COLUMNS,
                "id = ?",
                new String[]{((Integer)chapterId).toString()},
                null, null, null);
        cursor.moveToFirst();
        String title = cursor.getString(cursor.getColumnIndex(CHAPTER_TITLE));
        String content = cursor.getString(cursor.getColumnIndex(CHAPTER_CONTENT));
        cursor.close();

        return new Chapter(chapterId, title, content);
    }

    public void setCurrentChapter(Story story, int chapterId){
        ContentValues contentValues = new ContentValues();
        contentValues.put(STORY_LAST_CHAPTER,chapterId);
        SQLiteDatabase db = getWritableDatabase();
        db.update("tbl_story",
                contentValues,
                "id = ?",
                new String[]{((Integer)story.getId()).toString()});
        db.close();
    }

    public int getLastChapterNo(Story story){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT last_chapter_no FROM tbl_story WHERE id = ?", new String[]{
                ((Integer)story.getId()).toString()
        });
        cursor.moveToFirst();
        int lastChapter = cursor.getInt(0);
        cursor.close();
        return lastChapter;
    }

    public void updateFavourite(Story story, int isFavour) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STORY_IS_FAVORITE, isFavour);
        db.update("tbl_story",
                contentValues,
                "id = ?",
                new String[]{((Integer)story.getId()).toString()});
        db.close();
    }
}
