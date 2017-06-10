package image.manual.android.freemusic.networks.music_type.top_songs.search_songs;

import com.google.gson.annotations.SerializedName;

/**
 * Created by EDGY on 5/30/2017.
 */

public class Source {
    @SerializedName("128")
    private String linkSource;

    public Source(String linkSource) {
        this.linkSource = linkSource;
    }

    public String getLinkSource() {
        return linkSource;
    }
}
