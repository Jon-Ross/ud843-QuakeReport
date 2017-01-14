package com.example.android.quakereport;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Jon-Ross on 10/01/2017.
 */
public class EarthquakeNetwork {

    private static final String TAG = EarthquakeNetwork.class.getName();

    public String makeEarthquakeHttpRequest(URL url) throws IOException {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.connect();
            int responseCode = connection.getResponseCode();
            if(responseCode == 200) {
                inputStream = connection.getInputStream();
                return getJsonResponse(inputStream);
            } else {
                Log.d(TAG, "Error trying to connect. Response code: " + responseCode);
            }
        } catch(IOException e) {
            Log.e(TAG, "Problem retrieving earthquake JSON results", e);
            e.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
            if(inputStream != null) {
                inputStream.close();
            }
        }
        return null;
    }

    private String getJsonResponse(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader br = new BufferedReader(inputStreamReader);
        String line = br.readLine();
        while(line != null) {
            builder.append(line);
            line = br.readLine();
        }
        return builder.toString();
    }


    // ************************** GETTERS AND SETTERS **************************
}
