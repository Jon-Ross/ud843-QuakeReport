package com.example.android.quakereport;

/**
 * Created by Jon-Ross on 07/01/2017.
 */
public class EarthquakeDataModel {

    private final String location;
    private final double magnitude;
    private final long date;
    private final String url;

    public EarthquakeDataModel(String location, double magnitude, long date, String url) {
        this.location = location;
        this.magnitude = magnitude;
        this.date = date;
        this.url = url;
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

    public String getUrl() {
        return url;
    }
}
