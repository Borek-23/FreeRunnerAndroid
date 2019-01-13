package com.example.borek.myapplication.Function;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.borek.myapplication.RunningActivity;

/**
 * Created by borek on 13/01/19.
 */

public class StepsCalculator implements Runnable {

    private long startTime;
    private boolean stepsIsRunning;
    private Context mContext;

    public StepsCalculator(Context context) {
        mContext = context;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void run() {
        while (stepsIsRunning) {
            long since = System.currentTimeMillis() - startTime;

            // This is experimental calculation of calories burnt while running at 5.2mph, based on 190pounds individual
            // Just to see if the data be updated in real time and handled in other parts of app
            // In final product this would be recalculated based on individual's personal profile
            int seconds = (int) ((since / 1000) % 60);
            int stepsPerSecond = seconds * 3;

            ((RunningActivity)mContext).updateSteps(String.valueOf(stepsPerSecond));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startSteps() {
        startTime = System.currentTimeMillis();
        stepsIsRunning = true;
    }

    public void stopSteps() {
        stepsIsRunning = false;
    }

}
