package image.manual.android.freemusic.networks.service;



import image.manual.android.freemusic.networks.music_type.top_songs.search_songs.MainObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by EDGY on 5/30/2017.
 */

public interface SearchSongService {
    @GET("http://api.mp3.zing.vn/api/mobile/search/song")
    Call<MainObject> getSearchSongs(@Query("requestdata") String request);
}
