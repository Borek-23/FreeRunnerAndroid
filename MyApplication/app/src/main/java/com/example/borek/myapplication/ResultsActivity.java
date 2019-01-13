package com.example.borek.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ResultsActivity extends AppCompatActivity {

    // Declaring Button objects
    Button navButtonResult, shareButton;
    // Declaring TextView objects
    TextView resultsActivityTop, timeOfRun, caloriesBurnt, distanceRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Initialise the layout objects
        navButtonResult = (Button) findViewById(R.id.navButtonResult);
        shareButton = (Button) findViewById(R.id.shareButton);
        timeOfRun = (TextView) findViewById(R.id.timeOfRun);
        caloriesBurnt = (TextView) findViewById(R.id.caloriesBurnt);
        distanceRun = (TextView) findViewById(R.id.distanceRun);

        // Locating TextViews on the layout
        resultsActivityTop = (TextView) findViewById(R.id.resultsActivityTop);

        // Assigning custom font
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/bahnschrift.ttf");
        resultsActivityTop.setTypeface(customFont);

        // Set the Toolbar to display current date
        Date date = new Date();
        // Getting the format of the date based on the application context (locale)
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        // Updating a TextView to display the date
        resultsActivityTop.setText("Result for " + dateFormat.format(date));

        // Here creating a Bundle to obtain data from the RunningActivity
        Bundle extras = getIntent().getExtras();
        // Declaring string variables to get the values from variables created in RunningActivity
        String runTimeResult = extras.getString("stopWatchResult");
        String runDistanceResult = extras.getString("distanceResult");
        String runCaloriesResult = extras.getString("calorieResult");
        // Updating text for respective TextViews
        timeOfRun.setText(runTimeResult);
        distanceRun.setText(runDistanceResult);
        caloriesBurnt.setText(runCaloriesResult);
    }
}
