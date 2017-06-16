package image.manual.android.freemusic;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by EDGY on 6/16/2017.
 */

public class Subgenres {
    @SerializedName("subgenres")
    private ListMusicType listMusicType;

    public Subgenres(ListMusicType listMusicType) {
        this.listMusicType = listMusicType;
    }

    public ListMusicType getListMusicType() {
        return listMusicType;
    }
}
