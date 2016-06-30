package id.tech.rcslive.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.library.bubbleview.BubbleTextVew;

import java.util.List;

import id.tech.rcslive.activity.R;
import id.tech.rcslive.models.Rowdata_Categories;
import id.tech.rcslive.models.Rowdata_City;

public class RV_Adapter_SearchCategories extends RecyclerView.Adapter<RV_Adapter_SearchCategories.ViewHolder>{
    private Activity activity;
    private Context context;
    private List<Rowdata_Categories> datum;
    private OnSelectedCategoriesListener listener;

    public RV_Adapter_SearchCategories(Activity activity,Context context, List<Rowdata_Categories> datum) {
        this.activity = activity;
        this.context = context;
        this.datum = datum;
    }

    public interface OnSelectedCategoriesListener{
        public void selectedCategories(String id);
    }

    private void pilihCategory(String id){
        listener.selectedCategories(id);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Rowdata_Categories item = datum.get(position);

        try{
            listener = (OnSelectedCategoriesListener) activity;
        }catch (ClassCastException e){

        }
        holder.tv.setText(item.getCategoriesName());

        holder.wrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihCategory(item.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return datum.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_categories, null);
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
