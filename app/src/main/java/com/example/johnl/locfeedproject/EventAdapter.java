package com.example.johnl.locfeedproject;

/**
 * Created by johnl on 4/5/2017.
 */
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EventAdapter extends ArrayAdapter<EventModel> implements View.OnClickListener{

    private ArrayList<EventModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView header;
        TextView description;
        TextView user;
        TextView userRep;
        TextView timeStart;
        TextView timeEnd;
    }

    public EventAdapter(ArrayList<EventModel> data, Context context) {
        super(context, R.layout.row_event, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        EventModel eventModel =(EventModel)object;

        switch (v.getId())
        {
            case R.id.description:
                Snackbar.make(v, "Description: " + eventModel.getDescription(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        EventModel eventModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_event, parent, false);
            viewHolder.header = (TextView) convertView.findViewById(R.id.name);
            viewHolder.description = (TextView) convertView.findViewById(R.id.description);
            viewHolder.user = (TextView) convertView.findViewById(R.id.user);
            viewHolder.userRep = (TextView) convertView.findViewById(R.id.userRep);
            viewHolder.timeStart = (TextView) convertView.findViewById(R.id.timeStart);
            viewHolder.timeEnd = (TextView) convertView.findViewById(R.id.timeEnd);
            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.header.setText(eventModel.getName());
        viewHolder.description.setText(eventModel.getDescription());
        viewHolder.user.setText(eventModel.getUser());
        viewHolder.userRep.setText("  " + eventModel.getUserRep());
        viewHolder.timeStart.setText(eventModel.getTimeStart());
        viewHolder.timeEnd.setText(eventModel.getTimeEnd());
        viewHolder.description.setOnClickListener(this);
        viewHolder.description.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}