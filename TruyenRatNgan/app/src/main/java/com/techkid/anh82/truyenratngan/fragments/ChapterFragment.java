package com.techkid.anh82.truyenratngan.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.techkid.anh82.truyenratngan.R;
import com.techkid.anh82.truyenratngan.StoryApplication;
import com.techkid.anh82.truyenratngan.databases.StoryDatabase;
import com.techkid.anh82.truyenratngan.databases.models.Chapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChapterFragment extends Fragment {

    private TextView tvTitle;
    private WebView webView;
    private int chapterId;
    private Chapter chapter;

    public ChapterFragment() {
        // Required empty public constructor
    }

    public ChapterFragment setChapterId(int chapterId) {
        this.chapterId = chapterId;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chapter, container, false);
        getChapter();
        findReferences(view);
        setupUI();
        return view;
    }

    private void getChapter() {
        StoryDatabase storyDatabase = StoryApplication.getInstance().getStoryDatabase();
        chapter = storyDatabase.getChapter(chapterId);
    }

    private void setupUI() {
        tvTitle.setText(chapter.getTitle());
        webView.loadData(chapter.getContent(), "text/html; charset=utf-8", "UTF-8");
    }

    private void findReferences(View view) {
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        webView = (WebView) view.findViewById(R.id.wb_content);
    }
}
