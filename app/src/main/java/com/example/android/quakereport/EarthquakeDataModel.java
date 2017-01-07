package com.example.android.quakereport;

/**
 * Created by Jon-Ross on 07/01/2017.
 */
public class EarthquakeDataModel {

    private String location;
    private float magnitude;
    private String date;

    public EarthquakeDataModel(String location, float magnitude, String date) {
        this.location = location;
        this.magnitude = magnitude;
        this.date = date;
    }


    // ************************** GETTERS AND SETTERS **************************

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(float magnitude) {
        this.magnitude = magnitude;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
