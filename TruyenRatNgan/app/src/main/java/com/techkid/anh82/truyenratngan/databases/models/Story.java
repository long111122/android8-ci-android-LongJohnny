package com.techkid.anh82.truyenratngan.databases.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anh82 on 4/18/2017.
 */

public class Story implements Serializable {
    private int id;
    private String image;
    private String title;
    private String description;
    private int currentChapter;
    private boolean isFavorite;

    public Story(int id, String image, String title, String description, int currentChapter, boolean isFavorite) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.currentChapter = currentChapter;
        this.isFavorite = isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public int getCurrentChapter() {
        return currentChapter;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    @Override
    public String toString() {
        return "Story{" +
                "image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isFavorite=" + isFavorite +
                '}';
    }
}
