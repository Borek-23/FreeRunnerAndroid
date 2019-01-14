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

// This is a custom made adapter to use in my history list view as I wanted to create something else than stock list view
public class HistoricalDataListAdapter extends ArrayAdapter<HistoricalData> {

    private Context mContext;
    int mResource;

    // Constructor calls its super class and takes in context, resource and custom type array list
    public HistoricalDataListAdapter(Context context, int resource, ArrayList<HistoricalData> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    // Must override getView() method
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

        // Create the inflater to set in the elements of custom view
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        // Initiate appropriate texViews to display the content from the array list
        TextView textViewCaloriesBurntContent = (TextView) convertView.findViewById(R.id.caloriesBurntContent);
        TextView textViewDurationContent = (TextView) convertView.findViewById(R.id.durationContent);
        TextView textViewDistanceContent = (TextView) convertView.findViewById(R.id.distanceContent);
        TextView textViewDateTimeContent = (TextView) convertView.findViewById(R.id.dateTimeContent);

        // Set said textViews to with an appropriate content from the array list
        textViewCaloriesBurntContent.setText(caloriesBurnt);
        textViewDurationContent.setText(duration);
        textViewDistanceContent.setText(distance);
        textViewDateTimeContent.setText(dateTime);

        return convertView;
    }
}
