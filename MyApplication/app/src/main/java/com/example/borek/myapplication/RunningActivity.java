package com.example.borek.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class RunningActivity extends AppCompatActivity {

    TextView timer;
    Button navButtonRunning;
    Button completeRunButton;
    Button startStopWatch;

    public com.example.borek.myapplication.Function.Chronometer stopWatch;
    private Thread threadStopWatch;

    private Context mContext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);

        mContext = this;

        navButtonRunning = (Button) findViewById(R.id.navButtonRunning);
        completeRunButton = (Button) findViewById(R.id.completeRunButton);
        startStopWatch = (Button) findViewById(R.id.startStopWatch);
        timer = (TextView) findViewById(R.id.timer);

        startStopWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stopWatch == null) {
                    stopWatch = new com.example.borek.myapplication.Function.Chronometer(mContext);
                    threadStopWatch = new Thread(stopWatch);
                    threadStopWatch.start();
                    stopWatch.startChronometer();
                }
            }
        });

        completeRunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stopWatch != null) {
                    stopWatch.stopChronometer();
                    threadStopWatch.interrupt();
                    threadStopWatch = null;
                    stopWatch = null;
                }
                Intent resultsIntent = new Intent(mContext, ResultsActivity.class);
                startActivity(resultsIntent);
            }
        });
    }

//    public void resultsActivity(View results) {
//        Intent resultsIntent = new Intent(this, ResultsActivity.class);
//        startActivity(resultsIntent);
//    }

    public void updateStopWatch(final String time) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                timer.setText(time);
            }
        });
    }
}
