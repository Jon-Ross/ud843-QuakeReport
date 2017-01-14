package com.example.android.quakereport;

import android.text.TextUtils;

import com.example.android.quakereport.utils.QueryUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Jon-Ross on 10/01/2017.
 */
public class EarthquakeModelsGenerator {

    private static final String TAG = EarthquakeModelsGenerator.class.getName();

    private EarthquakeNetwork network;

    public EarthquakeModelsGenerator(EarthquakeNetwork network) {
        this.network = network;
    }

    public List<EarthquakeDataModel> getEarthquakeModels(URL url) throws IOException {
        String jsonResponse = network.makeEarthquakeHttpRequest(url);
        if(!TextUtils.isEmpty(jsonResponse)) {
            return QueryUtils.extractEarthquakes(jsonResponse);
        }
        return null;
    }


    // ************************** GETTERS AND SETTERS **************************
}
