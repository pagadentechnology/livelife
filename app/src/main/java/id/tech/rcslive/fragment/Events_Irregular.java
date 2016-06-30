package id.tech.rcslive.fragment;

/**
 * Created by RebelCreative-A1 on 21/03/2016.
 */

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import id.tech.rcslive.activity.R;
import id.tech.rcslive.adapters.RV_Adapter_Highlight;
import id.tech.rcslive.adapters.RV_Adapter_Irregular;
import id.tech.rcslive.adapters.Rest_Adapter;
import id.tech.rcslive.models.PojoEventIrregular;
import id.tech.rcslive.models.Pojo_EventHighlight;
import id.tech.rcslive.models.Rowdata_EventHighlight;
import id.tech.rcslive.models.Rowdata_EventIrregular;
import id.tech.rcslive.util.ParameterCollections;
import id.tech.rcslive.util.PublicFunctions;
import retrofit.Call;
import retrofit.Response;

public class Events_Irregular extends Fragment {
    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ImageView btn_refresh;
    String id_user;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_rv, null);
        rv = (RecyclerView)view.findViewById(R.id.rv);

        SharedPreferences spf = getActivity().getSharedPreferences(ParameterCollections.SPF_NAME, Context.MODE_PRIVATE);
        id_user = spf.getString(ParameterCollections.SPF_USER_ID, "");

        layoutManager = new GridLayoutManager(getContext(),1);

        btn_refresh = (ImageView)view.findViewById(R.id.btn_refresh);
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ASyncTask_GetAllEvent().execute();
            }
        });

        new ASyncTask_GetAllEvent().execute();
//        List<Rowdata_EventHighlight> data = new ArrayList<>();
//        Rowdata_EventHighlight item = new Rowdata_EventHighlight();
//        item.setIdEvent("1");
//        item.setTvTgl("Mon, 1 April 2016");
//        item.setTvJudul("Kumpul Kangen Angkatan 2010");
//        item.setTvAlamat("Jl. Soekarno Hatta no. 45, Gedung Mahaka Square. Jakarta Timur");
//        item.setTvKategori("#Reuni");
//        item.setJoined("58 People Joined");
//        data.add(item);
//
//        Rowdata_EventHighlight item2 = new Rowdata_EventHighlight();
//        item2.setIdEvent("2");
//        item2.setTvTgl("Tue, 12 April 2016");
//        item2.setTvJudul("Latihan Futsal Bulanan");
//        item2.setTvAlamat("Futsal Celebrity, Kelapa Gading. Jakarta Timur");
//        item2.setTvKategori("#Sport");
//        item2.setJoined("20 People Joined");
//        data.add(item2);

        return view;
    }

    private class ASyncTask_GetAllEvent extends AsyncTask<Void,Void,Void> {
        String cCode="0";
        List<Rowdata_EventIrregular> data;
        Animation animation;

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
            Rest_Adapter adapter = PublicFunctions.initRetrofit();

            Call<PojoEventIrregular> call = adapter.get_all_events_irregular(id_user);

            try{
//                Thread.sleep(1000);
                Response<PojoEventIrregular> response_event = call.execute();
                if(response_event.isSuccess()){
                    if(response_event.body().getJsonCode().equals("1")){
                        if(response_event.body().getData() != null){
                            for(int i=0; i< response_event.body().getData().size(); i++){
                                Rowdata_EventIrregular item = new Rowdata_EventIrregular();
                                item.setId(response_event.body().getData().get(i).getId());
                                item.setDeadline(response_event.body().getData().get(i).getDateConverted());
                                item.setEventTitle(response_event.body().getData().get(i).getEventTitle());
                                item.setEventLocation(response_event.body().getData().get(i).getEventLocation());
                                item.setCategoriesName(response_event.body().getData().get(i).getCategoriesName());
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
            if(cCode.equals("1")){
                rv.setVisibility(View.VISIBLE);
                btn_refresh.setVisibility(View.GONE);
                btn_refresh.setImageResource(R.drawable.img_transparent);

                adapter = new RV_Adapter_Irregular(getContext(), data);
                rv.setLayoutManager(layoutManager);
                rv.setAdapter(adapter);
            }else{
//                Toast.makeText(getActivity(), "No Hightlight Event Yet", Toast.LENGTH_LONG).show();
                rv.setVisibility(View.GONE);
                btn_refresh.setVisibility(View.VISIBLE);
                animation.cancel();
            }
        }
    }


}
