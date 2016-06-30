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
import id.tech.rcslive.models.Rowdata_City;

public class RV_Adapter_TypeEvent extends RecyclerView.Adapter<RV_Adapter_TypeEvent.ViewHolder>{
    private Activity activity;
    private Context context;
    private onSelectedTypeEventListener listener;

    public RV_Adapter_TypeEvent(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public interface onSelectedTypeEventListener{
        public void selectedType(String id);
    }

    private void selectedType(String id){
        listener.selectedType(id);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if(position ==0){
            holder.tv.setText("Irregular");
        }else{
            holder.tv.setText("Regular");
        }

        try{
            listener = (onSelectedTypeEventListener) activity;

        }catch (ClassCastException e){

        }
        holder.wrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position ==0){
                    selectedType("Irregular");
                }else{
                    selectedType("Regular");
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return 2;
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
