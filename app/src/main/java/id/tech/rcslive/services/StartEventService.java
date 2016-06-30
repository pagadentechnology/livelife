package id.tech.rcslive.services;

import android.app.Service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by macbook on 6/6/16.
 */
public class StartEventService extends Service {
    Intent myIntent;
    PendingIntent alarmIntent;
    AlarmManager alarams;
    SharedPreferences sp;
    String cContactId;
    Intent activate;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        myIntent = intent;

        //jalankan SMS Listener tiap 30mnt menghindari dimatikan oleh system
        Calendar c = Calendar.getInstance();
        activate = new Intent(StartEventService.this, CheckTomrrowEventService.class);
        activate.putExtra("code", "1");
        alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 111, activate,PendingIntent.FLAG_CANCEL_CURRENT);
        alarams = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarams.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),
                1000 * 60 * 2, alarmIntent);
        Log.e("Service Started", "");

        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
