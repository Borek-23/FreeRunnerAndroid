package com.example.borek.myapplication.Function;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.borek.myapplication.RunningActivity;

/**
 * Created by borek on 13/01/19.
 */

public class DistanceCalculator implements Runnable {

    // Declaring necessary variables
    private long startTime;
    private boolean distanceIsRunning;
    private Context mContext;

    // Setting a constructor to initiate context of this class
    public DistanceCalculator(Context context) {
        mContext = context;
    }

    // Must override run method since I am implementing an interface here (Runnable)
    @SuppressLint("DefaultLocale")
    @Override
    public void run() {
        // Creating a while loop which provides calculations while the thread is running
        while (distanceIsRunning) {
            long since = System.currentTimeMillis() - startTime;

            // This is experimental calculation of calories burnt while running at 5.2mph, based on 190pounds individual
            // Just to see if the data be updated in real time and handled in other parts of app
            // In final product this would be recalculated based on individual's personal profile
            int seconds = (int) ((since / 1000) % 60);
            double distance = 5.2 * seconds;

            // Accessing the Running activity's method updateDistance() and formatting string as desired
            ((RunningActivity)mContext).updateDistance(String.format("%.1f", distance));
            try {
                // Put thread to sleep for 10 milliseconds before starting to run
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // These methods are called from the RunningActivity

    // This method starts the while while loop
    public void startDistance() {
        startTime = System.currentTimeMillis();
        distanceIsRunning = true;
    }

    // This method stops the while loop
    public void stopDistance() {
        distanceIsRunning = false;
    }

}
