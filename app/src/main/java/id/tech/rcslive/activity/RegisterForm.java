package id.tech.rcslive.activity;

import android.content.Intent;
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
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.tech.rcslive.adapters.Rest_Adapter;
import id.tech.rcslive.models.PojoCity;
import id.tech.rcslive.models.PojoCountry;
import id.tech.rcslive.models.Rowdata_City;
import id.tech.rcslive.models.Rowdata_Country;
import id.tech.rcslive.util.ParameterCollections;
import id.tech.rcslive.util.PublicFunctions;
import retrofit.Call;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by macbook on 5/12/16.
 */
public class RegisterForm extends AppCompatActivity {
    @Bind(R.id.ed_username)EditText ed_username;
    @Bind(R.id.ed_email)EditText ed_email;
    @Bind(R.id.ed_phone)EditText ed_phone;
    @Bind(R.id.ed_password)EditText ed_password;
    @Bind(R.id.ed_password_retype)EditText ed_password_retype;
    @Bind(R.id.spinner_country)
    Spinner spinner_country;
    @Bind(R.id.spinner_city)
    Spinner spinner_city;

    String url_foto;
    String _ed_username, _ed_email, _ed_phone, _ed_password,_ed_password_retype;
    String mCountrySelected,mCitySelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

//        spf = getSharedPreferences(ParameterCollections.SPF_NAME, MODE_PRIVATE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Register");

        boolean from_fb = getIntent().getBooleanExtra("register_fb", false);
        if(from_fb){
            _ed_username = getIntent().getStringExtra("name");
            _ed_email = getIntent().getStringExtra("email");
            url_foto = getIntent().getStringExtra("url_foto");

            ed_username.setText(_ed_username);
            ed_email.setText(_ed_email);
        }

        new Async_CountryData().execute();
        new Async_CityData().execute();
//        Log.e("Name = ", nama);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_register, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_refresh:
                new Async_CountryData().execute();
                new Async_CityData().execute();
                break;

            case R.id.action_register:
                _ed_username = ed_username.getText().toString();
                _ed_email = ed_email.getText().toString();
                _ed_phone = ed_phone.getText().toString();
                _ed_password = ed_password.getText().toString();
                _ed_password_retype = ed_password_retype.getText().toString();

                if(!_ed_username.isEmpty() && !_ed_email.isEmpty() && !_ed_phone.isEmpty() && !_ed_password.isEmpty()
                        && !_ed_password_retype.isEmpty()){
                    if(!_ed_password.equals(_ed_password_retype)){
                        ed_password_retype.setError("Password did not match");
                    }else{
                        Intent intent = new Intent(getApplicationContext(), RegisterForm_ProfilePicture.class);
                        intent.putExtra("url_foto", url_foto);
                        intent.putExtra("_ed_username", _ed_username);
                        intent.putExtra("_ed_email", _ed_email);
                        intent.putExtra("_ed_phone", _ed_phone);
                        intent.putExtra("_ed_password", _ed_password);
                        intent.putExtra("_country", mCountrySelected);
                        intent.putExtra("_city", mCitySelected);
                        startActivity(intent);
                        finish();
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "Please Fill All Form", Toast.LENGTH_LONG).show();
                }


//                if(ed_Password.getText().toString().equals("") || ed_Username.getText().toString().equals("")){
//
//                    Toast.makeText(getApplicationContext(), "Please Fill Username & Password", Toast.LENGTH_LONG).show();
//                }else{
//                    new ASyncTask_Login().execute();
//                }

                break;
        }
        return super.onOptionsItemSelected(item);
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

        List<Rowdata_City> data_country;
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
                            for(int i=0; i < response.body().getData().size(); i++){
                                Rowdata_City item = new Rowdata_City();
                                item.setId(response.body().getData().get(i).getId());
                                item.setCityName(response.body().getData().get(i).getCityName());
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

            }else{
                Toast.makeText(getApplicationContext(), "Can not Load Data", Toast.LENGTH_LONG).show();

            }
        }
    }
}
