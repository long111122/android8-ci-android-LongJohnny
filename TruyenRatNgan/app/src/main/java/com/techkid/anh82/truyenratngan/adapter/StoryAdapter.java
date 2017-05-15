package com.techkid.anh82.truyenratngan.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.techkid.anh82.truyenratngan.R;
import com.techkid.anh82.truyenratngan.StoryApplication;
import com.techkid.anh82.truyenratngan.databases.models.Story;
import com.techkid.anh82.truyenratngan.network.ImageLoader;

import java.util.List;


/**
 * Created by EDGY on 5/7/2017.
 */

public class StoryAdapter extends BaseAdapter {

    private static final String TAG = "StoryAdapter";
    private List<Story> storyList;
    private Context context;

    public StoryAdapter(Context context, List<Story> storyList) {
        this.storyList = storyList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return storyList.size();
    }

    @Override
    public Object getItem(int position) {
        return storyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //1. Load item data
        Story story = storyList.get(position);

        //2. Create view if nesscessary
        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.item_story,parent,false);
        }

        //3. Config and return
        TextView tvTitle = (TextView) convertView.findViewById(R.id.iv_title);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.iv_description);
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.iv_story_image);
        tvTitle.setText(story.getTitle());
        tvDescription.setText(story.getDescription());
        //TODO : load ImageView /AsyncTask + URL + InputStream + BitmapFactory
        //TODO : PIcasso / Glide
        (new ImageLoader())
                .setImageView(ivImage)
                .loadImage(story.getImage());

//        int chapterCount = StoryApplication.getInstance().getStoryDatabase().getCountChapter(story);
//        Log.d(TAG, "count : "+chapterCount);

        return convertView;
    }
}
