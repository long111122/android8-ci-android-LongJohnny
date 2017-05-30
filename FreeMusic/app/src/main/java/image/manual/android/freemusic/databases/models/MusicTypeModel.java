package image.manual.android.freemusic.databases.models;

/**
 * Created by EDGY on 5/28/2017.
 */

public class MusicTypeModel {
    private String id;
    private String key;
    private int idImage;

    public  MusicTypeModel(String id, String key, int idImage) {
        this.id = id;
        this.key = key;
        this.idImage = idImage;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public int getIdImage() {
        return idImage;
    }
}
