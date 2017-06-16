package image.manual.android.freemusic;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EDGY on 5/23/2017.
 */

public class MediaType {
    @SerializedName("34")
    private Subgenres subgenres;

    public MediaType(Subgenres subgenres) {
        this.subgenres = subgenres;
    }

    public Subgenres getSubgenres() {
        return subgenres;
    }
}
