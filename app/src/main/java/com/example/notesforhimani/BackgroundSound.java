package com.example.notesforhimani;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Random;

public class BackgroundSound extends Service {
    MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        int[] musicArray = new int[]{R.raw.thatyou,R.raw.smile,R.raw.need};
        Random random=new Random();
        mediaPlayer = MediaPlayer.create(this,musicArray[random.nextInt(3)]);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(100,100);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        mediaPlayer.start();
        return flags;
    }
}
