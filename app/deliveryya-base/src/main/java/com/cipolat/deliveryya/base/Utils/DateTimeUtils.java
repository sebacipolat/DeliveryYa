package com.cipolat.deliveryya.base.Utils;

import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sebastian on 23/09/17.
 */

public class DateTimeUtils {

    public static String parseDateTime(String dateString, String originalFormat, String outputFromat) {

        SimpleDateFormat formatter = new SimpleDateFormat(originalFormat, Locale.US);
        Date date = null;
        try {
            date = formatter.parse(dateString);

            SimpleDateFormat dateFormat = new SimpleDateFormat(outputFromat, new Locale("US"));

            return dateFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getRelativeTimeSpan(String dateString, String originalFormat) {

        SimpleDateFormat formatter = new SimpleDateFormat(originalFormat, Locale.US);
        Date date = null;
        try {
            date = formatter.parse(dateString);

            return DateUtils.getRelativeTimeSpanString(date.getTime()).toString();

        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean timeIsBiggerThan(String time1, String time2, String timeMask) {
        boolean isbigger = false;
        Date date1;
        Date date2;

        try {
            date1 = new SimpleDateFormat(timeMask).parse(time1);
            date2 = new SimpleDateFormat(timeMask).parse(time2);
            if (date1.compareTo(date2) >= 0)
                isbigger = true;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return isbigger;
    }
}
