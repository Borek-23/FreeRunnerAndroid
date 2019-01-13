package com.example.borek.myapplication.Function;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.borek.myapplication.RunningActivity;

/**
 * Created by borek on 13/01/19.
 */

public class DistanceCalculator implements Runnable {

    private long startTime;
    private boolean distanceIsRunning;
    private Context mContext1;

    public DistanceCalculator(Context context) {
        mContext1 = context;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void run() {
        while (distanceIsRunning) {
            long since = System.currentTimeMillis() - startTime;

            int seconds = (int) ((since / 1000) % 60);
            double distance = 5.2 * seconds;

            ((RunningActivity)mContext1).updateDistance(String.valueOf(distance));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startDistance() {
        startTime = System.currentTimeMillis();
        distanceIsRunning = true;
    }

    public void stopDistance() {
        distanceIsRunning = false;
    }

}
