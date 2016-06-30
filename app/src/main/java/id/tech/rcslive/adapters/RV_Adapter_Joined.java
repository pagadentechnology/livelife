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
import id.tech.rcslive.activity.DetailEvent_Joined;
import id.tech.rcslive.activity.R;
import id.tech.rcslive.models.Rowdata_EventHighlight;
import id.tech.rcslive.models.Rowdata_EventJoined;
import id.tech.rcslive.util.ParameterCollections;

public class RV_Adapter_Joined extends RecyclerView.Adapter<RV_Adapter_Joined.ViewHolder>{
    private Context context;
    private List<Rowdata_EventJoined> datum;

    public RV_Adapter_Joined(Context context, List<Rowdata_EventJoined> datum) {
        this.context = context;
        this.datum = datum;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Rowdata_EventJoined item = datum.get(position);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try{
            Date date_Event = df.parse(item.getTvTgl());
            c.setTime(date_Event);
        }catch(ParseException e){

        }

        SimpleDateFormat df_new = new SimpleDateFormat("EEE, d MMM y");
        SimpleDateFormat df_jam = new SimpleDateFormat("hh:mm");
        String date_Event = df_new.format(c.getTime());
        String hour_Event = df_jam.format(c.getTime());

//        holder.tv_tgl.setText(date_Event);
        holder.tv_tgl.setText(item.getTvTgl());

        holder.tv_judul.setText(item.getTvJudul());
        holder.tv_alamat.setText(item.getTvAlamat());
        Glide.with(context).load(ParameterCollections.BASE_URL_IMG_THUMB + item.getEventPhoto()).placeholder(R.drawable.img_empty).into(holder.img);
        holder.wrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailEvent_Joined.class);
                intent.putExtra("id_event", item.getIdEvent());
                intent.putExtra("url_photo_event", ParameterCollections.BASE_URL_IMG_THUMB + item.getEventPhoto());
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

                intent.putExtra("joinID", item.getJoinID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datum.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_event_joined, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View wrapper;
        public ImageView img, btn_share;
        public TextView tv_tgl, tv_judul,tv_alamat;

        public ViewHolder(View itemView) {
            super(itemView);
            wrapper = (View)itemView.findViewById(R.id.wrapper);
            img = (ImageView)itemView.findViewById(R.id.img);
            btn_share = (ImageView)itemView.findViewById(R.id.btn_share);

            tv_tgl = (TextView)itemView.findViewById(R.id.tv_tgl);
            tv_judul = (TextView)itemView.findViewById(R.id.tv_judul);
            tv_alamat = (TextView)itemView.findViewById(R.id.tv_alamat);


        }
    }
}
