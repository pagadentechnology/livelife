package id.tech.rcslive.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.kogitune.activity_transition.ActivityTransition;
import com.kogitune.activity_transition.ActivityTransitionLauncher;
import com.kogitune.activity_transition.ExitActivityTransition;
import com.pkmmte.view.CircularImageView;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.blastering99.htmlloader.CustomProgressDialog;
import id.tech.rcslive.adapters.Rest_Adapter;
import id.tech.rcslive.dialogs.DialogConfirmation;
import id.tech.rcslive.dialogs.DialogUploadImage;
import id.tech.rcslive.models.PojoCity;
import id.tech.rcslive.models.PojoCountry;
import id.tech.rcslive.models.PojoGroup;
import id.tech.rcslive.models.PojoResponseInsert;
import id.tech.rcslive.models.Pojo_ResponseAccountUser;
import id.tech.rcslive.models.Pojo_ResponseRegister;
import id.tech.rcslive.models.Rowdata_City;
import id.tech.rcslive.models.Rowdata_Country;
import id.tech.rcslive.models.Rowdata_Group;
import id.tech.rcslive.util.ParameterCollections;
import id.tech.rcslive.util.PublicFunctions;
import retrofit.Call;
import retrofit.Response;

/**
 * Created by macbook on 5/19/16.
 */
public class AccountDetail extends AppCompatActivity {
    @Bind(R.id.img_profile)
    CircularImageView img_Profile;
    SharedPreferences spf;
    @Bind(R.id.ed_username)EditText ed_username;
    @Bind(R.id.ed_email)EditText ed_email;
    @Bind(R.id.ed_phone)EditText ed_phone;
    @Bind(R.id.ed_password)EditText ed_password;
    @Bind(R.id.ed_password_retype)EditText ed_password_retype;
    @Bind(R.id.spinner_country)Spinner spinner_country;
    @Bind(R.id.spinner_city)Spinner spinner_city;
    @Bind(R.id.spinner_hometown)Spinner spinner_hometown;
    @Bind(R.id.spinner_group)Spinner spinner_group;
    String mCountrySelected,mCitySelected, mGroupSelected, mHomeTownSelected;
    @Bind(R.id.radio_male)RadioButton radio_male;
    @Bind(R.id.radio_female)RadioButton radio_female;
    @Bind(R.id.ed_ttl)TextView ed_ttl;
    @Bind(R.id.ed_bio)EditText ed_bio;
    String id_user, url_foto="";

    private ExitActivityTransition exitTransition;
    @OnClick(R.id.btn_logout) public void onLogout(){
        DialogConfirmation dialog = new DialogConfirmation();
        dialog.setSh(spf);
        dialog.setFrom(0);
        dialog.setText("Sure to Logout ?");
        dialog.show(getSupportFragmentManager(), "");

    }

    @OnClick(R.id.img_profile) public void onClickPhoto(){
        Intent intent = new Intent(getApplicationContext(), DialogUploadImage.class);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 100){
            if(resultCode == RESULT_OK){
                url_foto = data.getStringExtra("mUrl_Img");
                new ASyncTask_ChangePhoto().execute();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountdetail);
        ButterKnife.bind(this);
        ActionBar ac = getSupportActionBar();
        ac.setTitle("Account Detail");
        ac.setDisplayHomeAsUpEnabled(true);

        spf = getSharedPreferences(ParameterCollections.SPF_NAME, MODE_PRIVATE);

        id_user = spf.getString(ParameterCollections.SPF_USER_ID, "");

        new AsyncTask_LoadPorifle().execute();
        new Async_CountryData().execute();
        new Async_CityData().execute();
        new Async_GroupData().execute();
//        ActivityTransition.with(getIntent()).to(img_Profile).start(savedInstanceState);
//        exitTransition = ActivityTransition.with(getIntent()).to(img_Profile).start(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_account, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
//                exitTransition.exit(AccountDetail.this);
                finish();
                break;
            case R.id.action_save:
                String password = ed_password.getText().toString();
                String password_retype = ed_password_retype.getText().toString();
                if(password.equals(password_retype)){
                    new AsyncTask_SendData().execute();
                }else{
                    Toast.makeText(getApplicationContext(), "Password didn't matched" , Toast.LENGTH_LONG).show();
                }
                break;
        }

        return false;
    }

    @Override
    public void onBackPressed() {
//        exitTransition.exit(AccountDetail.this);
        finish();
    }



    private class AsyncTask_LoadPorifle extends AsyncTask<Void,Void,Void>{
        boolean isSukses = false;
        String message = "";
        private CustomProgressDialog pDialog;
        private String idUser, nameUser, urlPhotoUsr= "";
        String name, email, phone, birth, bio, idCity, idCountry= "";
        private Bitmap bitmap_Profile;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new CustomProgressDialog(AccountDetail.this, R.style.SpotsDialogDefault);
            pDialog.setLoaderType(CustomProgressDialog.SPINNING_SQUARE);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            Rest_Adapter adapter = PublicFunctions.initRetrofit();
            try {
                String id = spf.getString(ParameterCollections.SPF_USER_ID,"0");
                Call<Pojo_ResponseAccountUser> call = adapter.get_profile_user(id);

                Response<Pojo_ResponseAccountUser> response = call.execute();

                if(response.isSuccess()){
                    if(response.body() != null){
                       if(response.body().getJsonCode().equals("1")){
                           name = response.body().getData().get(0).getMemberName();
                           email = response.body().getData().get(0).getMemberEmail();
                           phone = response.body().getData().get(0).getMemberPhone();
                           idCity = response.body().getData().get(0).getMemberCity();
                           idCountry = response.body().getData().get(0).getMemberCountry();
                           birth = response.body().getData().get(0).getMemberBirthyear();
                           bio = response.body().getData().get(0).getMemberBio();

                           String url= spf.getString(ParameterCollections.SPF_USER_PHOTO_URL, "");
                           if(url.contains("jpg") || url.contains("png")){
                               try{
                                   bitmap_Profile = Glide.with(getApplicationContext()).
                                           load(url).asBitmap().into(100,100).get();

                                   isSukses = true;
                               }catch (ExecutionException e){

                               }catch (InterruptedException e){

                               }


                           }

                           isSukses =true;
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
                ed_username.setText(name);
                ed_email.setText(email);
                ed_phone.setText(phone);
                ed_bio.setText(bio);
                ed_ttl.setText(birth);

                img_Profile.setImageBitmap(bitmap_Profile);
            }else{
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        }
    }

    private class Async_CountryData extends AsyncTask<Void,Void,Void>{
        List<Rowdata_Country> data_country;
        boolean isSukses= false;

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Void doInBackground(Void... params) {
            Rest_Adapter retrofit = PublicFunctions.initRetrofit();

            Call<PojoCountry> call = retrofit.get_all_country();
            try{
                Response<PojoCountry> response = call.execute();
                if(response.isSuccess()){
                    if(response.body() != null){
                        if(response.body().getData().size() > 0){
                            data_country = new ArrayList<>();
                            for(int i=0; i < response.body().getData().size(); i++){
                                Rowdata_Country item = new Rowdata_Country();
                                item.setId(response.body().getData().get(i).getId());
                                item.setCountryName(response.body().getData().get(i).getCountryName());
                                data_country.add(item);
                                isSukses = true;
                            }
                        }
                    }
                }else{
                    Log.e("Error", "no tuskses");
                }
            }catch (IOException e){

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(isSukses){
                List<String> array_country = new ArrayList<>();
                for(int i=0; i < data_country.size(); i ++){
                    array_country.add(data_country.get(i).getCountryName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        R.layout.spinner_item, array_country);
                adapter.setDropDownViewResource(R.layout.spinner_item);
                spinner_country.setAdapter(adapter);
                spinner_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mCountrySelected = data_country.get(position).getId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        mCountrySelected = data_country.get(0).getId();
                    }
                });

            }else{
                Toast.makeText(getApplicationContext(), "Can not Load Data", Toast.LENGTH_LONG).show();

            }
        }
    }

    private class Async_CityData extends AsyncTask<Void,Void,Void>{

        List<Rowdata_City> data_country, data_hometown;
        boolean isSukses= false;

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Void doInBackground(Void... params) {
            Rest_Adapter retrofit = PublicFunctions.initRetrofit();

            Call<PojoCity> call = retrofit.get_all_city();
            try{
                Response<PojoCity> response = call.execute();
                if(response.isSuccess()){
                    if(response.body() != null){
                        if(response.body().getData().size() > 0){
                            data_country = new ArrayList<>();
                            data_hometown = new ArrayList<>();
                            for(int i=0; i < response.body().getData().size(); i++){
                                Rowdata_City item = new Rowdata_City();
                                item.setId(response.body().getData().get(i).getId());
                                item.setCityName(response.body().getData().get(i).getCityName());
                                data_country.add(item);
                                data_hometown.add(item);
                                isSukses = true;
                            }
                        }
                    }
                }else{
                    Log.e("Error", "no tuskses");
                }
            }catch (IOException e){

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(isSukses){
                List<String> array_city = new ArrayList<>();
                for(int i=0; i < data_country.size(); i ++){
                    array_city.add(data_country.get(i).getCityName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        R.layout.spinner_item, array_city);
                adapter.setDropDownViewResource(R.layout.spinner_item);
                spinner_city.setAdapter(adapter);
                spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mCitySelected = data_country.get(position).getId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        mCitySelected = data_country.get(0).getId();
                    }
                });

                List<String> array_hometown = new ArrayList<>();
                for(int i=0; i < data_hometown.size(); i ++){
                    array_hometown.add(data_hometown.get(i).getCityName());
                }
                ArrayAdapter<String> adapter_hometown = new ArrayAdapter<String>(getApplicationContext(),
                        R.layout.spinner_item, array_hometown);
                adapter_hometown.setDropDownViewResource(R.layout.spinner_item);
                spinner_hometown.setAdapter(adapter_hometown);
                spinner_hometown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mHomeTownSelected= data_hometown.get(position).getId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        mHomeTownSelected = data_hometown.get(0).getId();
                    }
                });

            }else{
                Toast.makeText(getApplicationContext(), "Can not Load Data", Toast.LENGTH_LONG).show();

            }
        }
    }

    private class Async_GroupData extends AsyncTask<Void,Void,Void>{

        List<Rowdata_Group> data_group;
        boolean isSukses= false;

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Void doInBackground(Void... params) {
            Rest_Adapter retrofit = PublicFunctions.initRetrofit();

            Call<PojoGroup> call = retrofit.get_all_group();
            try{
                Response<PojoGroup> response = call.execute();
                if(response.isSuccess()){
                    if(response.body() != null){
                        if(response.body().getData().size() > 0){
                            data_group= new ArrayList<>();
                            for(int i=0; i < response.body().getData().size(); i++){
                                Rowdata_Group item = new Rowdata_Group();
                                item.setId(response.body().getData().get(i).getId());
                                item.setGroupName(response.body().getData().get(i).getGroupName());
                                data_group.add(item);
                                isSukses = true;
                            }
                        }
                    }
                }else{
                    Log.e("Error", "no tuskses");
                }
            }catch (IOException e){

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(isSukses){
                List<String> array_group = new ArrayList<>();
                for(int i=0; i < data_group.size(); i ++){
                    array_group.add(data_group.get(i).getGroupName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        R.layout.spinner_item, array_group);
                adapter.setDropDownViewResource(R.layout.spinner_item);
                spinner_group.setAdapter(adapter);
                spinner_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mGroupSelected = data_group.get(position).getId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        mGroupSelected = data_group.get(0).getId();
                    }
                });

            }else{
                Toast.makeText(getApplicationContext(), "Can not Load Data", Toast.LENGTH_LONG).show();

            }
        }
    }

    private class AsyncTask_SendData extends AsyncTask<Void,Void,Void>{
        private boolean isSukses = false;
        private CustomProgressDialog pDialog;
        private String message = "Something Wrong, Please Try Again";
        private String cName, cEmail, cPhone, cPassword, cGender, cBirth, cBio="";


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new CustomProgressDialog(AccountDetail.this, R.style.SpotsDialogDefault);
            pDialog.setLoaderType(CustomProgressDialog.SPINNING_SQUARE);
            pDialog.show();

            cName = ed_username.getText().toString();
            cEmail= ed_email.getText().toString();
            cPhone= ed_phone.getText().toString();
            cPassword= ed_password.getText().toString();

            if(radio_male.isChecked()){
                cGender = "1";
            }else {
                cGender = "2";
            }

            cBio = ed_bio.getText().toString();
            cBirth= ed_ttl.getText().toString();
        }

        @Override
        protected Void doInBackground(Void... params) {
            Rest_Adapter adapter = PublicFunctions.initRetrofit();
            try{
                Call<PojoResponseInsert> call = adapter.update_person(id_user , cName, cEmail, cPhone, cPassword,
                        mCountrySelected, mCitySelected,mHomeTownSelected, mGroupSelected, cGender,cBirth, cBio);
                Response<PojoResponseInsert> response = call.execute();

                if(response.isSuccess()){
                    if(response.body() != null){
                        if(response.body().getJsonCode().equals("1")){
                            isSukses = true;
                            spf.edit().putString(ParameterCollections.SPF_USER_NAME, cName).commit();
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
                Toast.makeText(getApplicationContext(), "Profile Saved", Toast.LENGTH_LONG).show();
                setResult(RESULT_OK);
                finish();

            }else{
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        }
    }

    private class ASyncTask_ChangePhoto extends AsyncTask<Void,Void,Void>{
        boolean isSukses = false;
        String message = "Something wrong, Please Try Again.";
        private CustomProgressDialog pDialog;
        private String nameUser, urlPhotoUsr= "";
        private Bitmap bitmap_Profile;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new CustomProgressDialog(AccountDetail.this, R.style.SpotsDialogDefault);
            pDialog.setLoaderType(CustomProgressDialog.SPINNING_SQUARE);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            Rest_Adapter adapter = PublicFunctions.initRetrofit();
            try {
                RequestBody _id_user = RequestBody.create(MediaType.parse("text/plain"), id_user);
                File file = new File(url_foto);
                RequestBody body_img = RequestBody.create(MediaType.parse("image/*"), file);
                Call<PojoResponseInsert> call = adapter.update_avatar(_id_user, body_img);
                Response<PojoResponseInsert> response = call.execute();

                if(response.isSuccess()){
                    if(response.body() != null){
                        if(response.body().getJsonCode().equals("1")){
                            isSukses = true;
                            urlPhotoUsr =  url_foto;
//                            urlPhotoUsr = ParameterCollections.BASE_URL_IMG_THUMB + urlPhotoUsr;

                            if(urlPhotoUsr.contains("jpg") || urlPhotoUsr.contains("png")){
                                try{
                                    bitmap_Profile = Glide.with(getApplicationContext()).
                                            load(urlPhotoUsr).asBitmap().into(100,100).get();

                                    isSukses = true;
                                    spf.edit().putString(ParameterCollections.SPF_USER_PHOTO_URL, urlPhotoUsr).commit();
                                }catch (ExecutionException e){

                                }catch (InterruptedException e){

                                }


                            }

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
                img_Profile.setImageBitmap(bitmap_Profile);
            }else{
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
