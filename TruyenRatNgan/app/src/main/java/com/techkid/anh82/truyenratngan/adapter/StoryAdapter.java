package com.techkid.anh82.truyenratngan.adapter;

import android.app.Application;
import android.content.Context;
import android.nfc.Tag;
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

import java.util.ArrayList;
import java.util.List;

import static com.techkid.anh82.truyenratngan.R.id.iv_fav;


/**
 * Created by EDGY on 5/7/2017.
 */

public class StoryAdapter extends BaseAdapter {

    private static final String TAG = "StoryAdapter";
    private List<Story> storyList;
    private Context context;
    private Story story;
    private StoryDetailHolder storyDetailHolder;
    private ArrayList<Integer> favs;

    public StoryAdapter(Context context, List<Story> storyList) {
        this.storyList = storyList;
        this.context = context;
        favs = new ArrayList<>();
        for (int i = 0; i < storyList.size(); i++){
            if(storyList.get(i).isFavorite()){
                favs.add(i, R.drawable.ic_favorite_black_24dp);
            } else {
                favs.add(i, R.drawable.ic_favorite_border_black_24dp);
            }
        }
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

    public class StoryDetailHolder {
        private TextView tvTitle;
        private TextView tvDescription;
        private ImageView ivImage;
        private ImageView ivFav;

        public StoryDetailHolder() {
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //1. Load item data
        View row = convertView;
        //2. Create view if nesscessary
        if(row == null){
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            row = layoutInflater.inflate(R.layout.item_story,parent,false);
            storyDetailHolder = new StoryDetailHolder();
            storyDetailHolder.tvTitle = (TextView) row.findViewById(R.id.iv_title);
            storyDetailHolder.tvDescription = (TextView) row.findViewById(R.id.iv_description);
            storyDetailHolder.ivImage = (ImageView) row.findViewById(R.id.iv_story_image);
            storyDetailHolder.ivFav = (ImageView) row.findViewById(R.id.iv_fav);
            row.setTag(storyDetailHolder);
        }

        //3. Config and return
        storyDetailHolder = (StoryDetailHolder) row.getTag();
        Story story = storyList.get(position);
        storyDetailHolder.tvTitle.setText(story.getTitle());
        storyDetailHolder.tvDescription.setText(story.getDescription());
        storyDetailHolder.ivFav.setImageResource(favs.get(position));
        //TODO : load ImageView /AsyncTask + URL + InputStream + BitmapFactory
        //TODO : PIcasso / Glide
        (new ImageLoader())
                .setImageView(storyDetailHolder.ivImage)
                .loadImage(story.getImage());

        storyDetailHolder.ivFav.setTag(position);
        storyDetailHolder.ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Story story1 = storyList.get(position);
                Log.d(TAG,"FK : "+story1.isFavorite());
                if(!story1.isFavorite()){
                    story1.setFavorite(true);
                    StoryApplication.getInstance().getStoryDatabase().updateFavourite(story1, 1);
                    favs.set(position, R.drawable.ic_favorite_black_24dp);
                } else {
                    story1.setFavorite(false);
                    StoryApplication.getInstance().getStoryDatabase().updateFavourite(story1, 0);
                    favs.set(position, R.drawable.ic_favorite_border_black_24dp);
                }
                notifyDataSetChanged();
                Log.d(TAG, "FK : "+story1.getTitle());
            }
        });
        notifyDataSetChanged();
        //        int chapterCount = StoryApplication.getInstance().getStoryDatabase().getCountChapter(story);
//        Log.d(TAG, "count : "+chapterCount);
        return row;
    }
}
