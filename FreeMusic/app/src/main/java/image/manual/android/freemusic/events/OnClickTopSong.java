package image.manual.android.freemusic.events;

import image.manual.android.freemusic.databases.models.TopSongModel;

/**
 * Created by EDGY on 6/10/2017.
 */

public class OnClickTopSong {
    private TopSongModel topSongModel;

    public OnClickTopSong(TopSongModel topSongModel) {
        this.topSongModel = topSongModel;
    }

    public TopSongModel getTopSongModel() {
        return topSongModel;
    }
}
