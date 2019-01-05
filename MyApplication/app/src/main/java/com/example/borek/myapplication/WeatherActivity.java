package com.example.borek.myapplication;

import android.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;

import java.util.List;

public class WeatherActivity extends AppCompatActivity {

    Button navButtonWeather;
    TextView runCondition;
    TextView runningConditionChangeable;
    ImageView weatherIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // Initiate elements
        navButtonWeather = (Button) findViewById(R.id.navButtonWeather);
        runCondition = (TextView) findViewById(R.id.runCondition);
        runningConditionChangeable = (TextView) findViewById(R.id.runningConditionChangeable);
        weatherIcon = (ImageView) findViewById(R.id.weatherIcon);
        weatherIcon = (ImageView) findViewById(R.id.weatherIcon);
    }
}
