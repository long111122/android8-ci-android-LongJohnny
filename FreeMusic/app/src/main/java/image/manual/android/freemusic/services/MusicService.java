package image.manual.android.freemusic.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import image.manual.android.freemusic.R;
import image.manual.android.freemusic.managers.MusicManager;

/**
 * Created by EDGY on 6/16/2017.
 */

public class MusicService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null){
            if (MusicManager.hybridMediaPlayer.isPlaying()){
                MusicManager.hybridMediaPlayer.pause();
                MusicNotification.remoteViews.setImageViewResource(R.id.iv_play, R.drawable.ic_play_arrow_black_24dp);
            } else {
                MusicManager.hybridMediaPlayer.play();
                MusicNotification.remoteViews.setImageViewResource(R.id.iv_play, R.drawable.ic_pause_black_24dp);
            }
            MusicNotification.notificationManager.notify(MusicNotification.NOTIFICATION_ID,
                    MusicNotification.builder.build());
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
