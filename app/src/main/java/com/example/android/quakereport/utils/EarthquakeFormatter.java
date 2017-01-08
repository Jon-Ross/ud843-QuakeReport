package com.example.android.quakereport.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.example.android.quakereport.R;

import java.text.Format;
import java.text.NumberFormat;
import java.util.Date;

/**
 * Created by Jon-Ross on 08/01/2017.
 */
public class EarthquakeFormatter {

    private final Context context;
    private final Date date;
    private final Format dateFormatter;
    private final Format timeFormatter;
    private final NumberFormat magnitudeFormatter;
    private final LocationFormatter locationFormatter;

    public EarthquakeFormatter(Context context, Date date, Format dateFormatter,
            Format timeFormatter, NumberFormat magnitudeFormatter) {
        this.context = context;
        this.date = date;
        this.dateFormatter = dateFormatter;
        this.timeFormatter = timeFormatter;
        this.magnitudeFormatter = magnitudeFormatter;
        locationFormatter = new LocationFormatter();
    }

    public String getDateFormatted(long timeMillis) {
        date.setTime(timeMillis);
        return dateFormatter.format(date);
    }

    public String getTimeFormatted(long timeMillis) {
        date.setTime(timeMillis);
        return timeFormatter.format(date);
    }

    public String getMagnitudeFormatted(double magnitude) {
        return magnitudeFormatter.format(magnitude);
    }

    public String getLocationOffset(String locationText) {
        return locationFormatter.getLocationOffset(locationText);
    }

    public String getPrimaryLocation(String locationText) {
        return locationFormatter.getPrimaryLocation(locationText);
    }

    public int getMagnitudeCircleColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(context, magnitudeColorResourceId);
    }


    // ********************* GETTERS AND SETTERS AND HELPER CLASSES *********************

    private class LocationFormatter {

        private String locationText;
        private String[] locationParts = new String[2];

        private String getLocationOffset(String locationText) {
            setLocationText(locationText);
            if(locationParts == null) {
                return null;
            }

            if(locationParts.length == 1) {
                return "Near the";
            } else {
                return locationParts[0] + " of ";
            }
        }

        private String getPrimaryLocation(String locationText) {
            setLocationText(locationText);
            if(locationParts == null) {
                return null;
            }

            if(locationParts.length == 1) {
                return locationParts[0];
            } else {
                return locationParts[1];
            }
        }

        private void setLocationText(String locationText) {
            if(this.locationText == null || !this.locationText.equals(locationText) ||
                    locationParts == null) {
                this.locationText = locationText;
                locationParts = locationText.split(" of ");
            }
        }
    }
}
