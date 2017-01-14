package com.example.android.quakereport.utils;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.text.TextUtils;
import android.util.Log;

import com.example.android.quakereport.EarthquakeDataModel;
import com.example.android.quakereport.EarthquakeModelsGenerator;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Jon-Ross on 14/01/2017.
 */
public class EarthquakeLoader extends AsyncTaskLoader<List<EarthquakeDataModel>> {

    private static final String TAG = EarthquakeLoader.class.getName();

    private String url;
    private EarthquakeModelsGenerator dataNetwork;

    public EarthquakeLoader(Context context, String url, EarthquakeModelsGenerator dataNetwork) {
        super(context);
        this.url = url;
        this.dataNetwork = dataNetwork;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<EarthquakeDataModel> loadInBackground() {
        Log.d(TAG, "loadInBackground");
        if(TextUtils.isEmpty(url)) {
            return null;
        }
        try {
            URL url = new URL(this.url);
            return dataNetwork.getEarthquakeModels(url);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    // ************************** GETTERS AND SETTERS **************************
}
