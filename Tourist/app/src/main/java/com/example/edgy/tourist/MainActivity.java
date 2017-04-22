package com.example.edgy.tourist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.edgy.tourist.dal.TouristApplication;
import com.example.edgy.tourist.dal.TouristDatabase;
import com.example.edgy.tourist.dal.model.Tourist;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lv_tourist);
        TouristDatabase touristDatabase = TouristApplication.getInstance().getTouristDatabase();
        List<Tourist> touristList = touristDatabase.loadAllTourists();
        for (Tourist tourist : touristList){
            Log.d(TAG,tourist.getPrice());
        }

        listView.setAdapter(new ImageAdapter(this, touristList));

    }


}
