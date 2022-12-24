package com.tutuit.a1.ui.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tutuit.a1.ui.activity.MainActivity;

public class BottomSheetClickReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        MainActivity activity = (MainActivity) context;
        activity.setBottomSheetState(intent.getStringExtra("name"),intent.getStringExtra("singer"));
    }
}