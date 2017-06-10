package image.manual.android.freemusic.networks.service;

import image.manual.android.freemusic.networks.music_type.top_songs.top_songs.MainObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by EDGY on 5/30/2017.
 */

public interface TopSongService {
    @GET("https://itunes.apple.com/us/rss/topsongs/limit=50/genre={idmusictype}/explicit=true/json")
    Call<MainObject> getTopSongs(@Path("idmusictype") String idMusicType);
}
