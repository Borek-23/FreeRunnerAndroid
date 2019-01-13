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
    public com.example.borek.myapplication.Function.StepsCalculator stepsMeasure;
    public com.example.borek.myapplication.Function.CaloriesCalculator caloriesMeasure;
    private Thread threadStopWatch, threadDistance, threadSteps, threadCalories;

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
                    distanceMeasure = new com.example.borek.myapplication.Function.DistanceCalculator(mContext);
                    threadDistance = new Thread(distanceMeasure);
                    threadDistance.start();
                    distanceMeasure.startDistance();
                }
                if (stepsMeasure == null) {
                    stepsMeasure = new com.example.borek.myapplication.Function.StepsCalculator(mContext);
                    threadSteps = new Thread(stepsMeasure);
                    threadSteps.start();
                    stepsMeasure.startSteps();
                }
                if (caloriesMeasure == null) {
                    caloriesMeasure = new com.example.borek.myapplication.Function.CaloriesCalculator(mContext);
                    threadCalories = new Thread(caloriesMeasure);
                    threadCalories.start();
                    caloriesMeasure.startCalories();
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
                if (stepsMeasure != null) {
                    stepsMeasure.stopSteps();
                    threadSteps.interrupt();
                    threadSteps = null;
                    stepsMeasure = null;
                }
                if (caloriesMeasure != null) {
                    caloriesMeasure.stopCalories();
                    threadCalories.interrupt();
                    threadCalories = null;
                    caloriesMeasure = null;
                }
                Intent resultsIntent = new Intent(mContext, ResultsActivity.class);
                String result = timer.getText().toString();
                String distanceResult = distanceWhileRunning.getText().toString();
                String calorieResult = caloriesWhileRunning.getText().toString();
                resultsIntent.putExtra("stopWatchResult", result);
                resultsIntent.putExtra("distanceResult", distanceResult);
                resultsIntent.putExtra("calorieResult", calorieResult);
                startActivity(resultsIntent);
            }
        });
    }

    // Use UI thread to update the textView in real time
    public void updateStopWatch(final String time) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                timer.setText(time);
            }
        });
    }

    // Use UI thread to update the textView in real time
    public void updateDistance(final String distance) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                distanceWhileRunning.setText(distance + " m");
            }
        });
    }

    // Use UI thread to update the textView in real time
    public void updateSteps(final String steps) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                stepsWhileRunning.setText(steps);
            }
        });
    }

    // Use UI thread to update the textView in real time
    public void updateCalories(final String calories) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                caloriesWhileRunning.setText(calories + " cal");
            }
        });
    }
}
