package com.example.borek.myapplication.Function;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.borek.myapplication.R;

import java.util.ArrayList;

/**
 * Created by borek on 13/01/19.
 */

public class HistoricalDataListAdapter extends ArrayAdapter<HistoricalData> {
    private static final String TAG = "HistoricalDataListAdapter";

    private Context mContext;
    int mResource;

    public HistoricalDataListAdapter(Context context, int resource, ArrayList<HistoricalData> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the workout information
        String caloriesBurnt = getItem(position).getCaloriesBurnt();
        String duration = getItem(position).getDuration();
        String distance = getItem(position).getDistance();
        String dateTime = getItem(position).getDateTime();

        // Create new object with the information
        HistoricalData run = new HistoricalData(caloriesBurnt, duration, distance, dateTime);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView textViewCaloriesBurntContent = (TextView) convertView.findViewById(R.id.caloriesBurntContent);
        TextView textViewDurationContent = (TextView) convertView.findViewById(R.id.durationContent);
        TextView textViewDistanceContent = (TextView) convertView.findViewById(R.id.distanceContent);
        TextView textViewDateTimeContent = (TextView) convertView.findViewById(R.id.dateTimeContent);

        textViewCaloriesBurntContent.setText(caloriesBurnt);
        textViewDurationContent.setText(duration);
        textViewDistanceContent.setText(distance);
        textViewDateTimeContent.setText(dateTime);

        return convertView;
    }
}
