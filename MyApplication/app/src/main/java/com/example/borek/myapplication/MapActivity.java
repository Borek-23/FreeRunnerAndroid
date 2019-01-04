package com.example.borek.myapplication;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MapActivity extends AppCompatActivity {

    // Button object
    Button navButtonMap;
    Button goTrackedButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        navButtonMap = (Button) findViewById(R.id.navButtonMap);
        goTrackedButton = (Button) findViewById(R.id.goTrackedButton);
        goTrackedButton = (Button) findViewById(R.id.goTrackedButton);
    }


    public void trackedRunningActivity(View running) {
        Intent runningIntent = new Intent(this, RunningActivity.class);
        startActivity(runningIntent);
    }

}
