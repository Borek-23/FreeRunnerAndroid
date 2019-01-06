package com.example.borek.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherActivity1 extends AppCompatActivity {

    Button navButtonWeather;
    TextView runCondition;
    TextView runningCondition;
    ImageView weatherIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather1);

        // Initiate elements
        navButtonWeather = (Button) findViewById(R.id.navButtonWeather);
        runCondition = (TextView) findViewById(R.id.runCondition);
        runningCondition = (TextView) findViewById(R.id.runningCondition);
        weatherIcon = (ImageView) findViewById(R.id.weatherIcon);
        weatherIcon = (ImageView) findViewById(R.id.weatherIcon);
    }
}
