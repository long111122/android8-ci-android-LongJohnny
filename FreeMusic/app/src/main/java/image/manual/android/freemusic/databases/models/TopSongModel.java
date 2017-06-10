package image.manual.android.freemusic.databases.models;

/**
 * Created by EDGY on 5/30/2017.
 */

public class TopSongModel {
    private String songName;
    private String smallImage;
    private String largeImage;
    private String singerName;
    private String linkSource;

    public TopSongModel(String songName, String smallImage, String largeImage, String singerName) {
        this.songName = songName;
        this.smallImage = smallImage;
        this.largeImage = largeImage;
        this.singerName = singerName;
    }

    public String getLinkSource() {
        return linkSource;
    }

    public void setLinkSource(String linkSource) {
        this.linkSource = linkSource;
    }

    public String getSongName() {
        return songName;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public String getSingerName() {
        return singerName;
    }
}
