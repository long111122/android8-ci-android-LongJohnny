package image.manual.android.freemusic.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import image.manual.android.freemusic.MediaType;

import image.manual.android.freemusic.MusicType;
import image.manual.android.freemusic.MusicTypeService;
import image.manual.android.freemusic.R;
import image.manual.android.freemusic.networks.RetrofitFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private List<MusicType> musicTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musicTypes = new ArrayList<>();
        RetrofitFactory
                .getInstance()
                .createService(MusicTypeService.class).getMusicTypes().enqueue(new Callback<List<MediaType>>() {
            @Override
            public void onResponse(Call<List<MediaType>> call, Response<List<MediaType>> response) {
                if(response.code() == 200) {
                    MediaType mediaType = response.body().get(3);
                    musicTypes = mediaType.getSubgenres();
                    for (MusicType musicType : musicTypes){
                        Log.d(TAG,"music id : " + musicType.getId());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<MediaType>> call, Throwable t) {
                Log.d(TAG, "Fail");
            }
        });
    }
}
