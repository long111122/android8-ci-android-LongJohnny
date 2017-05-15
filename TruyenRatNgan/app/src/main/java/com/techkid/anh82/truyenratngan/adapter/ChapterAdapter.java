package com.techkid.anh82.truyenratngan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.techkid.anh82.truyenratngan.StoryApplication;
import com.techkid.anh82.truyenratngan.databases.StoryDatabase;
import com.techkid.anh82.truyenratngan.databases.models.Story;
import com.techkid.anh82.truyenratngan.fragments.ChapterFragment;

import java.util.List;

/**
 * Created by EDGY on 5/15/2017.
 */

public class ChapterAdapter extends FragmentPagerAdapter{

    private Story story;
    private List<Integer> chapterIds;
    private StoryDatabase storyDatabase;

    public ChapterAdapter(FragmentManager fm) {
        super(fm);
        storyDatabase = StoryApplication.getInstance().getStoryDatabase();
    }

    public ChapterAdapter setStory(Story story) {
        this.story = story;
        this.chapterIds = storyDatabase.getChapterIds(story);
        return this;
    }

    @Override
    public Fragment getItem(int position) {
        return (new ChapterFragment())
                .setChapterId(chapterIds.get(position));
    }

    @Override
    public int getCount() {
        return storyDatabase.getCountChapter(story);
    }
}
