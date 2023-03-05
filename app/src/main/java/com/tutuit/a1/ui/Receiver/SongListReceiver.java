package com.tutuit.a1.ui.Receiver;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.tutuit.a1.ui.activity.MainActivity;

import java.util.List;


// 播放栏唤醒播放列表的广播
// 如何获取播放列表——
public class SongListReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
/*         MainActivity activity = (MainActivity) context;
 *//*
        activity.setBottomSheetSongList(musicBeans);*//*
        List<Fragment> fragments = activity.getFragmentManager().getFragments();*/


    }
}
