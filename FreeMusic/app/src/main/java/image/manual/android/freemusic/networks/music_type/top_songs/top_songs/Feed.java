package image.manual.android.freemusic.networks.music_type.top_songs.top_songs;

import java.util.List;

/**
 * Created by EDGY on 5/30/2017.
 */

public class Feed {
    private List<EntryObject> entry;

    public Feed(List<EntryObject> entryObjectList) {
        this.entry = entryObjectList;
    }

    public List<EntryObject> getEntryObjectList() {
        return entry;
    }
}
