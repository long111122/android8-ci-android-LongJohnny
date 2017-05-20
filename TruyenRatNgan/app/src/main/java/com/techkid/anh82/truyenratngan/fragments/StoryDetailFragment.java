package com.techkid.anh82.truyenratngan.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techkid.anh82.truyenratngan.R;
import com.techkid.anh82.truyenratngan.StoryApplication;
import com.techkid.anh82.truyenratngan.adapter.ChapterAdapter;
import com.techkid.anh82.truyenratngan.databases.models.Story;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryDetailFragment extends Fragment {

    private static final String TAG = "StoryDetailActivity";
    private ViewPager vpChapter;
    private Story story;

    public StoryDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_story_detail, container, false);
        vpChapter = (ViewPager) view.findViewById(R.id.vp_chapter);
        setupUI();
        return view;
    }

    public StoryDetailFragment setStory(Story story) {
        this.story = story;
        return this;
    }

    private void setupUI() {
        vpChapter.setAdapter(
                new ChapterAdapter(getFragmentManager())
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
        vpChapter.setCurrentItem(story.getCurrentChapter());
//        storyUpdate = StoryApplication.getInstance().getStoryDatabase().storyById(story.getId());
//        Log.d(TAG, "fk u : "+storyUpdate.toString());
//        if(storyUpdate.getCurrentChapter() != -1){
//            vpChapter.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    vpChapter.setCurrentItem(storyUpdate.getCurrentChapter());
//                }
//            }, 10);
//        }
    }
}
