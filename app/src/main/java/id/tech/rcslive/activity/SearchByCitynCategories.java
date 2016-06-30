package id.tech.rcslive.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.tech.rcslive.adapters.RV_Adapter_Highlight;
import id.tech.rcslive.adapters.RV_Adapter_SearchCategories;
import id.tech.rcslive.adapters.RV_Adapter_SearchCity;
import id.tech.rcslive.adapters.RV_Adapter_TypeEvent;
import id.tech.rcslive.adapters.Rest_Adapter;
import id.tech.rcslive.models.PojoCategories;
import id.tech.rcslive.models.PojoCity;
import id.tech.rcslive.models.PojoEventRegular;
import id.tech.rcslive.models.Pojo_EventHighlight;
import id.tech.rcslive.models.Rowdata_Categories;
import id.tech.rcslive.models.Rowdata_City;
import id.tech.rcslive.models.Rowdata_EventHighlight;
import id.tech.rcslive.models.Rowdata_EventRegular;
import id.tech.rcslive.util.ParameterCollections;
import id.tech.rcslive.util.PublicFunctions;
import retrofit.Call;
import retrofit.Response;

/**
 * Created by macbook on 4/5/16.
 */
public class SearchByCitynCategories extends AppCompatActivity implements RV_Adapter_SearchCity.onSelectedRegionListener,
RV_Adapter_SearchCategories.OnSelectedCategoriesListener, RV_Adapter_TypeEvent.onSelectedTypeEventListener{
    @Bind(R.id.rv)RecyclerView rv;
    @Bind(R.id.btn_refresh)ImageView btn_refresh;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter, adapter_result;
    Activity activity;
    String selected_region, selected_categories, selected_type_event;
    String id_user;

    @OnClick(R.id.btn_refresh) void onRefresh(){
        new ASyncTask_GetAllCity().execute();
    }

    @Override
    public void selectedRegion(String id) {
        selected_region = id;
//        new ASyncTask_GetTypeEvent().execute();
        new ASyncTask_GetAllCategories().execute();
    }

    @Override
    public void selectedType(String id) {
        selected_type_event = id;
//        new ASyncTask_GetAllCategories().execute();
    }

    @Override
    public void selectedCategories(String id) {
        selected_type_event = "Regular";
//        selected_categories = id;
        selected_categories = "1";
        new ASyncTask_GetEventResult().execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_rv);
        ButterKnife.bind(this);
        activity = this;
        ActionBar ac = getSupportActionBar();
        ac.setDisplayHomeAsUpEnabled(true);
        ac.setTitle("Search Regular Event ");

        SharedPreferences spf = getSharedPreferences(ParameterCollections.SPF_NAME, Context.MODE_PRIVATE);
        id_user = spf.getString(ParameterCollections.SPF_USER_ID, "");

        new ASyncTask_GetAllCity().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private class ASyncTask_GetAllCity extends AsyncTask<Void,Void,Void> {
        String cCode = "0";
        List<Rowdata_City> data;
        Animation animation;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            layoutManager = new GridLayoutManager(getApplicationContext(), 1);

            data = new ArrayList<>();

            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
            animation.setRepeatMode(Animation.INFINITE);

            rv.setVisibility(View.GONE);
            btn_refresh.setVisibility(View.VISIBLE);
            btn_refresh.setAnimation(animation);
        }

        @Override
        protected Void doInBackground(Void... params) {
            Rest_Adapter adapter = PublicFunctions.initRetrofit();

            Call<PojoCity> call = adapter.get_all_city();

            try {
                Thread.sleep(1000);
                Response<PojoCity> response_event = call.execute();
                if (response_event.isSuccess()) {
                    if (response_event.body().getJsonCode().equals("1")) {
                        if (response_event.body().getData() != null) {
                            for (int i = 0; i < response_event.body().getData().size(); i++) {
                                Rowdata_City item = new Rowdata_City();
                                item.setId(response_event.body().getData().get(i).getId());
                                item.setCityName(response_event.body().getData().get(i).getCityName());
                                data.add(item);

                                Log.e("id_event = ", response_event.body().getData().get(i).getId());
                            }
                        }
                    }
                    cCode = "1";
                } else {
                    cCode = "0";
                }
            } catch (IOException e) {

            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (cCode.equals("1")) {
                rv.setVisibility(View.VISIBLE);
                btn_refresh.setVisibility(View.GONE);
                btn_refresh.setImageResource(R.drawable.img_transparent);

                adapter = new RV_Adapter_SearchCity(activity, getApplicationContext(), data);
                rv.setLayoutManager(layoutManager);
                rv.setAdapter(adapter);

            } else {
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_LONG).show();
                rv.setVisibility(View.GONE);
                btn_refresh.setVisibility(View.VISIBLE);
                animation.cancel();
            }
        }

    }

    private class ASyncTask_GetTypeEvent extends AsyncTask<Void,Void,Void> {
        String cCode = "0";
        Animation animation;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            layoutManager = new GridLayoutManager(getApplicationContext(), 1);

            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
            animation.setRepeatMode(Animation.INFINITE);

            rv.setVisibility(View.GONE);
            btn_refresh.setVisibility(View.VISIBLE);
            btn_refresh.setAnimation(animation);
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Thread.sleep(1000);
                cCode="1";
            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (cCode.equals("1")) {
                rv.setVisibility(View.VISIBLE);
                btn_refresh.setVisibility(View.GONE);
                btn_refresh.setImageResource(R.drawable.img_transparent);

                adapter = new RV_Adapter_TypeEvent(activity, getApplicationContext());
                rv.setLayoutManager(layoutManager);
                rv.setAdapter(adapter);

            } else {
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_LONG).show();
                rv.setVisibility(View.GONE);
                btn_refresh.setVisibility(View.VISIBLE);
                animation.cancel();
            }
        }

    }

    private class ASyncTask_GetAllCategories extends AsyncTask<Void,Void,Void> {
        String cCode = "0";
        List<Rowdata_Categories> data;
        Animation animation;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            layoutManager = new GridLayoutManager(getApplicationContext(), 1);

            data = new ArrayList<>();

            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
            animation.setRepeatMode(Animation.INFINITE);

            rv.setVisibility(View.GONE);
            btn_refresh.setVisibility(View.VISIBLE);
            btn_refresh.setAnimation(animation);
        }

        @Override
        protected Void doInBackground(Void... params) {
            Rest_Adapter adapter = PublicFunctions.initRetrofit();

            Call<PojoCategories> call = adapter.get_all_categories();

            try {
                Thread.sleep(1000);
                Response<PojoCategories> response_event = call.execute();
                if (response_event.isSuccess()) {
                    if (response_event.body().getJsonCode().equals("1")) {
                        if (response_event.body().getData() != null) {
                            for (int i = 0; i < response_event.body().getData().size(); i++) {
                                Rowdata_Categories item = new Rowdata_Categories();
                                item.setId(response_event.body().getData().get(i).getId());
                                item.setCategoriesName(response_event.body().getData().get(i).getCategoriesName());

                                if(item.getId().equals("1")){
                                    data.add(item);
                                }

                                Log.e("id_event = ", response_event.body().getData().get(i).getId());
                            }
                        }
                    }
                    cCode = "1";
                } else {
                    cCode = "0";
                }
            } catch (IOException e) {

            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (cCode.equals("1")) {
                rv.setVisibility(View.VISIBLE);
                btn_refresh.setVisibility(View.GONE);
                btn_refresh.setImageResource(R.drawable.img_transparent);

                adapter = new RV_Adapter_SearchCategories(activity, getApplicationContext(), data);
                rv.setLayoutManager(layoutManager);
                rv.setAdapter(adapter);
            } else {
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_LONG).show();
                rv.setVisibility(View.GONE);
                btn_refresh.setVisibility(View.VISIBLE);
                animation.cancel();
            }
        }

    }

    private class ASyncTask_GetEventResult extends AsyncTask<Void,Void,Void> {
        String cCode = "0";
        List<Rowdata_EventHighlight> data;
        Animation animation;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            layoutManager = new GridLayoutManager(getApplicationContext(), 1);

            data = new ArrayList<>();

            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
            animation.setRepeatMode(Animation.INFINITE);

            rv.setVisibility(View.GONE);
            btn_refresh.setVisibility(View.VISIBLE);
            btn_refresh.setAnimation(animation);
        }

        @Override
        protected Void doInBackground(Void... params) {
            Rest_Adapter adapter = PublicFunctions.initRetrofit();
            Call<PojoEventRegular> call = adapter.get_result_events_regular(selected_region, id_user);

            try{
                Thread.sleep(1000);
                Response<PojoEventRegular> response_event = call.execute();
                if(response_event.isSuccess()){
                    if(response_event.body().getJsonCode().equals("1")){
                        if(response_event.body().getData() != null){
                            for(int i=0; i< response_event.body().getData().size(); i++){
                                Rowdata_EventHighlight item = new Rowdata_EventHighlight();
                                item.setIdEvent(response_event.body().getData().get(i).getId());
                                item.setTvTgl(response_event.body().getData().get(i).getDaySchedule());
                                item.setTvJudul(response_event.body().getData().get(i).getEventTitle());
                                item.setTvAlamat(response_event.body().getData().get(i).getEventLocation());
                                item.setTvKategori(response_event.body().getData().get(i).getCategoriesName());
                                item.setEventPhoto(response_event.body().getData().get(i).getEventPhoto());
                                item.setEventLat(response_event.body().getData().get(i).getEventLat());
                                item.setEventLon(response_event.body().getData().get(i).getEventLon());
                                item.setEventMinjoin(response_event.body().getData().get(i).getEventMinjoin());
                                item.setEventDescription(response_event.body().getData().get(i).getEventDescription());
                                item.setEventDocumentationid(response_event.body().getData().get(i).getEventDocumentationid());
                                item.setMemberName(response_event.body().getData().get(i).getMemberName());
                                item.setMemberPhone(response_event.body().getData().get(i).getMemberPhone());
                                item.setMemberPhoto(response_event.body().getData().get(i).getMemberPhoto());
                                item.setTotalJoin(response_event.body().getData().get(i).getTotalJoin());

                                item.setCek_exists(response_event.body().getData().get(i).getCek_exists());
                                data.add(item);

                                cCode = "1";
                                Log.e("id_event = ", response_event.body().getData().get(i).getId());
                            }
                        }
                    }
                    cCode="1";
                }else{
                    cCode="0";
                }
            }catch (IOException e){

            }catch (Exception e){

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (cCode.equals("1")) {
                rv.setVisibility(View.VISIBLE);
                btn_refresh.setVisibility(View.GONE);
                btn_refresh.setImageResource(R.drawable.img_transparent);

                adapter_result = new RV_Adapter_Highlight(getApplicationContext(), data);
                rv.setLayoutManager(layoutManager);
                rv.setAdapter(adapter_result);
            } else {
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
                finish();
                break;
        }
        return true;
    }
}
