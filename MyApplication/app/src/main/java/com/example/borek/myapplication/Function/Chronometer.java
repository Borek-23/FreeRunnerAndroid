package com.example.borek.myapplication.Function;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.borek.myapplication.ResultsActivity;
import com.example.borek.myapplication.RunningActivity;

/**
 * Created by borek on 06/01/19.
 */

public class Chronometer implements Runnable {

    // Make it easier on myself to calculate values for chronometer by declaring constants
    public static final long MILLIS_TO_MINUTES = 60000;
    public static final long MILLIS_TO_HOURS = 3600000;

    // Declaring necessary variables
    private long startTime;
    private boolean chronometerIsRunning;
    private Context mContext;

    // Setting a constructor to initiate context of this class
    public Chronometer(Context context) {
        mContext = context;
    }

    // Must override run method since I am implementing an interface here (Runnable)
    @SuppressLint("DefaultLocale")
    @Override
    public void run() {
        // Creating a while loop which provides calculations while the thread is running
        while (chronometerIsRunning) {
            long since = System.currentTimeMillis() - startTime;

            // Declaring necessary variables to update the TextView with
            int seconds = (int) ((since / 1000) % 60);
            int minutes = (int) ((since / MILLIS_TO_MINUTES) % 60);
            int hours = (int) ((since / MILLIS_TO_HOURS) % 24);
            int millis = (int) since % 1000;

            // Accessing the Running activity's method updateStopWatch() and formatting string as desired
            ((RunningActivity)mContext).updateStopWatch(String.format("%02d:%02d:%02d:%03d", hours, minutes, seconds, millis));
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
    public void startChronometer() {
        startTime = System.currentTimeMillis();
        chronometerIsRunning = true;
    }

    // This method stops the while loop
    public void stopChronometer() {
        chronometerIsRunning = false;
    }


}