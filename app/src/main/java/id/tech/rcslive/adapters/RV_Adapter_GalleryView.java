package id.tech.rcslive.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.tech.rcslive.activity.R;
import id.tech.rcslive.models.RowDataGallery;
import id.tech.rcslive.util.ParameterCollections;

/**
 * Created by macbook on 4/22/16.
 */
public class RV_Adapter_GalleryView extends RecyclerView.Adapter<RV_Adapter_GalleryView.ViewHolder> {
    private Activity activity_adapter;
    private Context context_adapter;
    private List<RowDataGallery> data, selected;
    private onSelectedImageInterface listener;

    public RV_Adapter_GalleryView(Activity activity_adapter, Context context_adapter, List<RowDataGallery> data) {
        this.activity_adapter = activity_adapter;
        this.context_adapter = context_adapter;
        this.data = data;
        this.selected = selected;
        selected = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final RowDataGallery item = data.get(position);

        try {
            listener = (onSelectedImageInterface) activity_adapter;
        } catch (ClassCastException e) {

        }

        holder.img.setImageBitmap(item.bitmap);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedImage(position);

            }
        });

    }

    public interface onSelectedImageInterface {
        public void changeActionbarTitle(int size_selected);
    }

    private void selectedImage(int positiion) {

        selected.add(data.get(positiion));
        ParameterCollections.data_selected = selected;
        listener.changeActionbarTitle(selected.size());
        data.remove(positiion);
        notifyDataSetChanged();
        activity_adapter.setResult(Activity.RESULT_OK);
        activity_adapter.finish();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context_adapter).inflate(R.layout.item_gallery, null);

        ViewHolder viewHolder = new ViewHolder(v, activity_adapter);
        return viewHolder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;

        private Activity activity;

        public ViewHolder(View v, Activity activity) {
            super(v);
            img = (ImageView) v.findViewById(R.id.img);
            this.activity = activity;
        }

    }
}
