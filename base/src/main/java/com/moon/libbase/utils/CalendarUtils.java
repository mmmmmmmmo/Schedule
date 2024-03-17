package com.moon.libbase.utils;
// _ooOoo_  
// o8888888o  
// 88" . "88  
// (| -_- |)  
//  O\ = /O  
// ___/`---'\____  
// .   ' \\| |// `.  
// / \\||| : |||// \  
// / _||||| -:- |||||- \  
// | | \\\ - /// | |  
// | \_| ''\---/'' | |  
// \ .-\__ `-` ___/-. /  
// ___`. .' /--.--\ `. . __  
// ."" '< `.___\_<|>_/___.' >'"".  
// | | : `- \`.;`\ _ /`;.`/ - ` : | |  
// \ \ `-. \_ __\ /__ _/ .-` / /  
// ======`-.____`-.___\_____/___.-`____.-'======  
// `=---='  
//          .......................
//           佛曰：bug泛滥，我已瘫痪！

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Android-Week-View
 * create by NiMa.Wang on 2020/1/3
 */

public class CalendarUtils {
    public CalendarUtils() {
    }

    public static void copyDateTo(Calendar calendar1, Calendar calendar2) {
        int year = getYear(calendar1);
        int month = getMonth(calendar1);
        int day = getDay(calendar1);
        calendar2.clear();
        calendar2.set(year, month, day);
    }

    public static int getDay(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static int getDayOfWeek(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    @NonNull
    public static Calendar getInstance() {
        Calendar calendar = Calendar.getInstance();
        copyDateTo(calendar, calendar);
        return calendar;
    }

    public static Calendar getInstance(@Nullable Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }

        copyDateTo(calendar, calendar);
        return calendar;
    }

    public static int getMonth(Calendar calendar) {
        return calendar.get(Calendar.MONTH);
    }

    public static int getYear(Calendar calendar) {
        return calendar.get(Calendar.YEAR);
    }

    //跳到每年的第一天
    public static void setToFirstDay(Calendar calendar) {
        int year = getYear(calendar);
        int month = getMonth(calendar);
        calendar.clear();
        calendar.set(year, month, 1);
    }

}

