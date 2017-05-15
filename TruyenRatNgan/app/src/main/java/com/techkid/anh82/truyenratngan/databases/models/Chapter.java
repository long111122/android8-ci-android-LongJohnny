package com.techkid.anh82.truyenratngan.databases.models;

/**
 * Created by EDGY on 5/15/2017.
 */

public class Chapter {
    private int id;
    private String title;
    private String content;

    public Chapter(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
