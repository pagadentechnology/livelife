package id.tech.rcslive.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import id.tech.rcslive.activity.DetailEvent;
import id.tech.rcslive.activity.R;
import id.tech.rcslive.models.Rowdata_EventCalendar;
import id.tech.rcslive.models.Rowdata_EventJoined;

public class RV_Adapter_Calendar extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<Rowdata_EventCalendar> datum;

    public RV_Adapter_Calendar(Context context, List<Rowdata_EventCalendar> datum) {
        this.context = context;
        this.datum = datum;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Rowdata_EventCalendar item = datum.get(position);

        switch (holder.getItemViewType()){
            case 0:
                ViewHolder_WithHeader holder_withHeader = (ViewHolder_WithHeader)holder;
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                try{
                    Date date_Event = df.parse(item.getTvTgl());
                    c.setTime(date_Event);
                }catch(ParseException e){

                }

                SimpleDateFormat df_new = new SimpleDateFormat("EEE, d MMM y");
                SimpleDateFormat df_jam = new SimpleDateFormat("hh:mm");
                String date_Event = df_new.format(c.getTime());
                String hour_Event = df_jam.format(c.getTime());

                holder_withHeader.tv_tgl.setText(date_Event);
                holder_withHeader.tv_jam.setText(hour_Event);
                holder_withHeader.tv_judul.setText(item.getTvJudul());
                holder_withHeader.tv_kategori.setText(item.getTvKategori());
                holder_withHeader.tv_joined.setText(item.getJoined() + " / Min. " + item.getEventMinjoin() + " People");
                holder_withHeader.wrapper.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DetailEvent.class);
                        intent.putExtra("id_event", item.getIdEvent());
                        intent.putExtra("url_photo_event", item.getEventPhoto());
                        intent.putExtra("judul_event", item.getTvJudul());
                        intent.putExtra("alamat_event", item.getTvAlamat());
                        intent.putExtra("tgl_event", item.getTvTgl());
                        intent.putExtra("lat_event", item.getEventLat());
                        intent.putExtra("lon_event", item.getEventLon());
                        intent.putExtra("desc_event", item.getEventDescription());
                        intent.putExtra("event_documentationid", item.getEventDocumentationid());
                        intent.putExtra("member_name", item.getMemberName());
                        intent.putExtra("member_phone", item.getMemberPhone());
                        intent.putExtra("member_photo", item.getMemberPhoto());
                        context.startActivity(intent);
                    }
                });
                break;
            case 1:
                ViewHolder_Content holderNow = (ViewHolder_Content)holder;

                Calendar c_content = Calendar.getInstance();
                SimpleDateFormat df_content  = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                try{
                    Date date_Event_content  = df_content.parse(item.getTvTgl());
                    c_content.setTime(date_Event_content );
                }catch(ParseException e){

                }
                SimpleDateFormat df_jam_content  = new SimpleDateFormat("hh:mm");
                String hour_Event_content  = df_jam_content .format(c_content.getTime());

                holderNow.tv_jam.setText(hour_Event_content );
                holderNow.tv_judul.setText(item.getTvJudul());
                holderNow.tv_kategori.setText(item.getTvKategori());
                holderNow.tv_joined.setText(item.getTotalJoin() + " / Min. " + item.getEventMinjoin() + " People");
                holderNow.wrapper.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DetailEvent.class);
                        intent.putExtra("id_event", item.getIdEvent());
                        intent.putExtra("url_photo_event", item.getEventPhoto());
                        intent.putExtra("judul_event", item.getTvJudul());
                        intent.putExtra("alamat_event", item.getTvAlamat());
                        intent.putExtra("tgl_event", item.getTvTgl());
                        intent.putExtra("lat_event", item.getEventLat());
                        intent.putExtra("lon_event", item.getEventLon());
                        intent.putExtra("desc_event", item.getEventDescription());
                        intent.putExtra("event_documentationid", item.getEventDocumentationid());
                        intent.putExtra("member_name", item.getMemberName());
                        intent.putExtra("member_phone", item.getMemberPhone());
                        intent.putExtra("member_photo", item.getMemberPhoto());

                        context.startActivity(intent);
                    }
                });
                break;
        }
    }

//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//    }

    @Override
    public int getItemCount() {
        return datum.size();
    }

    @Override
    public int getItemViewType(int position) {
        return datum.get(position).getTypeView();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:
                View viewHeader = LayoutInflater.from(context).inflate(R.layout.item_event_calendar_withheader, null);
                RecyclerView.ViewHolder viewHolderHeader = new ViewHolder_WithHeader(viewHeader);
                return viewHolderHeader;
            case 1:
                View view = LayoutInflater.from(context).inflate(R.layout.item_event_calendar, null);
                RecyclerView.ViewHolder viewHolder = new ViewHolder_Content(view);
                return viewHolder;
        }

        return null;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class ViewHolder_Content extends RecyclerView.ViewHolder {
        public View wrapper;
        public ImageView btn_share;
        public TextView  tv_jam,tv_judul,tv_kategori, tv_joined;

        public ViewHolder_Content(View itemView) {
            super(itemView);
            wrapper = (View)itemView.findViewById(R.id.wrapper);
            btn_share = (ImageView)itemView.findViewById(R.id.btn_share);

            tv_jam= (TextView)itemView.findViewById(R.id.tv_jam);
            tv_judul = (TextView)itemView.findViewById(R.id.tv_judul);
            tv_kategori = (TextView)itemView.findViewById(R.id.tv_kategori);
            tv_joined= (TextView)itemView.findViewById(R.id.tv_joined);

        }
    }

    public class ViewHolder_WithHeader extends RecyclerView.ViewHolder {
        public View wrapper;
        public ImageView btn_share;
        public TextView tv_tgl,tv_jam, tv_judul,tv_kategori, tv_joined;

        public ViewHolder_WithHeader(View itemView) {
            super(itemView);
            wrapper = (View)itemView.findViewById(R.id.wrapper);
            btn_share = (ImageView)itemView.findViewById(R.id.btn_share);

            tv_tgl = (TextView)itemView.findViewById(R.id.tv_tgl);
            tv_jam = (TextView)itemView.findViewById(R.id.tv_jam);
            tv_judul = (TextView)itemView.findViewById(R.id.tv_judul);
            tv_kategori = (TextView)itemView.findViewById(R.id.tv_kategori);
            tv_joined= (TextView)itemView.findViewById(R.id.tv_joined);

        }
    }
}
