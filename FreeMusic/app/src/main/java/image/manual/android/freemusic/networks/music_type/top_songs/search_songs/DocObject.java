package image.manual.android.freemusic.networks.music_type.top_songs.search_songs;

/**
 * Created by EDGY on 5/30/2017.
 */

public class DocObject {
    private String title;
    private String artist;
    private Source source;

    public DocObject(String title, String artist, Source source) {
        this.title = title;
        this.artist = artist;
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public Source getSource() {
        return source;
    }
}
