package id.tech.rcslive.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

import id.tech.rcslive.activity.DetailEvent;
import id.tech.rcslive.activity.R;
import id.tech.rcslive.models.Rowdata_EventHighlight;
import id.tech.rcslive.models.Rowdata_EventUserJoined;

public class RV_Adapter_Event_Joined extends RecyclerView.Adapter<RV_Adapter_Event_Joined.ViewHolder>{
    private Context context;
    private List<Rowdata_EventUserJoined> datum;

    public RV_Adapter_Event_Joined(Context context, List<Rowdata_EventUserJoined> datum) {
        this.context = context;
        this.datum = datum;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Rowdata_EventUserJoined item = datum.get(position);

        holder.tv.setText(item.getUserjoinedName());

        SimpleTarget target  = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                holder.img.setImageBitmap(resource);
            }
        };

        Glide.with(context).load(item.getUserjoinedPhoto()).asBitmap().placeholder(R.drawable.img_empty).into(target);

    }

    @Override
    public int getItemCount() {
        return datum.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_img_joined, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.img);
            tv = (TextView)itemView.findViewById(R.id.tv);

        }
    }
}
