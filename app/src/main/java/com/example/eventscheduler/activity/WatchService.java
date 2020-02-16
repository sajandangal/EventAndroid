package com.example.eventscheduler.activity;

import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

public class WatchService extends WearableListenerService {
    private final static String TAG = WatchService.class.getSimpleName();

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d(TAG, "onMessageReceived()");
        if (messageEvent.getPath().equals("/my_path")) {
            String message = new String(messageEvent.getData());
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            Log.d(TAG, message);
        } else
            super.onMessageReceived(messageEvent);
    }
}
