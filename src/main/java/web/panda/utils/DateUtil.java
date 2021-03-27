package web.panda.utils;

import java.util.Calendar;

public class DateUtil {
    public static int getDay(){
        Calendar calendar=Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        day = day==0?7:day;
        return day;
    }
}
