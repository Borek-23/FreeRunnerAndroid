package com.example.borek.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RunningActivity extends AppCompatActivity {

    TextView timer, distanceWhileRunning, stepsWhileRunning, caloriesWhileRunning;
    Button navButtonRunning, completeRunButton, startStopWatch;

    public com.example.borek.myapplication.Function.Chronometer stopWatch;
    public com.example.borek.myapplication.Function.DistanceCalculator distanceMeasure;
    private Thread threadStopWatch, threadDistance;

    private Context mContext, mContext1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);

        mContext = this;
        mContext1 = this;

        navButtonRunning = (Button) findViewById(R.id.navButtonRunning);
        completeRunButton = (Button) findViewById(R.id.completeRunButton);
        startStopWatch = (Button) findViewById(R.id.startStopWatch);
        timer = (TextView) findViewById(R.id.timer);
        distanceWhileRunning = (TextView) findViewById(R.id.distanceWhileRunning);
        stepsWhileRunning = (TextView) findViewById(R.id.stepsWhileRunning);
        caloriesWhileRunning = (TextView) findViewById(R.id.caloriesWhileRunning);

        startStopWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stopWatch == null) {
                    stopWatch = new com.example.borek.myapplication.Function.Chronometer(mContext);
                    threadStopWatch = new Thread(stopWatch);
                    threadStopWatch.start();
                    stopWatch.startChronometer();
                }
                if (distanceMeasure == null) {
                    distanceMeasure = new com.example.borek.myapplication.Function.DistanceCalculator(mContext1);
                    threadDistance = new Thread(distanceMeasure);
                    threadDistance.start();
                    distanceMeasure.startDistance();
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
                if (distanceMeasure != null) {
                    distanceMeasure.stopDistance();
                    threadDistance.interrupt();
                    threadDistance = null;
                    distanceMeasure = null;
                }
                Intent resultsIntent = new Intent(mContext, ResultsActivity.class);
                String result = timer.getText().toString();
                resultsIntent.putExtra("stopWatchResult", result);
                startActivity(resultsIntent);
            }
        });
    }

    public void updateStopWatch(final String time) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                timer.setText(time);
            }
        });
    }

    public void updateDistance(final String distance) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                distanceWhileRunning.setText(distance + " m");
            }
        });
    }
}
