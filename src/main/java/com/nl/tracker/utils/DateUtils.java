package com.nl.tracker.utils;

import java.sql.Time;
import java.text.ParseException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by levin1 on 1/30/2017.
 */
public class DateUtils {
    private static String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
    private static String UTC_ZONE = "UTC";

    public static Date millisToDate(long millis, TimeZone zone) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(UTC_ZONE));
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);

        if (zone == null) {
            df.setTimeZone(TimeZone.getTimeZone(UTC_ZONE));
            Date date = cal.getTime();
            return date;
        }

        return null;
    }

    public static Date getCurrentUTCDate() throws ParseException {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(UTC_ZONE));
        return cal.getTime();
    }

    public static Date getUTCDate(String time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT);
        DateFormat df = sdf1;
        sdf1.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = df.parse(sdf1.format(sdf.parse(time)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
