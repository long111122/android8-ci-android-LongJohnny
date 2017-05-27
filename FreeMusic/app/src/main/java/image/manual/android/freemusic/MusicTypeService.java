package image.manual.android.freemusic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by EDGY on 5/23/2017.
 */

public interface MusicTypeService {
    @GET("data/media-types.json")
    Call<List<MediaType>> getMusicTypes();
}
