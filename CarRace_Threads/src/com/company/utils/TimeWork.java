package com.company.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeWork {
    public static String convertToTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("s.SS");
        return sdf.format(new Date(time));
    }
}
