package com.example.borek.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherActivity extends AppCompatActivity {

    Button navButtonWeather;
    TextView runCondition;
    TextView runningConditionChangeable;
    TextView weatherCondition;
    TextView humidity;
    TextView visibility;
    TextView temperature;
    ImageView weatherIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // Initiate elements
        navButtonWeather = (Button) findViewById(R.id.navButtonWeather);
        runCondition = (TextView) findViewById(R.id.runCondition);
        runningConditionChangeable = (TextView) findViewById(R.id.runningConditionChangeable);
        weatherCondition = (TextView) findViewById(R.id.weatherCondition);
        humidity = (TextView) findViewById(R.id.humidity);
        visibility = (TextView) findViewById(R.id.visibility);
        temperature = (TextView) findViewById(R.id.temperature);
        weatherIcon = (ImageView) findViewById(R.id.weatherIcon);
    }

    
}
