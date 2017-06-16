package image.manual.android.freemusic.events;

import image.manual.android.freemusic.databases.models.TopSongModel;

/**
 * Created by EDGY on 6/11/2017.
 */

public class OnClickMiniPlayer {
    private TopSongModel topSongModel;

    public OnClickMiniPlayer(TopSongModel topSongModel) {
        this.topSongModel = topSongModel;
    }

    public TopSongModel getTopSongModel() {
        return topSongModel;
    }
}
