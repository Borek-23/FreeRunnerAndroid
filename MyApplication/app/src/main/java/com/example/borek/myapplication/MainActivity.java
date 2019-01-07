package com.example.borek.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Button objects
    Button navButtonMain;
    Button weatherButton;
    Button mapButton;
    Button tipsButton;
    Button historyButton;
    Button goButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Locating buttons on the layout
        navButtonMain = (Button) findViewById(R.id.navButtonMain);
        weatherButton = (Button) findViewById(R.id.weatherButton);
        mapButton = (Button) findViewById(R.id.mapButton);
        tipsButton = (Button) findViewById(R.id.tipsButton);
        historyButton = (Button) findViewById(R.id.historyButton);
        goButton = (Button) findViewById(R.id.goButton);

    }

    public void activityChanger(View activity) {
        if (activity.getId() == R.id.weatherButton) {
            Intent weatherIntent = new Intent(this, WeatherActivity1.class);
            startActivity(weatherIntent);
        }
        if (activity.getId() == R.id.mapButton) {
            Intent mapIntent = new Intent(this, RunningMapsActivity.class);
            startActivity(mapIntent);
        }
        if (activity.getId() == R.id.historyButton) {
            Intent historyIntent = new Intent(this, HistoryActivity.class);
            startActivity(historyIntent);
        }
        if (activity.getId() == R.id.goButton) {
            Intent runningIntent = new Intent(this, RunningActivity.class);
            startActivity(runningIntent);
        }
    }

    public void youtubeTips(View tips) {
        Intent youtubeTips = new Intent(Intent.ACTION_VIEW);
        Intent chooser = Intent.createChooser(youtubeTips, "Please launch YouTube for this feature");
        youtubeTips.setData(Uri.parse("vnd.youtube:///results?search_query=running+tips"));
        startActivity(chooser);
    }


}
