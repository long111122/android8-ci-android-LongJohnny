package image.manual.android.apptheanime.models;

/**
 * Created by EDGY on 5/22/2017.
 */

public class AnimeInfor {
    private int id;
    private String name;
    private String image;
    private int numberOfChapter;
    private int numberOfComments;

    public AnimeInfor(int id, String name, String image, int numberOfChapter, int numberOfComments) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.numberOfChapter = numberOfChapter;
        this.numberOfComments = numberOfComments;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getNumberOfChapter() {
        return numberOfChapter;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }
}
