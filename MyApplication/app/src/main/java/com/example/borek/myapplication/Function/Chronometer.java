package com.example.borek.myapplication.Function;

import android.content.Context;

import com.example.borek.myapplication.RunningActivity;

/**
 * Created by borek on 06/01/19.
 */

public class Chronometer implements Runnable {

    public static final long MILLIS_TO_MINUTES = 60000;
    public static final long MILLIS_TO_HOURS = 3600000;

    private long startTime;
    private boolean chronometerIsRunning;
    private Context mContext;

    public Chronometer(Context context) {
        mContext = context;
    }

    @Override
    public void run() {
        while (chronometerIsRunning) {
            long since = System.currentTimeMillis() - startTime;

            int seconds = (int) ((since / 1000) % 60);
            int minutes = (int) ((since / MILLIS_TO_MINUTES) % 60);
            int hours = (int) ((since / MILLIS_TO_HOURS) % 24);
            int millis = (int) since % 1000;

            ((RunningActivity)mContext).updateStopWatch(String.format("%02d:%02d:%02d:%03d", hours, minutes, seconds, millis));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startChronometer() {
        startTime = System.currentTimeMillis();
        chronometerIsRunning = true;
    }

    public void stopChronometer() {
        chronometerIsRunning = false;
    }


}