package image.manual.android.freemusic;

import com.google.gson.annotations.SerializedName;

/**
 * Created by EDGY on 5/23/2017.
 */

public class MusicType {
    private String id;
    //unknown variable, example 'class'
    @SerializedName("translation_key")
    private String musicKey;

    public MusicType(String id, String musicKey) {
        this.id = id;
        this.musicKey = musicKey;
    }

    public String getId() {
        return id;
    }

    public String getTranslation_key() {
        return musicKey;
    }
}
