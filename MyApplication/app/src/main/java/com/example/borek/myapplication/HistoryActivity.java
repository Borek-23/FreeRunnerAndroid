package com.example.borek.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.borek.myapplication.Function.HistoricalData;
import com.example.borek.myapplication.Function.HistoricalDataListAdapter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HistoryActivity extends AppCompatActivity {

    // Button objects
    Button navButtonHistory;
    Button shareButton;
    ListView resultsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Initiate the layout elements
        navButtonHistory = (Button) findViewById(R.id.navButtonHistory);
        shareButton = (Button) findViewById(R.id.shareButton);
        resultsListView = (ListView) findViewById(R.id.resultsListView);

        // Using shared preferences to obtain the data from RunningActivity's intent
        SharedPreferences sharedPref = getSharedPreferences("runResult", Context.MODE_PRIVATE);

        String timeResult = sharedPref.getString("timeResult", "");
        String distanceResult = sharedPref.getString("distanceResult", "");
        String calorieResult = sharedPref.getString("calorieResult", "");

        Date date = new Date();
        // Getting the format of the date based on the application context (locale)
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());

        // Create objects for data -> using the data from shared preferences
        HistoricalData run1 = new HistoricalData(calorieResult, timeResult, distanceResult, dateFormat.format(date));
        HistoricalData run2 = new HistoricalData(calorieResult, timeResult, distanceResult, dateFormat.format(date));
        HistoricalData run3 = new HistoricalData(calorieResult, timeResult, distanceResult, dateFormat.format(date));
        HistoricalData run4 = new HistoricalData(calorieResult, timeResult, distanceResult, dateFormat.format(date));
        HistoricalData run5 = new HistoricalData(calorieResult, timeResult, distanceResult, dateFormat.format(date));
        HistoricalData run6 = new HistoricalData(calorieResult, timeResult, distanceResult, dateFormat.format(date));
        HistoricalData run7 = new HistoricalData(calorieResult, timeResult, distanceResult, dateFormat.format(date));
        HistoricalData run8 = new HistoricalData(calorieResult, timeResult, distanceResult, dateFormat.format(date));
        HistoricalData run9 = new HistoricalData(calorieResult, timeResult, distanceResult, dateFormat.format(date));
        HistoricalData run10 = new HistoricalData(calorieResult, timeResult, distanceResult, dateFormat.format(date));

        // Create an arrayList to hold the data -> then populate it
        ArrayList<HistoricalData> historyData = new ArrayList<>();
        historyData.add(run1);
        historyData.add(run2);
        historyData.add(run3);
        historyData.add(run4);
        historyData.add(run5);
        historyData.add(run6);
        historyData.add(run7);
        historyData.add(run8);
        historyData.add(run9);
        historyData.add(run10);

        // Create an adapter object and populate it with the custom layout
        HistoricalDataListAdapter adapter = new HistoricalDataListAdapter(this, R.layout.adapter_view_layout, historyData);
        resultsListView.setAdapter(adapter);
    }
}
