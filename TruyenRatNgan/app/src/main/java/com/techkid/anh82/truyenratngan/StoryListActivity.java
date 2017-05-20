package com.techkid.anh82.truyenratngan;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.techkid.anh82.truyenratngan.adapter.StoryAdapter;
import com.techkid.anh82.truyenratngan.databases.StoryDatabase;
import com.techkid.anh82.truyenratngan.databases.models.Story;
import com.techkid.anh82.truyenratngan.fragments.StoryListFragment;

import java.util.List;

public class StoryListActivity extends AppCompatActivity {
    private static final String TAG = "StoryListActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayStartScreen();
    }

    private void displayStartScreen() {
        //1: Create fragment
        StoryListFragment storyListFragment = new StoryListFragment();

        changeScreen(storyListFragment, false);
    }

    public void changeScreen(Fragment fragment, boolean addToBackstack){
        //2: Create a transaction
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_main, fragment);
        if(addToBackstack) {
            fragmentTransaction.addToBackStack(null);
        }
        //3: Commit
        fragmentTransaction.commit();
    }
}
