package id.tech.rcslive.activity;

import butterknife.OnClick;
import id.tech.rcslive.adapters.Rest_Adapter;
import id.tech.rcslive.dialogs.DialogFragmentProgress;
import id.tech.rcslive.models.Pojo_EventHighlight;
import id.tech.rcslive.models.Pojo_ResponseLogin;
import id.tech.rcslive.util.*;
import id.tech.rcslive.fragment.WalkthroughFragmentActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import android.support.v4.view.ViewPager;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

public class LoginForm  extends AppCompatActivity {
    private CallbackManager cm;
    private AccessTokenTracker accessTokenTracker;
    private AccessToken accessToken;
    private LoginResult json_result;
    @Bind(R.id.btn_fb_connect) View btn_Fb;
    @Bind(R.id.btn_fb_connect_login) View btn_Fb_Login;
    private String user_id, user_email, user_fullname, user_foto, user_gender;
    @Bind(R.id.ed_username)
    EditText ed_Username;
    @Bind(R.id.ed_password) EditText ed_Password;
    @Bind(R.id.btn_register)
    Button btn_register;
    @OnClick(R.id.btn_register) void onCLickRegister(){
        Intent intent = new Intent(getApplicationContext(), RegisterForm.class);
        intent.putExtra("register_fb", false);
        startActivity(intent);
        finish();
    }
    SharedPreferences spf;
    private boolean bool_fb_login = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login_form);
        ButterKnife.bind(this);

        spf = getSharedPreferences(ParameterCollections.SPF_NAME, MODE_PRIVATE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Gabung Yuk");

        btn_Fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bool_fb_login = false;
                LoginManager.getInstance().logInWithReadPermissions(LoginForm.this, Arrays.asList("public_profile", "email"));
            }
        });

        btn_Fb_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bool_fb_login = true;
                LoginManager.getInstance().logInWithReadPermissions(LoginForm.this, Arrays.asList("public_profile", "email"));
            }
        });

        cm = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = AccessToken.getCurrentAccessToken();


            }
        };


        LoginManager.getInstance().registerCallback(cm, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.e("sukses", loginResult.toString());
                        json_result = loginResult;

                        GraphRequest request = GraphRequest.newMeRequest(json_result.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject user, GraphResponse response) {

                                user_id = user.optString("id");
                                user_fullname = user.optString("name");
                                user_email = user.optString("email");
                                user_gender = user.optString("gender");

                                try {
//                            String url_img_profile = "http://graph.facebook.com/"+id+"/picture.type(large)";
//                            ServiceHandlerJSON sh = new ServiceHandlerJSON();

                                    JSONObject obj_pic = user.optJSONObject("picture");
                                    JSONObject obj_data = obj_pic.optJSONObject("data");
                                    user_foto = obj_data.optString("url");


                                } catch (Exception e) {

                                }

                                spf.edit().putString(ParameterCollections.SPF_USER_PHOTO_URL,"").commit();
                                Log.e("fb response = ", user_fullname + " , " + user_email + ", " + user_foto);

                                if(bool_fb_login){
                                    // API cek ke DB apa sdh terdaftar email by facebooknya


                                }else{
                                    Intent intent = new Intent(getApplicationContext(), RegisterForm.class);
                                    intent.putExtra("register_fb", true);
                                    intent.putExtra("name", user_fullname);
                                    intent.putExtra("email", user_email);
                                    intent.putExtra("url_foto", user_foto);
                                    startActivity(intent);
                                    finish();
                                }

                            }
                        });

                        final Bundle b = new Bundle();
                        b.putString("fields", "name,email,gender,picture.type(large)");
                        request.setParameters(b);
                        request.executeAsync();

//                GraphRequest.newMeRequest(json_result.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//
//                    @Override
//                    public void onCompleted(JSONObject user, GraphResponse response) {
//                        String id = user.optString("id");
//                        String name = user.optString("name");
//                        String email = user.optString("email");
//
//
//                    }
//                }).executeAsync();

//                new AsyncTask_FB_LOGIN().execute();
                    }

                    @Override
                    public void onCancel() {
                        Log.e("canceled", "");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.e("error", error.getMessage().toString());
                    }
                }

        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cm.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_login, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;

            case R.id.action_login:
                if(ed_Password.getText().toString().equals("") || ed_Username.getText().toString().equals("")){

                    Toast.makeText(getApplicationContext(), "Please Fill Username & Password", Toast.LENGTH_LONG).show();
                }else{

                    //Sementara
//                    spf.edit().putString(ParameterCollections.SPF_USER_ID, "1").commit();
//                    spf.edit().putString(ParameterCollections.SPF_USER_NAME, "Ridho Maulana").commit();
//                    spf.edit().putString(ParameterCollections.SPF_USER_PHOTO_URL, "http://www.lcfc.com/images/common/bg_player_profile_default_big.png").commit();
//                    spf.edit().putBoolean(ParameterCollections.SPF_LOGGED, true).commit();
//                    startActivity(new Intent(getApplicationContext(), MenuUtama.class));
//                    finish();
                    new ASyncTask_Login().execute();
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class ASyncTask_Login extends AsyncTask<Void,Void,Void>{
        DialogFragmentProgress pDialog;
        String cCode="0";
        String cEmail, cPassword, cResponse = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new DialogFragmentProgress();
            pDialog.show(getSupportFragmentManager(), "");

            cEmail = ed_Username.getText().toString();
            cPassword = ed_Password.getText().toString();
        }

        @Override
        protected Void doInBackground(Void... params) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(ParameterCollections.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
            Rest_Adapter adapter = retrofit.create(Rest_Adapter.class);
            Call<Pojo_ResponseLogin> call = adapter.login(ParameterCollections.KIND_LOGIN, cEmail, cPassword);

            try{
                Response<Pojo_ResponseLogin> response = call.execute();
                if(response.isSuccess()){
                    if(response.body() != null){

                        cCode= response.body().getJsonCode().toString();
                        cResponse= response.body().getResponse().toString();

                        if(cCode.equals("1")){
                            String id = response.body().getData().get(0).getId();
                            String name = response.body().getData().get(0).getMemberName();
                            String url_photo = ParameterCollections.BASE_URL_IMG_THUMB +  response.body().getData().get(0).getMemberPhoto();

                            spf.edit().putString(ParameterCollections.SPF_USER_ID, id).commit();
                            spf.edit().putString(ParameterCollections.SPF_USER_NAME, name).commit();
                            spf.edit().putString(ParameterCollections.SPF_USER_PHOTO_URL, url_photo).commit();
                            spf.edit().putBoolean(ParameterCollections.SPF_LOGGED, true).commit();
                        }
                    }

                }else{
                    cCode="0";
                    cResponse = response.errorBody().toString();
                }

            }catch (IOException e){
                cCode="0";
                cResponse = ParameterCollections.ERROR_MESSAGE + e.getMessage().toString();
            }catch (Exception e){
                cCode="0";
                cResponse = ParameterCollections.ERROR_MESSAGE + e.getMessage().toString();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pDialog.dismiss();

            if(cCode.equals("1")){
                startActivity(new Intent(getApplicationContext(), MenuUtama.class));
                finish();
            }else{
                Toast.makeText(getApplicationContext(), cResponse, Toast.LENGTH_LONG).show();
            }
        }
    }
}
