package com.techkid.anh82.truyenratngan;

import android.content.Intent;
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

import java.util.List;

public class StoryListActivity extends AppCompatActivity {
    private static final String TAG = "StoryListActivity";
    private ArrayAdapter<Story> storyArrayAdapter;
    private List<Story> stories;
    private ListView lvStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvStory = (ListView) findViewById(R.id.story_list);
        loadData();
        setupUI();
//        if(stories != null){
//            for(Story s : stories){
//                TextView t1 = new TextView(this);
//
//                                t1.setText(s.getTitle());
//
//                mainLinear.addView(t1);
//
//                Log.d("Story is" , s.toString());
//            }
//        }
        lvStory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,String.format("onItemClick : %s",position));
                Intent intent = new Intent(StoryListActivity.this, StoryDetailActivity.class);
                intent.putExtra("story", stories.get(position));
                startActivity(intent);
            }
        });


    }

    private void setupUI() {
        //1.Create adapter
        storyArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stories);
        StoryAdapter storyAdapter = new StoryAdapter(this, stories);
        //2.Connect adapter to
        lvStory.setAdapter(storyAdapter);
    }

    public void loadData(){
        StoryDatabase storyDatabase = StoryApplication.getInstance().getStoryDatabase();
        stories = storyDatabase.loadAllStories();
    }
}
