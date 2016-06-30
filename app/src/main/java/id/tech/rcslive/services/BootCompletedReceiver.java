package id.tech.rcslive.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by macbook on 6/6/16.
 */
public class BootCompletedReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {

        //beri jeda 30dtk sebelum menjalanan SMS Service
        try{
            Thread.sleep(30000);
        }catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Intent notif_service = new Intent(context, StartEventService.class);
        notif_service.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        notif_service.addFlags(Intent.FLAG_RECEIVER_NO_ABORT);
        //add FLAG_RECEIVER_FOREGROUND to force the intent in foreground
        notif_service.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        context.startService(notif_service);

        Log.e("SERVICE >>>>", "SERVICE START");
    }
}
