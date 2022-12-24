package com.tutuit.a1.ui.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tutuit.a1.ui.activity.MainActivity;

public class SongPlayReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        MainActivity activity = (MainActivity) context;
        activity.initBottomVisibility(intent.getStringExtra("songName"),intent.getStringExtra("songSinger"));
    }
}