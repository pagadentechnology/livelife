package id.tech.rcslive.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.ActionBar;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.security.auth.login.LoginException;

import id.tech.rcslive.services.StartEventService;
import id.tech.rcslive.util.*;

public class Splashscreen extends AppCompatActivity {
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        ActionBar ac = getSupportActionBar();
        ac.hide();

        new AsyncTask_LoadData().execute();
    }

    private class AsyncTask_LoadData extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try{
                Thread.sleep(1500);

                sp = getSharedPreferences(ParameterCollections.SPF_NAME, MODE_PRIVATE);
                Intent notif_service = new Intent(Splashscreen.this, StartEventService.class);
                notif_service.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                notif_service.addFlags(Intent.FLAG_RECEIVER_NO_ABORT);
                //add FLAG_RECEIVER_FOREGROUND to force the intent in foreground
                notif_service.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
                startService(notif_service);
                Log.e("Service Started", "");
//                showHashKey();
            }catch (Exception e){

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            boolean isLogged = sp.getBoolean(ParameterCollections.SPF_LOGGED, false);

            if(isLogged){
                startActivity(new Intent(getApplicationContext(), MenuUtama.class));
                finish();
            }else{
                showHashKey();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        }
    }

    private void showHashKey()
    {
        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "id.tech.rcslive.activity",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

    }
}
