package com.example.borek.myapplication.Function;

/**
 * Created by borek on 13/01/19.
 */

public class HistoricalData {
    private String dateTime, duration, distance, caloriesBurnt;


    public HistoricalData(String caloriesBurnt, String duration, String distance, String dateTime) {
        this.caloriesBurnt = caloriesBurnt;
        this.duration = duration;
        this.distance = distance;
        this.dateTime = dateTime;

    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCaloriesBurnt() {
        return caloriesBurnt;
    }

    public void setCaloriesBurnt(String caloriesBurnt) {
        this.caloriesBurnt = caloriesBurnt;
    }
}
