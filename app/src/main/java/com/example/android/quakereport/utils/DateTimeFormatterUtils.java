package com.example.android.quakereport.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Jon-Ross on 08/01/2017.
 */
public class DateTimeFormatterUtils {

    private DateTimeFormatterUtils() {}

    public static String getDateFormat(long millis) {
        return getFormat(getDateObject(millis), "LLL dd, yyyy");
    }

    public static String getDateFormat(Date date) {
        return getFormat(date, "LLL dd, yyyy");
    }

    private static Date getDateObject(long millis) {
        return new Date(millis);
    }

    public static String getTimeFormat(long millis) {
        return getFormat(getDateObject(millis), "h:mm a");
    }

    public static String getTimeFormat(Date date) {
        return getFormat(date, "h:mm a");
    }

    private static String getFormat(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.UK);
        return formatter.format(date);
    }


    // ************************** GETTERS AND SETTERS **************************
}
