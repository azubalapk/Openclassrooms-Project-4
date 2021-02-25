package com.example.projet_3_oc_maru.utils;

import java.util.Calendar;

public class CurrentDateOrTimeUtil {
    static final Calendar c = Calendar.getInstance();
    static int mHour,mMinute,mYear,mMonth,mDay;

    public static void getCurrentDate(){
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
    }

    public static void getCurrentTime(){
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
    }
}
