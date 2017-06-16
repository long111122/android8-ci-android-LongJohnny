package image.manual.android.freemusic.managers;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hybridmediaplayer.HybridMediaPlayer;
import image.manual.android.freemusic.R;
import image.manual.android.freemusic.databases.models.TopSongModel;
import image.manual.android.freemusic.events.LoadUIPlayer;
import image.manual.android.freemusic.networks.RetrofitFactory;
import image.manual.android.freemusic.networks.music_type.top_songs.search_songs.DocObject;
import image.manual.android.freemusic.networks.music_type.top_songs.search_songs.MainObject;
import image.manual.android.freemusic.networks.service.SearchSongService;
import image.manual.android.freemusic.services.MusicNotification;
import image.manual.android.freemusic.utils.FuzzyMath;
import image.manual.android.freemusic.utils.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by EDGY on 5/30/2017.
 */

public class MusicManager {
    private static final String TAG = "MusicManager";
    public static HybridMediaPlayer hybridMediaPlayer;
    private MainObject mainObject;
    private static boolean isPrepared;
    private static boolean keepUpdate;

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
                        if(response.code() == 200) {
                            if (response.body().getDocs().size() != 0) {
                                //get list
                                List<Integer> ratioList = new ArrayList<>();
                                for (DocObject docObject : response.body().getDocs()) {
                                    int ratio = FuzzyMath.getRatio(dataSong, docObject.getTitle()
                                            + " " + docObject.getArtist(), false);
                                    ratioList.add(ratio);
                                }
                                //get max
                                for (int i = 0; i < ratioList.size(); i++) {
                                    if (ratioList.get(i) == Collections.max(ratioList)) {
                                        topSongModel.setLinkSource(response.body().getDocs()
                                                .get(i).getSource().getLinkSource());
                                        playSong(context, topSongModel);
                                    }
                                    Log.d(TAG, "onRatio : " + response.body().getDocs()
                                            .get(i).getSource().getLinkSource());
                                }
                            } else {
                                Toast.makeText(context, "Not found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MainObject> call, Throwable t) {

                    }
                });
    }

    public static void playSong(final Context context, final TopSongModel topSongModel){
        isPrepared = false;
        if(hybridMediaPlayer != null){
            hybridMediaPlayer.release();
        }
        hybridMediaPlayer = HybridMediaPlayer.getInstance(context);
        hybridMediaPlayer.setDataSource(topSongModel.getLinkSource());
        hybridMediaPlayer.prepare();
        MusicNotification.setupNotification(context, topSongModel);
        hybridMediaPlayer.setOnPreparedListener(new HybridMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(HybridMediaPlayer hybridMediaPlayer) {
                isPrepared = true;
                hybridMediaPlayer.play();

                EventBus.getDefault().post(new LoadUIPlayer(topSongModel));
            }
        });
    }

    public static void playOrPause(){
        if(isPrepared){
            hybridMediaPlayer.pause();
            isPrepared = false;
        } else {
            hybridMediaPlayer.play();
            isPrepared = true;
        }
        MusicNotification.updateNotification(hybridMediaPlayer.isPlaying());
    }

    public static void updateSong(final FloatingActionButton floatingActionButton, final SeekBar seekBar1,
                                  final SeekBar seekBar2, final TextView tvCurrentTime, final TextView tvDuration){
        final android.os.Handler handler = new android.os.Handler();
        Runnable autoUpdate = new Runnable() {
            @Override
            public void run() {
                if(keepUpdate){
                    if(hybridMediaPlayer.isPlaying()){
                        floatingActionButton.setImageResource(R.drawable.ic_pause_black_24dp);
                    } else {
                        floatingActionButton.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    }
                    //time of song
                    seekBar1.setMax(hybridMediaPlayer.getDuration());
                    seekBar1.setProgress(hybridMediaPlayer.getCurrentPosition());
                    if(seekBar2 != null){
                        seekBar2.setMax(hybridMediaPlayer.getDuration());
                        seekBar2.setProgress(hybridMediaPlayer.getCurrentPosition());
                    }
                    if(tvCurrentTime != null){
                        tvCurrentTime.setText(Util.convertTime(hybridMediaPlayer.getCurrentPosition()));
                        tvDuration.setText(Util.convertTime(hybridMediaPlayer.getDuration()));
                    }
                }
                handler.postDelayed(this, 100);
            }
        };
        autoUpdate.run();
        final int[] currentProgresss = new int[1];
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentProgresss[0] = progress;
                if(seekBar2 != null){
                    seekBar2.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //khi kéo seekbar
                keepUpdate = false;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //sau khi thả seekbar
                keepUpdate = true;
                hybridMediaPlayer.seekTo(currentProgresss[0]);
            }
        });

        if(seekBar2 != null){
            seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    currentProgresss[0] = progress;
                    seekBar1.setProgress(progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    keepUpdate = false;
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    keepUpdate = true;
                    hybridMediaPlayer.seekTo(currentProgresss[0]);
                }
            });
        }
    }
}
