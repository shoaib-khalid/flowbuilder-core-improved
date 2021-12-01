package com.kalsym.flowbuilder.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {

    public static String currentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date currentDate = new Date();
        String currentTimeStamp = sdf.format(currentDate);
        return currentTimeStamp;
    }

    public static String expiryTimestamp(int seconds) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.SECOND, seconds);
        Date expiryDate = c.getTime();
        return dateFormat.format(expiryDate);
    }
}
