package image.manual.android.freemusic.events;

import image.manual.android.freemusic.databases.models.MusicTypeModel;

/**
 * Created by EDGY on 5/30/2017.
 */

public class OnCLickMusicTypeModel {
    private MusicTypeModel musicTypeModel;

    public OnCLickMusicTypeModel(MusicTypeModel musicTypeModel) {
        this.musicTypeModel = musicTypeModel;
    }

    public MusicTypeModel getMusicTypeModel() {
        return musicTypeModel;
    }
}
