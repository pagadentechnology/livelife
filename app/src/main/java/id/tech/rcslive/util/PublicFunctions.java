package id.tech.rcslive.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import id.tech.rcslive.adapters.Rest_Adapter;
import id.tech.rcslive.models.PojoResponseGmap;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by macbook on 3/31/16.
 */
public class PublicFunctions {

    public static Rest_Adapter initRetrofit(){
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(270, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(270, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ParameterCollections.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        Rest_Adapter adapter = retrofit.create(Rest_Adapter.class);
        return adapter;
    }

    public static Rest_Adapter initRetrofit_Gmaps(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ParameterCollections.BASE_URL_GMAP)
                .addConverterFactory(GsonConverterFactory.create()).build();
        Rest_Adapter adapter = retrofit.create(Rest_Adapter.class);
        return adapter;
    }

    public static boolean getLocationNow(Context context, SharedPreferences sh) {
        GPSTracker gps = new GPSTracker(context);
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

//            now_latitude = String.valueOf(latitude);
//            now_longitude = String.valueOf(longitude);

            Log.e("Longitude", String.valueOf(longitude));
            Log.e("Latitude", String.valueOf(latitude));

            sh.edit()
                    .putString(ParameterCollections.TAG_LONGITUDE_NOW,
                            String.valueOf(longitude)).commit();
            sh.edit()
                    .putString(ParameterCollections.TAG_LATITUDE_NOW,
                            String.valueOf(latitude)).commit();

            return true;

        } else {
            return false;
        }
    }

    public static String calculateDistance(String latitude_now, String longitude_now,
                                    String latitude_destination, String longitude_destination){
//        String stringGmaps =  "http://maps.google.com/maps/api/directions/json?origin="+ latitude_now +"," + longitude_now +
//                "&destination= "+ latitude_destination + "," + longitude_destination + "&sensor=false&units=metric";

        try{
            Rest_Adapter adapter = PublicFunctions.initRetrofit_Gmaps();
            Call<PojoResponseGmap> call = adapter.calculate_distance(latitude_now + "," + longitude_now,
                    latitude_destination + "," + longitude_destination, "false", "metric");
            Response<PojoResponseGmap> response = call.execute();
            if(response.isSuccess()){

            }
//            string stringGmaps = adapter.
            JSONObject jsonObject = new JSONObject("");
            JSONArray jsonArray_routes = jsonObject.getJSONArray("routes");
            JSONArray jsonArray_legs = jsonArray_routes.getJSONArray(2);
            JSONObject jsonObject_distance = jsonArray_legs.getJSONObject(0);
            return jsonObject_distance.getString("text");
        } catch (IOException e){

        } catch(JSONException e){
            return "";
        }

        return "";
    }
}
