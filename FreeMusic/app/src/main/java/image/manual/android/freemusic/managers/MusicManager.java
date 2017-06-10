package image.manual.android.freemusic.managers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hybridmediaplayer.HybridMediaPlayer;
import image.manual.android.freemusic.databases.models.TopSongModel;
import image.manual.android.freemusic.networks.RetrofitFactory;
import image.manual.android.freemusic.networks.music_type.top_songs.search_songs.DocObject;
import image.manual.android.freemusic.networks.music_type.top_songs.search_songs.MainObject;
import image.manual.android.freemusic.networks.service.SearchSongService;
import image.manual.android.freemusic.utils.FuzzyMath;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by EDGY on 5/30/2017.
 */

public class MusicManager {
    private static final String TAG = "MusicManager";
    private static HybridMediaPlayer hybridMediaPlayer;
    private MainObject mainObject;

    public static void loadSearchSong(final Context context, final TopSongModel topSongModel){
        SearchSongService searchSongService = RetrofitFactory
                .getInstance()
                .createService(SearchSongService.class);
        final String dataSong = topSongModel.getSongName() + " " + topSongModel.getSingerName();
        String requestData = "{\"q\":\"" + dataSong + "\", \"sort\":\"hot\", \"start\":\"0\", \"length\":\"10\"}";
        searchSongService
                .getSearchSongs(requestData)
                .enqueue(new Callback<MainObject>() {
                    @Override
                    public void onResponse(Call<MainObject> call, Response<MainObject> response) {
                        if(response.body().getDocs().size() != 0){
                            //get list
                            List<Integer> ratioList = new ArrayList<>();
                            for (DocObject docObject : response.body().getDocs()){
                                int ratio = FuzzyMath.getRatio(dataSong, docObject.getTitle()
                                        + " " + docObject.getArtist(), false);
                                ratioList.add(ratio);
                            }
                            //get max
                            for (int i = 0; i < ratioList.size(); i++){
                                if(ratioList.get(i) == Collections.max(ratioList)){
                                    topSongModel.setLinkSource(response.body().getDocs()
                                            .get(i).getSource().getLinkSource());
                                    playSong(context, topSongModel);
                                }
                                Log.d(TAG, "onRatio : " + response.body().getDocs()
                                        .get(i).getSource().getLinkSource());
                            }
                        }else {
                            Toast.makeText(context, "Not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MainObject> call, Throwable t) {

                    }
                });
    }

    public static void playSong(Context context, TopSongModel topSongModel){
        if(hybridMediaPlayer != null){
            hybridMediaPlayer.release();
        }
        hybridMediaPlayer = HybridMediaPlayer.getInstance(context);
        hybridMediaPlayer.setDataSource(topSongModel.getLinkSource());
        hybridMediaPlayer.prepare();

        hybridMediaPlayer.setOnPreparedListener(new HybridMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(HybridMediaPlayer hybridMediaPlayer) {
                hybridMediaPlayer.play();
            }
        });
    }
}
