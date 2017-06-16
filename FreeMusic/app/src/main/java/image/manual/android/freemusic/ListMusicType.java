package image.manual.android.freemusic;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EDGY on 6/16/2017.
 */

public class ListMusicType {
    @SerializedName("subgenres")
    private List<MusicType> musicTypeList;

    @SerializedName("2")
    private MusicType musicType2;
    @SerializedName("3")
    private MusicType musicType3;
    @SerializedName("4")
    private MusicType musicType4;
    @SerializedName("5")
    private MusicType musicType5;
    @SerializedName("6")
    private MusicType musicType6;
    @SerializedName("7")
    private MusicType musicType7;
    @SerializedName("8")
    private MusicType musicType8;

    @SerializedName("10")
    private MusicType musicType10;
    @SerializedName("11")
    private MusicType musicType11;
    @SerializedName("12")
    private MusicType musicType12;
    @SerializedName("13")
    private MusicType musicType13;
    @SerializedName("14")
    private MusicType musicType14;
    @SerializedName("15")
    private MusicType musicType15;
    @SerializedName("16")
    private MusicType musicType16;
    @SerializedName("17")
    private MusicType musicType17;
    @SerializedName("18")
    private MusicType musicType18;
    @SerializedName("19")
    private MusicType musicType19;
    @SerializedName("20")
    private MusicType musicType20;
    @SerializedName("21")
    private MusicType musicType21;
    @SerializedName("22")
    private MusicType musicType22;
    @SerializedName("23")
    private MusicType musicType23;
    @SerializedName("24")
    private MusicType musicType24;
    @SerializedName("25")
    private MusicType musicType25;

    @SerializedName("27")
    private MusicType musicType27;
    @SerializedName("28")
    private MusicType musicType28;
    @SerializedName("29")
    private MusicType musicType29;
    @SerializedName("30")
    private MusicType musicType30;

    @SerializedName("50")
    private MusicType musicType50;
    @SerializedName("52")
    private MusicType musicType52;
    @SerializedName("53")
    private MusicType musicType53;

    @SerializedName("1122")
    private MusicType musicType1122;
    @SerializedName("1232")
    private MusicType musicType1232;
    @SerializedName("1243")
    private MusicType musicType1243;
    @SerializedName("1262")
    private MusicType musicType1262;

    @SerializedName("50000061")
    private MusicType musicType50000061;
    @SerializedName("50000063")
    private MusicType musicType50000063;
    @SerializedName("50000064")
    private MusicType musicType50000064;
    @SerializedName("50000066")
    private MusicType musicType50000066;
    @SerializedName("50000068")
    private MusicType musicType50000068;


    public ListMusicType(List<MusicType> musicTypeList) {
        this.musicTypeList = musicTypeList;
    }

    public List<MusicType> getMusicTypeList() {
        musicTypeList = new ArrayList<>();
        musicTypeList.add(musicType2);
        musicTypeList.add(musicType3);
        musicTypeList.add(musicType4);
        musicTypeList.add(musicType5);
        musicTypeList.add(musicType6);
        musicTypeList.add(musicType7);
        musicTypeList.add(musicType8);

        musicTypeList.add(musicType10);
        musicTypeList.add(musicType11);
        musicTypeList.add(musicType12);
        musicTypeList.add(musicType13);
        musicTypeList.add(musicType14);
        musicTypeList.add(musicType15);
        musicTypeList.add(musicType16);
        musicTypeList.add(musicType17);
        musicTypeList.add(musicType18);
        musicTypeList.add(musicType19);
        musicTypeList.add(musicType20);
        musicTypeList.add(musicType21);
        musicTypeList.add(musicType22);
        musicTypeList.add(musicType23);
        musicTypeList.add(musicType24);
        musicTypeList.add(musicType25);

        musicTypeList.add(musicType27);
        musicTypeList.add(musicType28);
        musicTypeList.add(musicType29);
        musicTypeList.add(musicType30);

        musicTypeList.add(musicType50);
        musicTypeList.add(musicType52);
        musicTypeList.add(musicType53);

        musicTypeList.add(musicType1122);
        musicTypeList.add(musicType1232);
        musicTypeList.add(musicType1243);
        musicTypeList.add(musicType1262);

        musicTypeList.add(musicType50000061);
        musicTypeList.add(musicType50000063);
        musicTypeList.add(musicType50000064);
        musicTypeList.add(musicType50000066);
        musicTypeList.add(musicType50000068);
        return musicTypeList;
    }
}
