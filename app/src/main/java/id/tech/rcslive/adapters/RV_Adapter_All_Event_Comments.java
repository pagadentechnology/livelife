package id.tech.rcslive.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import id.tech.rcslive.activity.R;
import id.tech.rcslive.models.Rowdata_Comment;

/**
 * Created by macbook on 4/12/16.
 */
public class RV_Adapter_All_Event_Comments extends RecyclerView.Adapter<RV_Adapter_All_Event_Comments.ViewHolder>{
    private List<Rowdata_Comment> objects;
    private Context context;

    public RV_Adapter_All_Event_Comments(List<Rowdata_Comment> objects, Context context) {
        this.objects = objects;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Rowdata_Comment item = objects.get(position);

//        holder.img_commentor.setImageBitmap();
        holder.tv_commentor.setText(item.getNamaCommentor());
        holder.tv_comment.setText(item.getCommentDescription());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView img_commentor;
        public TextView tv_commentor, tv_comment;

        public ViewHolder(View view){
            super(view);
            img_commentor = (ImageView)view.findViewById(R.id.img_commentor);
            tv_commentor = (TextView)view.findViewById(R.id.tv_commentor);
            tv_comment = (TextView)view.findViewById(R.id.tv_comment);
        }
    }
}
