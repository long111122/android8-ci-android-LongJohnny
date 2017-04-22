package com.example.edgy.tourist.dal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.edgy.tourist.dal.model.Tourist;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EDGY on 4/22/2017.
 */

public class TouristDatabase extends SQLiteAssetHelper{
    private static final String DATABASE_NAME = "tourist.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TOURIST_IMAGE = "image";
    private static final String TOURIST_TITLE = "title";
    private static final String TOURIST_DESCRIPTION = "description";
    private static final String TOURIST_PRICE = "price";
    private static final String TOURIST_RATE = "rate";
    private static final String TYPE_CURRENCY = "$";

    private static final String TOURIST_TABLE = "tourist_tbl";
    private static final String[] TOURIST_ALL_COLUMNS = new String[]{
            TOURIST_IMAGE,
            TOURIST_TITLE,
            TOURIST_DESCRIPTION,
            TOURIST_PRICE,
            TOURIST_RATE
    };
    private static final String TAG = "TouristDatabase";


    public TouristDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<Tourist> loadAllTourists(){
        List<Tourist> touristList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TOURIST_TABLE, TOURIST_ALL_COLUMNS, null, null, null, null, null);
        Log.d(TAG,"falseLoading");
        while(cursor.moveToNext()){
            String image = cursor.getString(cursor.getColumnIndex(TOURIST_IMAGE));
            String title = cursor.getString(cursor.getColumnIndex(TOURIST_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(TOURIST_DESCRIPTION));
            String price = cursor.getInt(cursor.getColumnIndex(TOURIST_PRICE)) + TYPE_CURRENCY;
            int rate = cursor.getInt(cursor.getColumnIndex(TOURIST_RATE));
            touristList.add(new Tourist(image, title, description, price, rate));
            Log.d(TAG,price);
        }
        cursor.close();
        sqLiteDatabase.close();
        return touristList;
    }
}
