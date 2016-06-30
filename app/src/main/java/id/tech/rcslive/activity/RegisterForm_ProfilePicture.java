package id.tech.rcslive.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.pkmmte.view.CircularImageView;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.blastering99.htmlloader.CustomProgressDialog;
import id.tech.rcslive.adapters.Rest_Adapter;
import id.tech.rcslive.dialogs.DialogUploadImage;
import id.tech.rcslive.models.PojoResponseInsert;
import id.tech.rcslive.models.Pojo_ResponseRegister;
import id.tech.rcslive.util.ParameterCollections;
import id.tech.rcslive.util.PublicFunctions;
import retrofit.Call;
import retrofit.Response;

/**
 * Created by macbook on 5/13/16.
 */
public class RegisterForm_ProfilePicture extends AppCompatActivity {
    SharedPreferences spf;
    @Bind(R.id.img)CircularImageView img;
    @OnClick(R.id.img)public void onImageClick(){
        Intent intent = new Intent(getApplicationContext(), DialogUploadImage.class);
        startActivityForResult(intent, 100);
    }
    @OnClick(R.id.btn) public void register(){
        new ASyncTask_Register().execute();

    }
    String url_foto;
    String _ed_username, _ed_email, _ed_phone, _ed_password;
    String mCountrySelected,mCitySelected;
    Activity activity;


    private class ASyncTask_Register extends AsyncTask<Void,Void,Void>{
        boolean isSukses = false;
        String message = "";
        private CustomProgressDialog pDialog;
        private String idUser, nameUser, urlPhotoUsr= "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new CustomProgressDialog(activity, R.style.SpotsDialogDefault);
            pDialog.setLoaderType(CustomProgressDialog.SPINNING_SQUARE);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            Rest_Adapter adapter = PublicFunctions.initRetrofit();
            try {
                RequestBody _ed_username_ = RequestBody.create(MediaType.parse("text/plain"), _ed_username);
                RequestBody _ed_email_ = RequestBody.create(MediaType.parse("text/plain"), _ed_email);
                RequestBody _ed_phone_ = RequestBody.create(MediaType.parse("text/plain"), _ed_phone);
                RequestBody _ed_password_ = RequestBody.create(MediaType.parse("text/plain"), _ed_password);
                RequestBody _country_ = RequestBody.create(MediaType.parse("text/plain"), mCountrySelected);
                RequestBody _city_ = RequestBody.create(MediaType.parse("text/plain"), mCitySelected);

                File file = new File(url_foto);
                RequestBody body_img = RequestBody.create(MediaType.parse("image/*"), file);

                Call<Pojo_ResponseRegister> call = adapter.register(_ed_username_,
                        _ed_email_,_ed_phone_,_ed_password_,_country_, _city_ , body_img);
                Response<Pojo_ResponseRegister> response = call.execute();

                if(response.isSuccess()){
                    if(response.body() != null){
                        if(response.body().getJsonCode().equals("1")){
                            isSukses = true;
                            idUser = response.body().getId();
                            nameUser= response.body().getMemberName();
                            urlPhotoUsr =  response.body().getMemberPhoto();

                            urlPhotoUsr = ParameterCollections.BASE_URL_IMG_THUMB + urlPhotoUsr;

                            spf.edit().putString(ParameterCollections.SPF_USER_ID, idUser).commit();
                            spf.edit().putString(ParameterCollections.SPF_USER_NAME, nameUser).commit();
                            spf.edit().putString(ParameterCollections.SPF_USER_PHOTO_URL, urlPhotoUsr).commit();
                            spf.edit().putBoolean(ParameterCollections.SPF_LOGGED, true).commit();
                        }else{
                            message = response.body().getResponse();
                        }
                    }
                }else{
                    message = response.errorBody().toString();
                }

            }catch (IOException e){
                message = "Something Wrong, IOException";

            }catch (Exception e){
                message = "Something Wrong, General Exception  ";
            }

            return null;


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pDialog.dismiss();
            if(isSukses){

                Intent intent = new Intent(getApplicationContext(), MenuUtama.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_profilepic);
        ButterKnife.bind(this);
        activity = this;
        spf = getSharedPreferences(ParameterCollections.SPF_NAME, MODE_PRIVATE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Photo Profile");

        _ed_username = getIntent().getStringExtra("_ed_username");
        _ed_email = getIntent().getStringExtra("_ed_email");
        _ed_phone = getIntent().getStringExtra("_ed_phone");
        _ed_password = getIntent().getStringExtra("_ed_password");
        mCountrySelected = getIntent().getStringExtra("_country");
        mCitySelected = getIntent().getStringExtra("_city");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 100){
            if(resultCode == RESULT_OK){
                url_foto = data.getStringExtra("mUrl_Img");
                Target<Bitmap> target = new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        img.setImageBitmap(resource);
                    }
                };
                Glide.with(getApplicationContext()).load(url_foto).asBitmap().into(target);
            }
        }
    }
}
