package id.tech.rcslive.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.library.bubbleview.BubbleTextVew;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import id.tech.rcslive.activity.DetailEvent;
import id.tech.rcslive.activity.R;
import id.tech.rcslive.models.Rowdata_City;
import id.tech.rcslive.models.Rowdata_EventHighlight;

public class RV_Adapter_SearchCity extends RecyclerView.Adapter<RV_Adapter_SearchCity.ViewHolder>{
    private Activity activity;
    private Context context;
    private List<Rowdata_City> datum;
    private onSelectedRegionListener listener;

    public RV_Adapter_SearchCity(Activity activity, Context context, List<Rowdata_City> datum) {
        this.activity = activity;
        this.context = context;
        this.datum = datum;
    }

    public interface onSelectedRegionListener{
        public void selectedRegion(String id);
    }

    private void selectedIdRegions(String id){
        listener.selectedRegion(id);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Rowdata_City item = datum.get(position);

        holder.tv.setText(item.getCityName());
        try{
            listener = (onSelectedRegionListener) activity;

        }catch (ClassCastException e){

        }
        holder.wrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedIdRegions(item.getId());
            }
        });


    }

    @Override
    public int getItemCount() {
        return datum.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_city, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View wrapper;
        public BubbleTextVew tv;

        public ViewHolder(View itemView) {
            super(itemView);
            wrapper = (View)itemView.findViewById(R.id.wrapper);

            tv = (BubbleTextVew)itemView.findViewById(R.id.tv);

        }
    }
}
