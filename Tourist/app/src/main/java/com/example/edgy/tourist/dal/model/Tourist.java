package com.example.edgy.tourist.dal.model;

/**
 * Created by EDGY on 4/22/2017.
 */

public class Tourist {
    private String image;
    private String title;
    private String description;
    private String price;
    private int rate;

    public Tourist(String image, String title, String description, String price, int rate) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.price = price;
        this.rate = rate;
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

    public String getPrice() {
        return price;
    }

    public int getRate() {
        return rate;
    }
}
