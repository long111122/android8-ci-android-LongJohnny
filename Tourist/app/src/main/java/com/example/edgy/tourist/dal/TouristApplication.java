package com.example.edgy.tourist.dal;

import android.app.Application;

import com.example.edgy.tourist.dal.model.Tourist;

/**
 * Created by EDGY on 4/22/2017.
 */

public class TouristApplication extends Application{
    private static TouristApplication instance;
    private TouristDatabase touristDatabase;

    @Override
    public void onCreate() {
        instance = this;
        touristDatabase = new TouristDatabase(this);
        super.onCreate();
    }

    public static TouristApplication getInstance() {
        return instance;
    }

    public TouristDatabase getTouristDatabase() {
        return touristDatabase;
    }
}
