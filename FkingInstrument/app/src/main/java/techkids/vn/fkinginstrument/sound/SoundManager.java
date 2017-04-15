package techkids.vn.fkinginstrument.sound;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by EDGY on 4/15/2017.
 */

public class SoundManager {
    private static final int NUMBER_OF_NOTES = 10;
    public static SoundPool soundPool = new SoundPool(NUMBER_OF_NOTES, AudioManager.STREAM_MUSIC, 0);
    public static ArrayList<Integer> soundList = new ArrayList<>();

    public static void loadSoundIntoList(Context context){
        for (int i = 1; i <= NUMBER_OF_NOTES; i++){
            int resIdSound = context.getResources().getIdentifier("sound_"+i, "raw", context.getPackageName());
            int soundPoolId = soundPool.load(context,resIdSound,1);
            soundList.add(soundPoolId);
        }
    }

    static HashMap<String, Integer> listSounds = new HashMap<>();

    static{
        listSounds.put("sound_1",0);
        listSounds.put("sound_2",1);
        listSounds.put("sound_3",2);
        listSounds.put("sound_4",3);
        listSounds.put("sound_5",4);
        listSounds.put("sound_6",5);
        listSounds.put("sound_7",6);
        listSounds.put("sound_8",7);
        listSounds.put("sound_9",8);
        listSounds.put("sound_10",9);
    }

    public static void PlaySound(String string){
        soundPool.play(soundList.get(listSounds.get(string)), 1.0f, 1.0f, 1, 0, 1.0f);
    }
}
