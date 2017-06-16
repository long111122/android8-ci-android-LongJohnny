package image.manual.android.freemusic.utils;

import java.util.concurrent.TimeUnit;

/**
 * Created by EDGY on 6/15/2017.
 */

public class Util {
    public static String convertTime(int time){
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(time),
                TimeUnit.MILLISECONDS.toSeconds(time)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
    }
}
