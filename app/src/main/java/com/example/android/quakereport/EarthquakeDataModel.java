package com.example.android.quakereport;

/**
 * Created by Jon-Ross on 07/01/2017.
 */
public class EarthquakeDataModel {

    private String location;
    private double magnitude;
    private long date;

    public EarthquakeDataModel(String location, double magnitude, long date) {
        this.location = location;
        this.magnitude = magnitude;
        this.date = date;
    }


    // ************************** GETTERS AND SETTERS **************************

    public String getLocation() {
        return location;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public long getDateInMillis() {
        return date;
    }
}
