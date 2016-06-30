package id.tech.rcslive.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import id.tech.rcslive.activity.R;
import id.tech.rcslive.models.Rowdata_EventCalendar;

/**
 * Created by macbook on 4/11/16.
 */
public class CustomAdapter_Calendar extends ArrayAdapter<Rowdata_EventCalendar> {
    private Context context;
    private List<Rowdata_EventCalendar> objects;

    public CustomAdapter_Calendar(Context context, int resource, List<Rowdata_EventCalendar> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Rowdata_EventCalendar getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(Rowdata_EventCalendar item) {
        return super.getPosition(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.item_event_calendar_content, null);
        return null;
    }
}
