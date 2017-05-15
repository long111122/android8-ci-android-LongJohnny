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

    String content = "<div class=\"maincontent\">\n" +
            "<p style=\"text-align: center;\"></p>\n" +
            "<p style=\"text-align: justify;\">Một ngày nọ, con lừa của một ông chủ trang trại sảy chân rơi xuống một cái giếng.</p>\n" +
            "<p>Lừa kêu la tội nghiệp hàng giờ liền. Người chủ trang trại cố nghĩ xem nên làm gì.</p>\n" +
            "<p>Cuối cùng ông quyết định: con lừa đã già, dù sao thì cái giếng cũng cần được lấp lại và không ích lợi gì trong việc cứu con lừa lên cả.</p>\n" +
            "<p>Ông nhờ vài người hàng xóm sang giúp mình.Họ xúc đất và đổ vào giếng. Ngay từ đầu, lừa đã hiểu chuyện gì đang xảy ra và nó kêu la thảm thiết. Nhưng sau đó lừa trở nên im lặng. Sau một vài xẻng đất, ông chủ trang trại nhìn xuống giếng và vô cùng sửng sốt.</p>\n" +
            "<p>Mỗi khi bị một xẻng đất đổ lên lưng, lừa lắc mình cho đất rơi xuống và bước chân lên trên. Cứ như vậy, đất đổ xuống, lừa lại bước lên cao hơn. Chỉ một lúc sau mọi người nhìn thấy chú lừa xuất hiện trên miệng giếng và lóc cóc chạy ra ngoài.</p>\n" +
            "<p><strong>Lời tựa:</strong></p>\n" +
            "<p><strong>Cuộc sống sẽ đổ rất nhiều thứ khó chịu lên người bạn.</strong></p>\n" +
            "<p><strong>Hãy xem mỗi vấn đề bạn gặp phải là một hòn đá để bạn bước</strong><br/><strong>lên cao hơn.</strong></p>\n" +
            "<p><strong>Chúng ta có thể thoát khỏi cái giếng sâu nhất chỉ đơn giản bằng cách đừng bao giờ đầu hàng</strong>.</p>\n" +
            "<p>-ST-</p> </div>";
}
