package com.example.borek.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RunningActivity extends AppCompatActivity {

    // Button object
    Button navButtonRunning;
    Button completeRunButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);

        navButtonRunning = (Button) findViewById(R.id.navButtonRunning);
        completeRunButton = (Button) findViewById(R.id.completeRunButton);
    }

    public void resultsActivity(View results) {
        Intent resultsIntent = new Intent(this, ResultsActivity.class);
        startActivity(resultsIntent);
    }

}
