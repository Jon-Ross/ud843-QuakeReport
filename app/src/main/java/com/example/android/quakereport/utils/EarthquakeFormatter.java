package com.example.android.quakereport.utils;

import java.text.Format;
import java.text.NumberFormat;
import java.util.Date;

/**
 * Created by Jon-Ross on 08/01/2017.
 */
public class EarthquakeFormatter {

    private Date date;
    private Format dateFormatter;
    private Format timeFormatter;
    private NumberFormat magnitudeFormatter;
    private LocationFormatter locationFormatter;

    public EarthquakeFormatter(Date date, Format dateFormatter,
            Format timeFormatter, NumberFormat magnitudeFormatter) {
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
