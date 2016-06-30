package id.tech.rcslive.services;

import id.tech.rcslive.activity.DetailEvent;
import  id.tech.rcslive.activity.R;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import id.tech.rcslive.activity.Splashscreen;
import id.tech.rcslive.adapters.Rest_Adapter;
import id.tech.rcslive.models.Pojo_EventJoined;
import id.tech.rcslive.models.Rowdata_EventJoined;
import id.tech.rcslive.util.ParameterCollections;
import id.tech.rcslive.util.PublicFunctions;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by macbook on 6/6/16.
 */
public class CheckTomrrowEventService extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context,final Intent intent) {
        Rest_Adapter adapter = PublicFunctions.initRetrofit();

        SharedPreferences spf = context.getSharedPreferences(ParameterCollections.SPF_NAME, Context.MODE_PRIVATE);
        Call<Pojo_EventJoined> call = adapter.get_all_events_joined(ParameterCollections.KIND_EVENT_JOINED,
                spf.getString(ParameterCollections.SPF_USER_ID, ""));

        try {
//            Response<Pojo_EventJoined> response_event = call.execute();

            call.enqueue(new Callback<Pojo_EventJoined>() {
                @Override
                public void onResponse(Response<Pojo_EventJoined> response, Retrofit retrofit) {
                    if (response.body().getJsonCode().equals("1")) {
                        if (response.body().getData() != null) {
                            for (int i = 0; i < response.body().getData().size(); i++) {
                                if(response.body().getData().get(i).getEventType().equals("Irregular")){
                                    String date_dateline = response.body().getData().get(i).getConvertDate();


                                    Calendar calendar = Calendar.getInstance();

                                    calendar.add(Calendar.DAY_OF_YEAR , 1);
                                    Date tomorrow = calendar.getTime();

                                    DateFormat dateFormat = new SimpleDateFormat("EEEE, dd/MM/yyyy");
                                    String today_string = dateFormat.format(tomorrow);

                                    if(today_string.equals(date_dateline)){
                                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                                        builder.setSmallIcon(R.drawable.ic_launcher);
                                        builder.setContentTitle("Your Tomorrow Activity");
                                        builder.setContentText(response.body().getData().get(i).getEventTitle());
                                        Intent intent_detail_notif = new Intent(context, DetailEvent.class);

                                        intent_detail_notif.putExtra("id_event", response.body().getData().get(i).getId());
                                        intent_detail_notif.putExtra("url_photo_event", ParameterCollections.BASE_URL_IMG_THUMB + response.body().getData().get(i).getEventPhoto());
                                        intent_detail_notif.putExtra("judul_event", response.body().getData().get(i).getEventTitle());
                                        intent_detail_notif.putExtra("alamat_event", response.body().getData().get(i).getEventLocation());
                                        intent_detail_notif.putExtra("tgl_event", response.body().getData().get(i).getConvertDate());
                                        intent_detail_notif.putExtra("lat_event", response.body().getData().get(i).getEventLat());
                                        intent_detail_notif.putExtra("lon_event", response.body().getData().get(i).getEventLon());
                                        intent_detail_notif.putExtra("desc_event", response.body().getData().get(i).getEventDescription());
                                        intent_detail_notif.putExtra("event_documentationid", response.body().getData().get(i).getEventDocumentationid());
                                        intent_detail_notif.putExtra("member_name", response.body().getData().get(i).getMemberName());
                                        intent_detail_notif.putExtra("member_phone", response.body().getData().get(i).getMemberPhone());
                                        intent_detail_notif.putExtra("member_photo", response.body().getData().get(i).getMemberPhoto());

                                        intent_detail_notif.putExtra("joinID", response.body().getData().get(i).getJoinID());

                                        PendingIntent pi = PendingIntent.getActivity(context, 0, intent_detail_notif,
                                                PendingIntent.FLAG_UPDATE_CURRENT);
                                        builder.setContentIntent(pi);
                                        builder.addAction(R.drawable.ic_launcher, "Nokia LiveLife", pi);
                                        builder.setAutoCancel(true);
                                        int notif_id = Integer.parseInt(response.body().getData().get(i).getId());

                                        NotificationManager notif = (NotificationManager)
                                                context.getSystemService(Context.NOTIFICATION_SERVICE);
                                        notif.notify(notif_id, builder.build());
                                    }

                                }else {
                                    String date = response.body().getData().get(i).getDaySchedule();
                                    if(date.contains("and")){
                                        Log.e("Date Event",date);
                                    }else{
                                        Log.e("Date Event",date);
                                        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
                                        Date date_now = new Date();
                                        String dayOfWeek = sdf.format(date_now);
                                        Log.e("Day of Week",dayOfWeek);

                                        int x = 0;
                                        int y =0;

                                        if(dayOfWeek.equals("Monday")){
                                            x = 1;
                                        }else if(dayOfWeek.equals("Tuesday")){
                                            x = 2;
                                        }else if(dayOfWeek.equals("Wednesday")){
                                            x = 3;
                                        }else if(dayOfWeek.equals("Thursday")){
                                            x = 4;
                                        }else if(dayOfWeek.equals("Friday")){
                                            x = 5;
                                        }else if(dayOfWeek.equals("Saturday")){
                                            x = 6;
                                        }else if(dayOfWeek.equals("Sunday")){
                                            x = 7;
                                        }

                                        if(date.contains("Monday")){
                                            y = 1;
                                        }else if(date.contains("Tuesday")){
                                            y = 2;
                                        }else if(date.contains("Wednesday")){
                                            y = 3;
                                        }else if(date.contains("Thursday")){
                                            y = 4;
                                        }else if(date.contains("Friday")){
                                            y = 5;
                                        }else if(date.contains("Saturday")){
                                            y = 6;
                                        }else if(date.contains("Sunday")){
                                            y = 7;
                                        }

                                        Log.e("Date Calculate", "Now x = " +  String.valueOf(x));
                                        Log.e("Date Calculate", "Date y = " +  String.valueOf(y));
//                                        Log.e("Date Calculate", "y + 1 = " +  String.valueOf(y + 1));
                                        if(x+1 == y){
                                            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                                            builder.setSmallIcon(R.drawable.ic_launcher);
                                            builder.setContentTitle("Your Tomorrow Activity");
                                            builder.setContentText(response.body().getData().get(i).getEventTitle());
                                            Intent intent_detail_notif = new Intent(context, DetailEvent.class);

                                            intent_detail_notif.putExtra("id_event", response.body().getData().get(i).getId());
                                            intent_detail_notif.putExtra("url_photo_event", ParameterCollections.BASE_URL_IMG_THUMB + response.body().getData().get(i).getEventPhoto());
                                            intent_detail_notif.putExtra("judul_event", response.body().getData().get(i).getEventTitle());
                                            intent_detail_notif.putExtra("alamat_event", response.body().getData().get(i).getEventLocation());
                                            intent_detail_notif.putExtra("tgl_event", date);
                                            intent_detail_notif.putExtra("lat_event", response.body().getData().get(i).getEventLat());
                                            intent_detail_notif.putExtra("lon_event", response.body().getData().get(i).getEventLon());
                                            intent_detail_notif.putExtra("desc_event", response.body().getData().get(i).getEventDescription());
                                            intent_detail_notif.putExtra("event_documentationid", response.body().getData().get(i).getEventDocumentationid());
                                            intent_detail_notif.putExtra("member_name", response.body().getData().get(i).getMemberName());
                                            intent_detail_notif.putExtra("member_phone", response.body().getData().get(i).getMemberPhone());
                                            intent_detail_notif.putExtra("member_photo", response.body().getData().get(i).getMemberPhoto());

                                            intent_detail_notif.putExtra("joinID", response.body().getData().get(i).getJoinID());

                                            PendingIntent pi = PendingIntent.getActivity(context, 0, intent_detail_notif,
                                                    PendingIntent.FLAG_UPDATE_CURRENT);
                                            builder.setContentIntent(pi);
                                            builder.addAction(R.drawable.ic_launcher, "Nokia LiveLife", pi);
                                            builder.setAutoCancel(true);
                                            int notif_id = Integer.parseInt(response.body().getData().get(i).getId());

                                            NotificationManager notif = (NotificationManager)
                                                    context.getSystemService(Context.NOTIFICATION_SERVICE);
                                            notif.notify(notif_id, builder.build());

                                        }else if( x -6 == 1){
                                            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                                            builder.setSmallIcon(R.drawable.ic_launcher);
                                            builder.setContentTitle("Your Tomorrow Activity");
                                            builder.setContentText(response.body().getData().get(i).getEventTitle());
                                            Intent intent_detail_notif = new Intent(context, DetailEvent.class);

                                            intent_detail_notif.putExtra("id_event", response.body().getData().get(i).getId());
                                            intent_detail_notif.putExtra("url_photo_event", ParameterCollections.BASE_URL_IMG_THUMB + response.body().getData().get(i).getEventPhoto());
                                            intent_detail_notif.putExtra("judul_event", response.body().getData().get(i).getEventTitle());
                                            intent_detail_notif.putExtra("alamat_event", response.body().getData().get(i).getEventLocation());
                                            intent_detail_notif.putExtra("tgl_event", date);
                                            intent_detail_notif.putExtra("lat_event", response.body().getData().get(i).getEventLat());
                                            intent_detail_notif.putExtra("lon_event", response.body().getData().get(i).getEventLon());
                                            intent_detail_notif.putExtra("desc_event", response.body().getData().get(i).getEventDescription());
                                            intent_detail_notif.putExtra("event_documentationid", response.body().getData().get(i).getEventDocumentationid());
                                            intent_detail_notif.putExtra("member_name", response.body().getData().get(i).getMemberName());
                                            intent_detail_notif.putExtra("member_phone", response.body().getData().get(i).getMemberPhone());
                                            intent_detail_notif.putExtra("member_photo", response.body().getData().get(i).getMemberPhoto());

                                            intent_detail_notif.putExtra("joinID", response.body().getData().get(i).getJoinID());

                                            PendingIntent pi = PendingIntent.getActivity(context, 0, intent_detail_notif,
                                                    PendingIntent.FLAG_UPDATE_CURRENT);
                                            builder.setContentIntent(pi);
                                            builder.addAction(R.drawable.ic_launcher, "Nokia LiveLife", pi);
                                            builder.setAutoCancel(true);
                                            int notif_id = Integer.parseInt(response.body().getData().get(i).getId());

                                            NotificationManager notif = (NotificationManager)
                                                    context.getSystemService(Context.NOTIFICATION_SERVICE);
                                            notif.notify(notif_id, builder.build());
                                        }


                                    }

                                }

                            }
                        }else{
                            Log.e("Event Test", "Data null");
                        }
                    }else{
                        Log.e("Event Test", "JSON Code != 1");
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.e("Event Test", "failure");

                }
            });

        }catch (Exception e){
            Log.e("Event Test", "Exception");
        }

    }

}
