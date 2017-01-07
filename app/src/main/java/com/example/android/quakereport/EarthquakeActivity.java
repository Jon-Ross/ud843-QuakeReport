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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_view_activity);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.earthquake_list_data);
        if(recyclerView == null) { return; }

        List<EarthquakeDataModel> earthquakeList = getEarthquakeDataModels();
        if(earthquakeList == null) { return; }

        EarthquakeListAdapter earthquakeAdapter = new EarthquakeListAdapter(earthquakeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(earthquakeAdapter);

        /*// Create a fake list of earthquake locations.
        ArrayList<String> earthquakes = new ArrayList<>();
        earthquakes.add("San Francisco");
        earthquakes.add("London");
        earthquakes.add("Tokyo");
        earthquakes.add("Mexico City");
        earthquakes.add("Moscow");
        earthquakes.add("Rio de Janeiro");
        earthquakes.add("Paris");

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, earthquakes);

        // Set the earthquakeAdapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);*/
    }

    private List<EarthquakeDataModel> getEarthquakeDataModels() {
        List<EarthquakeDataModel> earthquakeList = new ArrayList<>();
        earthquakeList.add(new EarthquakeDataModel("San Francisco", 4.3f, "2015-06-12"));
        earthquakeList.add(new EarthquakeDataModel("London", 1.3f, "2015-02-02"));
        earthquakeList.add(new EarthquakeDataModel("Tokyo", 5.2f, "2015-02-02"));
        earthquakeList.add(new EarthquakeDataModel("Mexico City", 7.4f, "2015-02-02"));
        earthquakeList.add(new EarthquakeDataModel("Moscow", 3.6f, "2015-02-02"));
        earthquakeList.add(new EarthquakeDataModel("Rio de Janeiro", 4.8f, "2015-02-02"));
        earthquakeList.add(new EarthquakeDataModel("Paris", 2.0f, "2015-02-02"));
        return earthquakeList;
    }

    private class EarthquakeListAdapter extends RecyclerView.Adapter<EarthquakeViewHolder> {

        private List<EarthquakeDataModel> list;

        public EarthquakeListAdapter(List<EarthquakeDataModel> list) {
            this.list = list;
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
    }

    private class EarthquakeViewHolder extends RecyclerView.ViewHolder {

        public EarthquakeViewHolder(View itemView) {
            super(itemView);
        }

        public void bindView(EarthquakeDataModel model) {
            TextView magnitude = (TextView) itemView.findViewById(R.id.magnitude);
            magnitude.setText(String.valueOf(model.getMagnitude()));

            TextView location = (TextView) itemView.findViewById(R.id.location);
            location.setText(model.getLocation());

            TextView date = (TextView) itemView.findViewById(R.id.date);
            date.setText(model.getDate());
        }
    }
}
