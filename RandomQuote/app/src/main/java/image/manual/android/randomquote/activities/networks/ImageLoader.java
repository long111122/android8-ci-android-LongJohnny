package image.manual.android.randomquote.activities.networks;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import image.manual.android.randomquote.R;
import image.manual.android.randomquote.activities.activities.MainActivity;

/**
 * Created by EDGY on 5/17/2017.
 */

public class ImageLoader extends AsyncTask<String, Void, Bitmap> {

    private static final String TAG = "ImageLoader";
    private ImageView ivBackground;
    private final String IMAGE_URL = "https://source.unsplash.com/random/";
    private int IMAGE_WIDTH;
    private int IMAGE_HEIGHT;
    private String urlFix;

    public ImageLoader(Context context) {
        urlFix = "";
        if (Build.VERSION.SDK_INT >= 11) {
            Point size = new Point();
            try {
                ((Activity)context).getWindowManager().getDefaultDisplay().getRealSize(size);
                IMAGE_WIDTH = size.x;
                IMAGE_HEIGHT = size.y;
            } catch (NoSuchMethodError e) {
            }
        } else {
            DisplayMetrics metrics = new DisplayMetrics();
            ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
            IMAGE_WIDTH = metrics.widthPixels;
            IMAGE_HEIGHT = metrics.heightPixels;
        }

//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        IMAGE_WIDTH = displayMetrics.widthPixels;
//        IMAGE_HEIGHT = displayMetrics.heightPixels;
    }

    public ImageLoader setIvBackground(ImageView ivBackground) {
        this.ivBackground = ivBackground;
        return this;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    public void loadImage(){
        if(urlFix.equals("")){
            ivBackground.setScaleType(ImageView.ScaleType.FIT_CENTER);
            ivBackground.setImageResource(R.drawable.animation_rotation_loading);
            execute();
        }
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            urlFix = IMAGE_URL + IMAGE_WIDTH + "x" + IMAGE_HEIGHT;
            Log.d(TAG,"url image : "+urlFix);
            URL url = new URL(urlFix);
            InputStream inputStream = url.openStream();
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
        ivBackground.setScaleType(ImageView.ScaleType.FIT_XY);
        ivBackground.setImageBitmap(bitmap);
    }
}
