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
    Button navButtonResult;
    Button shareButton;

    // TextView object
    TextView resultsActivityTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        navButtonResult = (Button) findViewById(R.id.navButtonResult);
        shareButton = (Button) findViewById(R.id.shareButton);

        // Locating TextViews on the layout
        resultsActivityTop = (TextView) findViewById(R.id.resultsActivityTop);

        // Assign custom font
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/bahnschrift.ttf");
        resultsActivityTop.setTypeface(customFont);

        // Set the Toolbar to display current date
        Date date = new Date();
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        resultsActivityTop.setText("Result for " + dateFormat.format(date));
    }
}
