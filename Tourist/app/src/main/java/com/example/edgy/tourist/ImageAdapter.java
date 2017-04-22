package com.example.edgy.tourist;

import android.content.ContentValues;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edgy.tourist.dal.model.Tourist;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by EDGY on 4/22/2017.
 */

public class ImageAdapter extends BaseAdapter {

    private static final String TAG = "ImageAdapter";
    private Context context;
    private ImageView imageView;
    private TextView textView;
    private LayoutInflater layoutInflater;
    private List<Tourist> touristList;

    public ImageAdapter(Context context, List<Tourist> touristList) {
        this.context = context;
        this.touristList = touristList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return touristList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.layout_tourist_default, null, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.tourist_image);
        TextView tvTitle = (TextView) view.findViewById(R.id.tourist_title);
        TextView tvDescription = (TextView) view.findViewById(R.id.tourist_description);
        TextView tvPrice = (TextView) view.findViewById(R.id.tourist_price);
        TextView tvRate = (TextView) view.findViewById(R.id.tourist_rate);
        tvTitle.setText(touristList.get(position).getTitle());
        tvDescription.setText(touristList.get(position).getDescription());
        tvPrice.setText("PRICE : "+touristList.get(position).getPrice());
        tvRate.setText(touristList.get(position).getRate() + "");
        Log.d(TAG,touristList.get(position).getImage());
        Picasso.with(context).load(touristList.get(position).getImage()).into(imageView);
        return view;
    }
}
