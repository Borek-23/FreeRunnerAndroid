package com.example.borek.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class HistoryActivity extends AppCompatActivity {

    // Button objects
    Button navButton;
    Button shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        navButton = (Button) findViewById(R.id.navButton);
        shareButton = (Button) findViewById(R.id.shareButton);
    }
}
