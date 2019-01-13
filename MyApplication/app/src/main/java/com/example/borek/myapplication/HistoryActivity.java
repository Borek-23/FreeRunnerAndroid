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

        navButtonHistory = (Button) findViewById(R.id.navButtonHistory);
        shareButton = (Button) findViewById(R.id.shareButton);
        resultsListView = (ListView) findViewById(R.id.resultsListView);

        SharedPreferences sharedPref = getSharedPreferences("runResult", Context.MODE_PRIVATE);

        String timeResult = sharedPref.getString("timeResult", "");
        String distanceResult = sharedPref.getString("distanceResult", "");
        String calorieResult = sharedPref.getString("calorieResult", "");

        Date date = new Date();
        // Getting the format of the date based on the application context (locale)
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());

        // Create objects for data
        HistoricalData run1 = new HistoricalData(calorieResult, timeResult, distanceResult, dateFormat.format(date));
//        HistoricalData run2 = new HistoricalData(calorieResult, timeResult, distanceResult, "1/1/2019");
//        HistoricalData run3 = new HistoricalData("520", "23:23", "9", "3/1/2019");
//        HistoricalData run4 = new HistoricalData("234", "23:23", "9", "4/1/2019");
//        HistoricalData run5 = new HistoricalData("234", "23:23", "9", "5/1/2019");
//        HistoricalData run6 = new HistoricalData("345", "23:23", "9", "6/1/2019");
//        HistoricalData run7 = new HistoricalData("556", "23:23", "9", "7/1/2019");
//        HistoricalData run8 = new HistoricalData("456", "23:23", "9", "8/1/2019");
//        HistoricalData run9 = new HistoricalData("977", "23:23", "9", "9/1/2019");

        // Create an arrayList to hold the data -> then populate it
        ArrayList<HistoricalData> historyData = new ArrayList<>();
        historyData.add(run1);
//        historyData.add(run2);
//        historyData.add(run3);
//        historyData.add(run4);
//        historyData.add(run5);
//        historyData.add(run6);
//        historyData.add(run7);
//        historyData.add(run8);
//        historyData.add(run9);

        HistoricalDataListAdapter adapter = new HistoricalDataListAdapter(this, R.layout.adapter_view_layout, historyData);
        resultsListView.setAdapter(adapter);
    }
}
