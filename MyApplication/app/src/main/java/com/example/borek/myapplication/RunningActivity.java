package com.example.borek.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RunningActivity extends AppCompatActivity {

    // Declaring TexView and Button variables
    TextView timer, distanceWhileRunning, stepsWhileRunning, caloriesWhileRunning;
    Button navButtonRunning, completeRunButton, startStopWatch;

    // Declaring and instance of the Runnable here and assigning to variables
    public com.example.borek.myapplication.Function.Chronometer stopWatch;
    public com.example.borek.myapplication.Function.DistanceCalculator distanceMeasure;
    public com.example.borek.myapplication.Function.StepsCalculator stepsMeasure;
    public com.example.borek.myapplication.Function.CaloriesCalculator caloriesMeasure;

    // Declaring variables of type Thread (threads essentially)
    private Thread threadStopWatch, threadDistance, threadSteps, threadCalories;

    // Declaring a variable mContext of type context to specify context of this Intent
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);

        // Assigning the context of this intent to a variable mContext
        mContext = this;

        // Initiating layout elements to be usable within this intent
        navButtonRunning = (Button) findViewById(R.id.navButtonRunning);
        completeRunButton = (Button) findViewById(R.id.completeRunButton);
        startStopWatch = (Button) findViewById(R.id.startStopWatch);
        timer = (TextView) findViewById(R.id.timer);
        distanceWhileRunning = (TextView) findViewById(R.id.distanceWhileRunning);
        stepsWhileRunning = (TextView) findViewById(R.id.stepsWhileRunning);
        caloriesWhileRunning = (TextView) findViewById(R.id.caloriesWhileRunning);

        // Creating an onClickListener to set the function of what happens when appropriate button is clicked
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

                saveHistory(view);
            }
        });
    }

    // Save the data from running to shared preferences
    public void saveHistory(View view) {
        SharedPreferences sharedPref = getSharedPreferences("runResult", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("timeResult", timer.getText().toString());
        editor.putString("distanceResult", distanceWhileRunning.getText().toString());
        editor.putString("calorieResult", caloriesWhileRunning.getText().toString());
        editor.apply();

        Toast.makeText(this, "Saved to History", Toast.LENGTH_LONG).show();
    }

    // The methods bellow update values of TextViews in real time -> all depending on System.currentTimeMillis()

    // Use UI thread to update the chronometer textView in real time
    public void updateStopWatch(final String time) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                timer.setText(time);
            }
        });
    }

    // Use UI thread to update the distance textView in real time
    public void updateDistance(final String distance) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                distanceWhileRunning.setText(distance + " m");
            }
        });
    }

    // Use UI thread to update the steps textView in real time
    public void updateSteps(final String steps) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                stepsWhileRunning.setText(steps);
            }
        });
    }

    // Use UI thread to update the calories textView in real time
    public void updateCalories(final String calories) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                caloriesWhileRunning.setText(calories + " cal");
            }
        });
    }
}
