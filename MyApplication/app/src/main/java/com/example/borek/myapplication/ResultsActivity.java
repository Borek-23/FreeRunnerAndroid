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

    // Button object
    Button navButtonResult, shareButton;
    TextView resultsActivityTop, timeOfRun, caloriesBurnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        navButtonResult = (Button) findViewById(R.id.navButtonResult);
        shareButton = (Button) findViewById(R.id.shareButton);
        timeOfRun = (TextView) findViewById(R.id.timeOfRun);
        caloriesBurnt = (TextView) findViewById(R.id.caloriesBurnt);

        // Locating TextViews on the layout
        resultsActivityTop = (TextView) findViewById(R.id.resultsActivityTop);

        // Assign custom font
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/bahnschrift.ttf");
        resultsActivityTop.setTypeface(customFont);

        // Set the Toolbar to display current date
        Date date = new Date();
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        resultsActivityTop.setText("Result for " + dateFormat.format(date));

        Bundle extras = getIntent().getExtras();
        String runTimeResult = extras.getString("stopWatchResult");
        timeOfRun.setText(runTimeResult);

//        int calorieResult = calculateBurntCalories();
//        caloriesBurnt.setText(calorieResult);
    }

    // This is experimental calculation of calories burnt while running at 5.2mph, based on 80kg individual
    // Just to see if the data be updated in real time and handled in other parts of app
    // In final product this would be recalculated based on individual's personal profile
//    public int calculateBurntCalories() {
//        int caloriePerMinute = (int) ((9 * 80 * 3.5) / 200); // per minute
//        String textData = timeOfRun.getText().toString();
//        int textToDouble = Integer.parseInt(textData);
//        return textToDouble; // (caloriePerMinute * textToDouble)
//    }


}
