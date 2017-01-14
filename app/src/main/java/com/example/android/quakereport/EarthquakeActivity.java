/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.quakereport.utils.EarthquakeFormatter;
import com.example.android.quakereport.utils.EarthquakeLoader;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EarthquakeActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<EarthquakeDataModel>> {

    public static final String TAG = EarthquakeActivity.class.getName();

    private static final int EARTHQUAKE_LOADER_ID = 0;
    private static final String EARTHQUAKE_URL = "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    private EarthquakeListAdapter earthquakeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_view_activity);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.earthquake_list_data);
        if(recyclerView == null) { return; }

        List<EarthquakeDataModel> earthquakeList = new ArrayList<>();
        earthquakeAdapter = new EarthquakeListAdapter(
                earthquakeList, new EarthquakeFormatter(this, new Date(),
                new SimpleDateFormat("LLL dd, yyyy", Locale.UK),
                new SimpleDateFormat("h:mm a", Locale.UK), new DecimalFormat("0.0")));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(earthquakeAdapter);

        getSupportLoaderManager().initLoader(EARTHQUAKE_LOADER_ID, null, this);
    }

    private void updateEarthquakeData(List<EarthquakeDataModel> models) {
        if(models == null) {
            return;
        }
        earthquakeAdapter.setList(models);
        earthquakeAdapter.notifyDataSetChanged();
    }

    @Override
    public Loader<List<EarthquakeDataModel>> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader");
        return new EarthquakeLoader(this, EARTHQUAKE_URL, new EarthquakeModelsGenerator(new EarthquakeNetwork()));
    }

    @Override
    public void onLoadFinished(Loader<List<EarthquakeDataModel>> loader,
            List<EarthquakeDataModel> earthquakeDataModels) {
        Log.d(TAG, "onLoadFinished");
        if(earthquakeDataModels == null) {
            return;
        }
        updateEarthquakeData(earthquakeDataModels);
    }

    @Override
    public void onLoaderReset(Loader<List<EarthquakeDataModel>> loader) {
        Log.d(TAG, "onLoaderReset");
        updateEarthquakeData(new ArrayList<EarthquakeDataModel>());
    }

    private class EarthquakeListAdapter extends RecyclerView.Adapter<EarthquakeListAdapter.EarthquakeViewHolder> {

        private List<EarthquakeDataModel> list;
        private EarthquakeFormatter formatter;

        EarthquakeListAdapter(List<EarthquakeDataModel> list, EarthquakeFormatter formatter) {
            this.list = list;
            this.formatter = formatter;
        }

        @Override
        public EarthquakeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.earthquake_data_item, parent, false);
            return new EarthquakeViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(EarthquakeViewHolder holder, int position) {
            EarthquakeDataModel model = list.get(position);
            holder.bindView(model);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public void setList(List<EarthquakeDataModel> list) {
            this.list = list;
        }

        class EarthquakeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            EarthquakeViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
            }

            void bindView(EarthquakeDataModel model) {
                double magnitude = model.getMagnitude();
                TextView magnitudeView = (TextView) itemView.findViewById(R.id.magnitude);
                magnitudeView.setText(formatter.getMagnitudeFormatted(magnitude));
                GradientDrawable magnitudeDrawable = (GradientDrawable) magnitudeView.getBackground();
                magnitudeDrawable.setColor(formatter.getMagnitudeCircleColor(magnitude));

                String locationText = model.getLocation();
                TextView primaryLocationView = (TextView) itemView.findViewById(R.id.primary_location);
                String primaryLocation = formatter.getPrimaryLocation(locationText);
                primaryLocationView.setText(primaryLocation);

                TextView locationOffsetView = (TextView) itemView.findViewById(R.id.location_offset);
                String locationOffset = formatter.getLocationOffset(locationText);
                locationOffsetView.setText(locationOffset);

                long dateInMillis = model.getDateInMillis();
                TextView date = (TextView) itemView.findViewById(R.id.date);
                date.setText(formatter.getDateFormatted(dateInMillis));

                TextView time = (TextView) itemView.findViewById(R.id.time);
                time.setText(formatter.getTimeFormatted(dateInMillis));
            }

            @Override
            public void onClick(View v) {
                EarthquakeDataModel model = list.get(getLayoutPosition());
                openEarthquakeWebPage(model.getUrl());
            }

            private void openEarthquakeWebPage(String url) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        }
    }
}
