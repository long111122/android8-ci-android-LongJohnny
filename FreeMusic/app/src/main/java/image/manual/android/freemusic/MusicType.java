package image.manual.android.freemusic;

import com.google.gson.annotations.SerializedName;

/**
 * Created by EDGY on 5/23/2017.
 */

public class MusicType {
    private String id;
    private String name;

    public MusicType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
