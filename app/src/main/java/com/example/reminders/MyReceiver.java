package com.example.reminders;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intent1 = new Intent(context, MyIntentService.class);
        Log.d("DARE", "Sega sme vo receiver-ot");
        context.startService(intent1);
    }
}