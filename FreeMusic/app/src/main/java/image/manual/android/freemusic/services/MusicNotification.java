package image.manual.android.freemusic.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import image.manual.android.freemusic.R;
import image.manual.android.freemusic.activities.MainActivity;
import image.manual.android.freemusic.databases.models.TopSongModel;

/**
 * Created by EDGY on 6/16/2017.
 */

public class MusicNotification {
    public static NotificationCompat.Builder builder;
    public static NotificationManager notificationManager;
    public static int NOTIFICATION_ID = 1;
    public static RemoteViews remoteViews;

    public static void setupNotification(Context context, TopSongModel topSongModel){
        //1. Create intent
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        //2. Create remove view
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_layout);
        remoteViews.setTextViewText(R.id.tv_song, topSongModel.getSongName());
        remoteViews.setTextViewText(R.id.tv_singer, topSongModel.getSingerName());
        remoteViews.setImageViewResource(R.id.iv_play, R.drawable.ic_pause_black_24dp);
        setOnPauseNotification(context);

        //3. Pending intent
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //4. Create builder
        builder = new NotificationCompat.Builder(context)
                .setOngoing(true)
                .setSmallIcon(R.drawable.ic_music_note_black_24dp)
                .setContent(remoteViews)
                .setContentIntent(pendingIntent);

        //5. Create notification
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }

    public static void setOnPauseNotification(Context context){
        Intent intent = new Intent(context, MusicService.class);
        PendingIntent pendingIntent = PendingIntent.getService(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.iv_play, pendingIntent);
    }

    public static void updateNotification(boolean isPlaying){
        if(isPlaying){
            remoteViews.setImageViewResource(R.id.iv_play, R.drawable.ic_pause_black_24dp);
        } else {
            remoteViews.setImageViewResource(R.id.iv_play, R.drawable.ic_play_arrow_black_24dp);
        }
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
