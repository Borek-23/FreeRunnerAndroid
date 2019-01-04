package com.example.borek.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MapActivity extends AppCompatActivity {

    // Button object
    Button navButton;
    Button goTrackedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        navButton = (Button) findViewById(R.id.navButton);
        goTrackedButton = (Button) findViewById(R.id.goTrackedButton);
    }

    public void trackedRunningActivity(View running) {
        Intent runningIntent = new Intent(this, RunningActivity.class);
        startActivity(runningIntent);
    }
}
