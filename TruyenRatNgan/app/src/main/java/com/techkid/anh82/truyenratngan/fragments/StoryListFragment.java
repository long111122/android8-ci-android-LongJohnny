package com.techkid.anh82.truyenratngan.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.techkid.anh82.truyenratngan.R;
import com.techkid.anh82.truyenratngan.StoryApplication;
import com.techkid.anh82.truyenratngan.StoryListActivity;
import com.techkid.anh82.truyenratngan.adapter.StoryAdapter;
import com.techkid.anh82.truyenratngan.databases.StoryDatabase;
import com.techkid.anh82.truyenratngan.databases.models.Story;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryListFragment extends Fragment {

    private ArrayAdapter<Story> storyArrayAdapter;
    private List<Story> stories;
    private ListView lvStory;

    public StoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_story_list, container, false);
        lvStory = (ListView) view.findViewById(R.id.story_list);
        lvStory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(), StoryDetailActivity.class);
//                intent.putExtra("story", stories.get(position));
//                startActivity(intent);
                //TODO : Change screen
                Story story = stories.get(position);
                ((StoryListActivity)getActivity()).
                        changeScreen(new StoryDetailFragment()
                                .setStory(story), true);
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
        setupUI();
    }

    private void setupUI() {
        //1.Create adapter
        storyArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, stories);
        StoryAdapter storyAdapter = new StoryAdapter(getActivity(), stories);
        //2.Connect adapter to
        lvStory.setAdapter(storyAdapter);
    }

    public void loadData(){
        StoryDatabase storyDatabase = StoryApplication.getInstance().getStoryDatabase();
        stories = storyDatabase.loadAllStories();
    }
}
