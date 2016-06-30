package id.tech.rcslive.fragment;

/**
 * Created by RebelCreative-A1 on 21/03/2016.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

//import dev.dworks.libs.astickyheader.SimpleSectionedListAdapter;
//import dev.dworks.libs.astickyheader.ui.PinnedSectionListView;
import id.tech.rcslive.activity.R;
import id.tech.rcslive.adapters.CustomAdapter_Calendar;
import id.tech.rcslive.adapters.RV_Adapter_Calendar;
import id.tech.rcslive.adapters.RV_Adapter_Joined;
import id.tech.rcslive.adapters.Rest_Adapter;
import id.tech.rcslive.models.Pojo_EventCalendar;
import id.tech.rcslive.models.Pojo_EventHighlight;
import id.tech.rcslive.models.Rowdata_EventCalendar;
import id.tech.rcslive.models.Rowdata_EventJoined;
import id.tech.rcslive.util.ParameterCollections;
import id.tech.rcslive.util.PublicFunctions;
import retrofit.Call;
import retrofit.Response;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Events_Calendar extends Fragment{
    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    SharedPreferences spf;
    ImageView btn_refresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_rv_calendar, null);
        rv = (RecyclerView)view.findViewById(R.id.rv);

        spf = getActivity().getSharedPreferences(ParameterCollections.SPF_NAME, Context.MODE_PRIVATE);
        btn_refresh = (ImageView)view.findViewById(R.id.btn_refresh);
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask_GetAll_EventByCalendar().execute();
            }
        });

        new AsyncTask_GetAll_EventByCalendar().execute();
        return view;
    }

    private class AsyncTask_GetAll_EventByCalendar extends AsyncTask<Void,Void,Void>{
        String cCode="0";
        List<Rowdata_EventCalendar> data;
        CustomAdapter_Calendar adapter_Calendar;
        Animation animation;
        boolean isSukses = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            data = new ArrayList<>();
            animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
            animation.setRepeatMode(Animation.INFINITE);

            rv.setVisibility(View.GONE);
            btn_refresh.setVisibility(View.VISIBLE);
            btn_refresh.setAnimation(animation);
        }

        @Override
        protected Void doInBackground(Void... params) {
            Rest_Adapter adapter= PublicFunctions.initRetrofit();

            Call<Pojo_EventCalendar> call = adapter.get_all_events_calendar(ParameterCollections.KIND_EVENT,
                    "ON");

            try{
                Response<Pojo_EventCalendar> response = call.execute();
                Thread.sleep(1000);
                if(response.isSuccess()){
                    if(response.body().getJsonCode().equals("1")){
                        for(int i=0; i < response.body().getData().size(); i++){
                            isSukses = true;
                            Rowdata_EventCalendar item = new Rowdata_EventCalendar();
                            item.setIdEvent(response.body().getData().get(i).getId());
                            item.setTvTgl(response.body().getData().get(i).getDeadline());
                            item.setTvJudul(response.body().getData().get(i).getEventTitle());
                            item.setTvAlamat(response.body().getData().get(i).getEventLocation());
                            item.setTvKategori(response.body().getData().get(i).getCategoriesName());
                            item.setEventPhoto(response.body().getData().get(i).getEventPhoto());

                            //blum ada Joined
                            item.setTotalJoin(response.body().getData().get(i).getTotalJoin());
                            item.setEventMinjoin(response.body().getData().get(i).getEventMinjoin());
                            item.setEventDocumentationid(response.body().getData().get(i).getEventDocumentationid());
                            item.setMemberName(response.body().getData().get(i).getMemberName());
                            item.setMemberPhone(response.body().getData().get(i).getMemberPhone());
                            item.setMemberPhoto(response.body().getData().get(i).getMemberPhoto());

                            String tgl_spf = spf.getString(ParameterCollections.SPF_SAME_DATE, "");

                            Calendar c_spf = Calendar.getInstance();
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                            try{
                                Date date_Event = df.parse(tgl_spf);
                                c_spf.setTime(date_Event);
                            }catch(ParseException e){

                            }

                            Calendar c_event = Calendar.getInstance();
                            SimpleDateFormat df_event = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                            try{
                                Date date_Event = df_event.parse(response.body().getData().get(i).getDeadline());
                                c_event.setTime(date_Event);
                            }catch(ParseException e){

                            }

                            boolean isSameDay = c_spf.get(Calendar.YEAR) == c_event.get(Calendar.YEAR) &&
                                    c_spf.get(Calendar.DAY_OF_YEAR) == c_event.get(Calendar.DAY_OF_YEAR);


//                            if(tgl_spf.equals("") || !tgl_spf.equals(response.body().getData().get(i).getDeadline())){
                            if(!isSameDay){
                                item.setTypeView(0);

                            }else{
                                item.setTypeView(1);
                            }
                            spf.edit().putString(ParameterCollections.SPF_SAME_DATE, response.body().getData().get(i).getDeadline()).commit();

                            data.add(item);
                        }
                    }
                }
            }catch (IOException e){

            }catch (Exception e){

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(isSukses){
                rv.setVisibility(View.VISIBLE);
                btn_refresh.setVisibility(View.GONE);
                btn_refresh.setImageResource(R.drawable.img_transparent);
                layoutManager = new GridLayoutManager(getContext(),1);
                adapter = new RV_Adapter_Calendar(getActivity(), data);
                rv.setLayoutManager(layoutManager);
                rv.setAdapter(adapter);
            }else{
//                Toast.makeText(getActivity(), "No Event on Calendar Yet", Toast.LENGTH_LONG).show();
                rv.setVisibility(View.GONE);
                btn_refresh.setVisibility(View.VISIBLE);
                animation.cancel();
            }

        }
    }
}
