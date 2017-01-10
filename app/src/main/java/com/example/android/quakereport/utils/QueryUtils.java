package com.example.android.quakereport.utils;

import android.util.Log;

import com.example.android.quakereport.EarthquakeDataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jon-Ross on 08/01/2017.
 */
public final class QueryUtils {

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link EarthquakeDataModel} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<EarthquakeDataModel> extractEarthquakes(String jsonResponse) {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<EarthquakeDataModel> earthquakes = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.
            JSONObject root = new JSONObject(jsonResponse);

            JSONArray featuresArray = root.getJSONArray("features");
            for(int i = 0; i < featuresArray.length(); i++) {
                JSONObject featuresObject = featuresArray.getJSONObject(i);
                JSONObject propsObject = featuresObject.getJSONObject("properties");

                String location = propsObject.getString("place");
                double magnitude = propsObject.getDouble("mag");
                long date = propsObject.getLong("time");
                String url = propsObject.getString("url");
                EarthquakeDataModel model = new EarthquakeDataModel(location, magnitude, date, url);
                earthquakes.add(model);
            }
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }


    // ************************** GETTERS AND SETTERS **************************
}
