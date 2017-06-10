package image.manual.android.freemusic.networks.music_type.top_songs.search_songs;

import java.util.List;

/**
 * Created by EDGY on 5/30/2017.
 */

public class MainObject {
    private List<DocObject> docs;

    public MainObject(List<DocObject> docs) {
        this.docs = docs;
    }

    public List<DocObject> getDocs() {
        return docs;
    }
}
