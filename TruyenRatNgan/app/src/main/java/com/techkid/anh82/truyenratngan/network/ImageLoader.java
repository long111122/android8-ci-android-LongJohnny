package com.techkid.anh82.truyenratngan.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.techkid.anh82.truyenratngan.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by EDGY on 5/15/2017.
 */

public class ImageLoader extends AsyncTask<String, Void, Bitmap>{
    private String urlString;
    private ImageView imageView;
    private String imageTag;

    public ImageLoader() {
    }

    public ImageLoader setImageView(ImageView imageView) {
        this.imageView = imageView;
        this.imageTag = (imageView.getTag() == null) ? "" : imageView.getTag().toString();
        return this;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    public void loadImage(String urlString){
        if(!urlString.equals(imageTag)){
            imageView.setImageResource(R.drawable.animation_rotation_loading);
            execute(urlString);
        }
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        urlString = params[0];
        if(urlString.equals(imageTag)){
            return null;
        }
        //1. Open connection
        try {
            URL url = new URL(urlString);

            //2. Get stream
            InputStream inputStream = url.openStream();

            //3. Get bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            imageView.setTag(urlString);
        }
    }
}
