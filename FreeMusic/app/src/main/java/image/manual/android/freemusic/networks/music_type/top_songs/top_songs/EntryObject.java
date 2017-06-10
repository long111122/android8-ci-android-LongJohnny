package image.manual.android.freemusic.networks.music_type.top_songs.top_songs;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by EDGY on 5/30/2017.
 */

public class EntryObject {
    @SerializedName("im:name")
    private NameObject nameObject;

    @SerializedName("im:image")
    private List<ImageObject> imageObjectList;

    @SerializedName("im:artist")
    private ArtistObject artistObject;

    public EntryObject(NameObject nameObject, List<ImageObject> imageObjectList, ArtistObject artistObject) {
        this.nameObject = nameObject;
        this.imageObjectList = imageObjectList;
        this.artistObject = artistObject;
    }

    public NameObject getNameObject() {
        return nameObject;
    }

    public List<ImageObject> getImageObjectList() {
        return imageObjectList;
    }

    public ArtistObject getArtistObject() {
        return artistObject;
    }
}
