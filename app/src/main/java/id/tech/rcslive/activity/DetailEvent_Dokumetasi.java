package id.tech.rcslive.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.blastering99.htmlloader.CustomProgressDialog;
import id.tech.rcslive.adapters.RV_Adapter_Event_Dokumentasi;
import id.tech.rcslive.adapters.Rest_Adapter;
import id.tech.rcslive.models.PojoResponseInsert;
import id.tech.rcslive.models.Pojo_Dokumentasi;
import id.tech.rcslive.models.RowData_Dokumentasi;
import id.tech.rcslive.util.ParameterCollections;
import id.tech.rcslive.util.PublicFunctions;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by macbook on 4/1/16.
 */
public class DetailEvent_Dokumetasi extends AppCompatActivity{
    Activity activity;
    String url_file;
    @Bind(R.id.rv)
    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    String event_documentationid;
    @Bind(R.id.btn_refresh)
    ImageView btn_refresh;
    @Bind(R.id.fab)FloatingActionButton fab;
    @OnClick(R.id.fab) void sendData(){
        startActivityForResult(new Intent(getApplicationContext(), GalleryView.class), 111);

    }
    @OnClick(R.id.btn_refresh) void onClickRefresh(){
        new AsyncTask_LoadDokumentasi().execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_rv_white_add_documentasi);
        ButterKnife.bind(this);
        activity = this;
        ActionBar ac = getSupportActionBar();
        ac.setDisplayHomeAsUpEnabled(true);
        ac.setTitle("Event Photos");

        event_documentationid= getIntent().getStringExtra("event_documentationid");
        layoutManager = new GridLayoutManager(getApplicationContext(),3);

        new AsyncTask_LoadDokumentasi().execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            url_file = ParameterCollections.data_selected.get(0).path;

            new AsyncTask_SendData().execute();
        }
    }

    private class AsyncTask_SendData extends AsyncTask<Void,Void,Void>{
        private boolean isSukses = false;
        private CustomProgressDialog pDialog;
        private String message = "Something Wrong, Please Try Again";

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
            try{
                RequestBody documentationid = RequestBody.create(MediaType.parse("text/plain"), event_documentationid);

                File file = new File(url_file);
                RequestBody body_img = RequestBody.create(MediaType.parse("image/*"), file);

                Call<PojoResponseInsert> call = adapter.insert_event_dokumentasi(
                        ParameterCollections.KIND_DOCUMENTATION_INSERT,documentationid, body_img);
                Response<PojoResponseInsert> response = call.execute();

                if(response.isSuccess()){
                    if(response.body() != null){
                        if(response.body().getJsonCode().equals("1")){
                            isSukses = true;
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
                new AsyncTask_LoadDokumentasi().execute();
            }else{
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        }
    }

    private class AsyncTask_LoadDokumentasi extends AsyncTask<Void,Void,Void>{
        String cCode="0";
        List<RowData_Dokumentasi> data;
        Animation animation;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            data = new ArrayList<>();
            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
            animation.setRepeatMode(Animation.INFINITE);

            rv.setVisibility(View.GONE);
            btn_refresh.setVisibility(View.VISIBLE);
            btn_refresh.setAnimation(animation);
        }

        @Override
        protected Void doInBackground(Void... params) {

            Retrofit retrofit = new Retrofit.Builder().baseUrl(ParameterCollections.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
            Rest_Adapter adapter = retrofit.create(Rest_Adapter.class);

            //sementara
//            event_documentationid = "doc20161112";
            Call<Pojo_Dokumentasi> call = adapter.get_all_dokumentasi("documentationbyid_documentation" ,event_documentationid);
            try{
                Response<Pojo_Dokumentasi> response = call.execute();
                if(response.isSuccess()){
                    if(response.body() != null){
                        if(response.body().getJsonCode().equals("1")){
                            if(response.body().getData() != null){
                                for(int i=0; i< response.body().getData().size(); i++){
                                    RowData_Dokumentasi item= new RowData_Dokumentasi();
                                    item.setId(response.body().getData().get(i).getId());
                                    item.setDocumentationPhoto(ParameterCollections.BASE_URL_IMG_THUMB + response.body().getData().get(i).getDocumentationPhoto());
                                    data.add(item);

                                }
                                cCode=response.body().getJsonCode();
                            }
                        }
                    }

                }
            }catch (IOException e){

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(cCode.equals("1")){
                rv.setVisibility(View.VISIBLE);
                btn_refresh.setVisibility(View.GONE);
                btn_refresh.setImageResource(R.drawable.img_transparent);

                adapter = new RV_Adapter_Event_Dokumentasi(getApplicationContext(), data);
                rv.setLayoutManager(layoutManager);
                rv.setAdapter(adapter);
            }else{
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_LONG).show();
                rv.setVisibility(View.GONE);
                btn_refresh.setVisibility(View.VISIBLE);
                animation.cancel();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
