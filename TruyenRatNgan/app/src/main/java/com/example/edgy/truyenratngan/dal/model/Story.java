package com.example.edgy.truyenratngan.dal.model;

/**
 * Created by EDGY on 4/18/2017.
 */

public class Story {
    private String image;
    private String title;
    private String description;
    private boolean isFavorite;

    public Story(String image, String title, String description, boolean isFavorite) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.isFavorite = isFavorite;
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
        return "image : " + image + "\ntitle : " + title + "\ndescription : "+description + "\nis_favorite : "+isFavorite;
    }
}
