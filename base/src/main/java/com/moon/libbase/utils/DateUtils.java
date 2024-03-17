package com.moon.libbase.utils;


import android.content.res.Resources;
import android.util.Log;

import androidx.annotation.Nullable;

import com.moon.libbase.R;
import com.moon.libbase.base.BaseApplication;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


public class DateUtils {
    public static final long MILLIS_IN_MINUTES = 1000 * 60;
    public static final long MILLIS_IN_HOUR = MILLIS_IN_MINUTES * 60;
    public static final long MILLIS_IN_DAY = MILLIS_IN_HOUR * 24;
    public static final long MILLIS_IN_MONTH = MILLIS_IN_DAY * 30;
    public static final long MILLIS_IN_YEAR = MILLIS_IN_DAY * 365;


    public static final SimpleDateFormat EEE_D_MMM_YYYY_HH_MM_SS_Z_FORMAT = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
    public static final SimpleDateFormat EEE_MMMDD_YYYY_FORMAT = new SimpleDateFormat("EEE,MMMdd, yyyy", Locale.ENGLISH);
    public static final SimpleDateFormat YYYY_MM_DD_EEEE_CN_FORMAT = new SimpleDateFormat("yyyy年MM月dd日,EEEE", Locale.CHINA);
    public static final SimpleDateFormat YYYY_MM_DD_EEEE_JA_FORMAT = new SimpleDateFormat("yyyy年MM月dd日,EEEE", Locale.JAPAN);
    public static final SimpleDateFormat MMMDD_FORMAT = new SimpleDateFormat("MMMdd", Locale.ENGLISH);
    public static final SimpleDateFormat YYYY_MM_DD_EEE_HH_MM_FORMAT = new SimpleDateFormat("yyyy/MM/dd (EEE) HH:mm", Locale.ENGLISH);
    public static final SimpleDateFormat YYYY_MM_DD_HH_MM_SS_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    public static final SimpleDateFormat YYYY_MM_DD_EEE_FORMAT = new SimpleDateFormat("yyyy/MM/dd (EEE)", Locale.ENGLISH);
    public static final SimpleDateFormat EEE_YYYY_MM_DD_FORMAT = new SimpleDateFormat("(EEE) yyyy/MM/dd", Locale.ENGLISH);
    public static final SimpleDateFormat YYYY_MM_DD_HH_MM_CN_FORMAT = new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.ENGLISH);
    public static final SimpleDateFormat YYYY_MM_DD_HH_MM_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
    public static final SimpleDateFormat YYYY_MM_DD_HH_MM_SLASH_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ENGLISH);
    public static final SimpleDateFormat YYYY_MM_DD_HH_MM_SS_SLASH_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH);
    public static final SimpleDateFormat YYYY_MM_DD_CN_FORMAT = new SimpleDateFormat("yyyy年MM月dd日", Locale.ENGLISH);
    public static final SimpleDateFormat YYYY_MM_DD_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    public static final SimpleDateFormat YYYY_MM_DD_SLASH_FORMAT = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
    public static final SimpleDateFormat YYYY_MM_DD_DOT_FORMAT = new SimpleDateFormat("yyyy.MM.dd", Locale.ENGLISH);
    public static final SimpleDateFormat YYYY_MM_DD_HH_MM_DOT_FORMAT = new SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.ENGLISH);
    public static final SimpleDateFormat YY_MM_DD_FORMAT = new SimpleDateFormat("yy-MM-dd", Locale.ENGLISH);
    public static final SimpleDateFormat YYYY_MM_FORMAT = new SimpleDateFormat("yyyy年MM月", Locale.ENGLISH);
    public static final SimpleDateFormat YYYY_MM_DOT_FORMAT = new SimpleDateFormat("yyyy.MM", Locale.ENGLISH);
    public static final SimpleDateFormat YYYY_MM_NOT_ALL_FORMAT = new SimpleDateFormat("yyyy/MM", Locale.ENGLISH);
    public static final SimpleDateFormat MM_DD_CN_FORMAT = new SimpleDateFormat("MM月dd日", Locale.CHINA);
    public static final SimpleDateFormat MM_DD_JA_FORMAT = new SimpleDateFormat("MM月dd日", Locale.JAPAN);
    public static final SimpleDateFormat MM_DD_DOT_FORMAT = new SimpleDateFormat("MM.dd", Locale.ENGLISH);
    public static final SimpleDateFormat MM_DD_CN_FORMAT_S = new SimpleDateFormat("MM/dd", Locale.ENGLISH);
    public static final SimpleDateFormat MM_DD_HH_MM_FORMAT = new SimpleDateFormat("MM-dd HH:mm", Locale.ENGLISH);
    public static final SimpleDateFormat MM_DD_HH_MM_CN_FORMAT = new SimpleDateFormat("MM月dd日 HH:mm", Locale.ENGLISH);
    public static final SimpleDateFormat HH_MM_SS_FORMAT = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
    public static final SimpleDateFormat HH_MM_CN_FORMAT = new SimpleDateFormat("aa hh:mm", Locale.CHINA);
    public static final SimpleDateFormat HH_MM_JA_FORMAT = new SimpleDateFormat("aa hh:mm", Locale.JAPAN);
    public static final SimpleDateFormat HH_MM12_FORMAT = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
    public static final SimpleDateFormat MM_SS_FORMAT = new SimpleDateFormat("mm:ss", Locale.ENGLISH);
    public static final SimpleDateFormat MM_CN_FORMAT = new SimpleDateFormat("MM月", Locale.ENGLISH);
    public static final SimpleDateFormat M_CN_FORMAT = new SimpleDateFormat("M月", Locale.ENGLISH);
    public static final SimpleDateFormat HH_MM_FORMAT_CH = new SimpleDateFormat("HH:mm", Locale.CHINA);

    public static String getTimestampString(long timeMillis) {
        boolean isToady = android.text.format.DateUtils.isToday(timeMillis);
        String dateString;
        if (isToady) {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            dateString = formatter.format(timeMillis);
            dateString = dateString.substring(0, 5);
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            dateString = formatter.format(timeMillis);
            dateString = dateString.substring(2, dateString.length());

        }
        return dateString;

    }

    public static String getHoursMin(long timeMillis) {
        String dateString;
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
        dateString = formatter.format(timeMillis);
        dateString = dateString.substring(0, 5);
        return dateString;
    }

    public static String getTimeOfFormat(long timeMillis, SimpleDateFormat simpleDateFormat) {
        String timeformate;
        if (timeMillis <= 0L) {
            timeformate = "";
        } else {
            timeformate = format(new Date(timeMillis), simpleDateFormat);
        }
        return timeformate;
    }

    public static String format(Date date, SimpleDateFormat simpleDateFormat) {
        String timeformate;
        try {
            timeformate = simpleDateFormat.format(date);
        } catch (Exception e) {
            timeformate = "";
        }
        return timeformate;
    }

    public static String getTimeOfFormat(long timeMillis, String formatString) {
        return new SimpleDateFormat(formatString, Locale.getDefault()).format(timeMillis);
    }

    public static boolean isCloseEnough(long msgTime, long msgTime1) {
        if (Math.abs(msgTime1 - msgTime) < 30 * 1000 * 60)
            return true;
        return false;
    }

    @Nullable
    public static Date formatTimeDate(String time) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //几点到几点
    public static String formatBETime(long start, long end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(start);
        StringBuilder dateString = new StringBuilder();
        dateString.append(calendar.get(Calendar.HOUR_OF_DAY));
        dateString.append(":");
        dateString.append(formatTime(calendar.get(Calendar.MINUTE)));
        dateString.append(" - ");
        calendar.setTimeInMillis(end);
        dateString.append(calendar.get(Calendar.HOUR_OF_DAY));
        dateString.append(":");
        dateString.append(formatTime(calendar.get(Calendar.MINUTE)));
        return dateString.toString();
    }

    //几月几号，周几  几点到几点
    public static String formatBETime(String timestr, int start, int end) {
        long time = Long.parseLong(timestr);
        StringBuilder dateString = new StringBuilder();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ", Locale.getDefault());
        dateString.append(formatter.format(time));
        dateString.append(getWeek(time));
        dateString.append(getConvertTime(start));
        dateString.append(" - ");
        dateString.append(getConvertTime(end));
        return dateString.toString();
    }

    //2020/01/15(周二)16:00-18:00
    public static String formatWeekBETime(long time, int start, int end) {
        StringBuilder dateString = new StringBuilder();
        dateString.append(getTimeOfFormat(time, YYYY_MM_DD_EEE_FORMAT));
        dateString.append(getConvertTime(start));
        dateString.append(" - ");
        dateString.append(getConvertTime(end));
        return dateString.toString();
    }

    public static String formatBETime(long time, int start, int end) {
        StringBuilder dateString = new StringBuilder();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ", Locale.getDefault());
        dateString.append(formatter.format(time));
        dateString.append(getWeek(time));
        dateString.append(getConvertTime(start));
        dateString.append(" - ");
        dateString.append(getConvertTime(end));
        return dateString.toString();
    }

    //开始时间，几点到几点
    public static String formatBETime(long time, long start, long end) {
        StringBuilder dateString = new StringBuilder();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ", Locale.getDefault());
        dateString.append(formatter.format(time));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(start);
        dateString.append(calendar.get(Calendar.HOUR_OF_DAY));
        dateString.append(":");
        dateString.append(formatTime(calendar.get(Calendar.MINUTE)));
        dateString.append(" - ");
        calendar.setTimeInMillis(end);
        dateString.append(calendar.get(Calendar.HOUR_OF_DAY));
        dateString.append(":");
        dateString.append(formatTime(calendar.get(Calendar.MINUTE)));
        return dateString.toString();
    }

    //分钟数
    public static String formatBETime(int start, int end) {
        StringBuilder dateString = new StringBuilder();
        dateString.append(getConvertTime(start));
        dateString.append(" - ");
        dateString.append(getConvertTime(end));
        return dateString.toString();
    }

    public static String getTodayString() {
        return new SimpleDateFormat("yy/MM/dd").format(System.currentTimeMillis());
    }

    public static String getTodayString(SimpleDateFormat simpleDateFormat) {
        return simpleDateFormat.format(System.currentTimeMillis());
    }

    public static int getHourInterval(long msgTime) {
        long now = System.currentTimeMillis();
        long length = msgTime - now;
        int interval;
        if (length <= 0) {
            interval = 0;
        } else {
            interval = (int) (length / MILLIS_IN_HOUR) + 1;
        }
        return interval;
    }

    /**
     * 是否是新的一天
     *
     * @param ms1 老时间
     * @param ms2 新时间
     * @return
     */
    public static boolean isNewDay(final long ms1, final long ms2) {
        return (ms2 / MILLIS_IN_DAY) != (ms1 / MILLIS_IN_DAY);
    }

    /**
     * 是否同一天
     * @param ms1
     * @param ms2
     * @return
     */
    public static boolean isOldDay(final long ms1, final long ms2) {
        return (ms2 / MILLIS_IN_DAY) == (ms1 / MILLIS_IN_DAY);
    }

    public static boolean isSameDay(final long ms1, final long ms2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(ms1);
        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(ms2);
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
                && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 是否在同一月
     */
    public static boolean isSameMonth(final long ms1, final long ms2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(ms1);
        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(ms2);
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH);
    }

    /**
     * 年月日格式化
     *
     * @param year
     * @param month
     * @param day
     * @return
     */

    public static String getCalendarString(int year, int month, int day) {
        GregorianCalendar date = new GregorianCalendar(year, month, day);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date.getTime());
    }

    public static String getCalendarString(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(time);
    }


    public static String secToTime(long seconds) {
        long day = seconds / (3600 * 24);
        long hour = (seconds - day * 3600 * 24) / 3600;
        long minute = (seconds - day * 3600 * 24 - hour * 3600) / 60;
        long second = (seconds - day * 3600 * 24 - hour * 3600 - minute * 60);

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "天");
        }
        sb.append(hour + ":");

        if (minute > 0 && minute < 10) {
            sb.append("0" + minute + ":");
            if (second > 0 && second < 10) {
                sb.append("0" + second);
            } else if (second > 9) {
                sb.append(second);
            } else if (second == 0) {
                sb.append("00");
            }
        } else if (minute > 9) {
            sb.append(minute + ":");
            if (second > 0 && second < 10) {
                sb.append("0" + second);
            } else if (second > 9) {
                sb.append(second);
            } else if (second == 0) {
                sb.append("00");
            }
        } else {
            if (second > 0 && second < 10) {
                sb.append("00:0" + second);
            } else if (second > 9) {
                sb.append("00:" + second);
            } else if (second == 0) {
                sb.append("00:00");
            }
        }


        return sb.toString();
    }

    public static String secToTimeString(long seconds) {
        long day = seconds / (3600 * 24);
        long hour = (seconds - day * 3600 * 24) / 3600;
        long minute = (seconds - day * 3600 * 24 - hour * 3600) / 60;
        long second = (seconds - day * 3600 * 24 - hour * 3600 - minute * 60);

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day).append("天");
        }
        if (hour > 0) {
            sb.append(hour).append("小时");
        }
        if (minute > 0) {
            sb.append(minute).append("分");
        }
        sb.append(second).append("秒");
        return sb.toString();
    }

    public static int getConstellation(int month, int day) {
        int[] DayArr = {22, 20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23};  // 两个星座分割日
        int index = (month) % 12;
        if (day < DayArr[index]) {
            index = index - 1;
        }
        if (index < 0) {
            return index + 12;
        }
        return index % 12;
    }


    public static boolean chekTimeThreeday(long uploadtime, long nowtime, long error_time) {
        if ((nowtime - uploadtime) > error_time) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据时间转成对应的时分格式
     *
     * @param time 以分单位的时间，如100分钟
     * @return 时分格式字符串 如"01：40"
     */
    public static String getConvertTime(long time) {

        return getConvertHour(time) +
                ":" +
                getConvertMin(time);
    }

    /**
     * 格式化时间
     */
    public static String formatTime(long time) {
        StringBuilder str = new StringBuilder(2);
        if (0 <= time && time <= 9) {
            str.append("0");
        }
        str.append(time);
        return str.toString();
    }

    public static String getConvertHour(long time) {
        return formatTime(getHour(time));
    }


    public static String getConvertMin(long time) {
        return formatTime(getMin(time));
    }

    //将分钟数转换成当天具体时间戳
    public static long covertDayTime(long Mintime, long daytime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(daytime);
        calendar.set(Calendar.HOUR_OF_DAY, (int) getHour(Mintime));
        calendar.set(Calendar.MINUTE, (int) getMin(Mintime));
        return calendar.getTimeInMillis();
    }

    /**
     * 时分转成 分
     */
    public static long convertToTime(int hour, int min) {
        return hour * 60 + min;
    }

    public static long getHour(long time) {
        return time / 60;
    }

    public static long getMin(long time) {
        return time % 60;
    }

    public static String getWeek(long time) {

        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date(time));
        int week = cd.get(Calendar.DAY_OF_WEEK); //获取星期
        String weekString;
        Resources resources = BaseApplication.Companion.getInstance().getResources();
        switch (week) {
            case Calendar.SUNDAY:
                weekString = resources.getString(R.string.week_cart_sunday);
                break;
            case Calendar.MONDAY:
                weekString = resources.getString(R.string.week_cart_monday);
                break;
            case Calendar.TUESDAY:
                weekString = resources.getString(R.string.week_cart_tuesday);
                break;
            case Calendar.WEDNESDAY:
                weekString = resources.getString(R.string.week_cart_wednesday);
                break;
            case Calendar.THURSDAY:
                weekString = resources.getString(R.string.week_cart_thursday);
                break;
            case Calendar.FRIDAY:
                weekString = resources.getString(R.string.week_cart_friday);
                break;
            case Calendar.SATURDAY:
                weekString = resources.getString(R.string.week_cart_saturday);
                break;
            default:
                weekString = "";
        }

        return weekString;
    }

    public static int getWeekDay(int day) {
        return day == 1 ? 8 : day;
    }

    //根据时间戳得到当月第一天的时间戳
    public static long getMonthTime(long time) {
        Calendar calendar = Calendar.getInstance();
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        calendar.setTimeZone(tz);
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static long getMonthTimeOfToday() {
        return getMonthTime(System.currentTimeMillis());
    }

    /**
     * 当天时间，只包括日期，不包括时间
     *
     * @return
     */
    public static long getDateWithNoTime() {
        return getDateWithNoTime(System.currentTimeMillis());
    }

    /**
     * 当天时间，只包括日期，不包括时间
     */
    public static long getDateWithNoTime(long time) {
        Calendar calendar = Calendar.getInstance();
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        calendar.setTimeZone(tz);
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    //根据当前时间获取本周的周一
    public static long getWeekMondayTime(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return calendar.getTimeInMillis();
    }

    //根据当前时间获取本周的周一
    public static long getWeekMondayTime() {
        return getWeekMondayTime(System.currentTimeMillis());
    }

    //根据当前时间获取本周的周日
    public static long getWeekSundayTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return calendar.getTimeInMillis();
    }

    /**
     * 根据时间获取本周周几
     *
     * @param time   时间戳
     * @param start  自然周起始位置，比如Calendar.MONDAY代表以周一为起始的自然周
     * @param select 需要获取的周位置
     */
    public static long getWeekEndTime(long time, int start, int select) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        //先算出周起始位置
        long startTime;
        if (calendar.get(Calendar.DAY_OF_WEEK) >= start) {
            calendar.set(Calendar.DAY_OF_WEEK, start);
            startTime = calendar.getTimeInMillis();
        } else {
            calendar.set(Calendar.DAY_OF_WEEK, start);
            startTime = calendar.getTimeInMillis() - MILLIS_IN_DAY * 7;
        }
        //再根据起始位置差算出对应周位置
        if (select < start) {
            select = select + 7;
        }
        return startTime + MILLIS_IN_DAY * (select - start);
    }


    public static long getLeftTime(long time) {
        return (time - getDateWithNoTime()) / MILLIS_IN_DAY + 1;
    }

    /**
     * @param format     具体的字符串格式, 例如 YYYY_MM_DD_SLASH_FORMAT
     * @param dateString 包含日期的字符串，例如 2020/01/01
     * @return 时间戳
     */
    public static long string2TimeStamp(SimpleDateFormat format, String dateString) {
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    // 根据时间戳获取当天开始时候的时间戳
    public static long getTodayStartTime(long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    // 根据时间戳获取第二天开始时候的时间戳
    public static long getNextDayStartTime(long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis() + 1;
    }

    //过去离今天是多少天,time传入0点时间戳
    public static int dayfromnow(long time) {
        return (int) ((getDateWithNoTime() - getTodayStartTime(time)) / MILLIS_IN_DAY);
    }

    //当前是几点钟
    public static long getHourOfDay(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static String getSelectDate(int year, int month, int dayOfMonth, SimpleDateFormat simpleDateFormat) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String SelectDay = simpleDateFormat.format(c.getTime());
        return SelectDay;
    }

    /**
     * 是否最近7天，可获取历史数据
     * @param dayFormat
     * @param simpleDateFormat
     * @return
     */
    public static boolean isEnableHistory(String dayFormat, SimpleDateFormat simpleDateFormat) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = simpleDateFormat.parse(dayFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);

        if(c.getTimeInMillis()>System.currentTimeMillis()){
            return false;
        }

        if (Math.abs(System.currentTimeMillis() - c.getTimeInMillis()) > MILLIS_IN_DAY*7)
            return false;
        return true;
    }

    //获取CurrentDay日期前一天
    public static String getCurrentDayBefore(String dayFormat, SimpleDateFormat simpleDateFormat) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = simpleDateFormat.parse(dayFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = simpleDateFormat.format(c.getTime());
        return dayBefore;
    }

    //获取CurrentDay日期后一天
    public static String getCurrentDayAfter(String dayFormat, SimpleDateFormat simpleDateFormat) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = simpleDateFormat.parse(dayFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = simpleDateFormat.format(c.getTime());
        return dayAfter;
    }

    //根据当前时间获取本周日期(自然周)
    public static String getDayWeek(SimpleDateFormat simpleDateFormat) {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DATE);
        String monday = "";
        String sunday = "";
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {//星期天
            sunday = simpleDateFormat.format(c.getTime());
            c.set(Calendar.DATE, day - 6);
            monday = simpleDateFormat.format(c.getTime());
        } else {
            c.set(Calendar.DATE, day - c.get(Calendar.DAY_OF_WEEK) + 2);
            int day1 = c.get(Calendar.DATE);
            monday = simpleDateFormat.format(c.getTime());
            c.set(Calendar.DATE, day1 + 6);
            sunday = simpleDateFormat.format(c.getTime());
        }

        return monday + "-" + sunday;
    }

    public static String getSelectDayWeek(int year, int month, int dayOfMonth, SimpleDateFormat simpleDateFormat) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        c.get(Calendar.WEEK_OF_MONTH);
        int day = c.get(Calendar.DATE);

        String monday = "";
        String sunday = "";
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {//星期天
            sunday = simpleDateFormat.format(c.getTime());
            c.set(Calendar.DATE, day - 6);
            monday = simpleDateFormat.format(c.getTime());
        } else {
            //星期一
            c.set(Calendar.DATE, day - c.get(Calendar.DAY_OF_WEEK) + 2);
            int day1 = c.get(Calendar.DATE);
            monday = simpleDateFormat.format(c.getTime());
            //星期天
            c.set(Calendar.DATE, day1 + 6);
            sunday = simpleDateFormat.format(c.getTime());
        }

        return monday + "-" + sunday;
    }

    //获取上一周日期
    public static String getCurrentWeekBefore(String weekFormat, SimpleDateFormat simpleDateFormat) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            String[] list = weekFormat.split("-");
            if (list == null || list.length <= 0) {
                return null;
            }
            date = simpleDateFormat.parse(list[0]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int week = c.get(Calendar.WEEK_OF_MONTH);
        c.set(Calendar.WEEK_OF_MONTH, week - 1);

        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String monday = simpleDateFormat.format(c.getTime());

        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 6);
        String sunday = simpleDateFormat.format(c.getTime());

        return monday + "-" + sunday;
    }

    //获取下一周日期
    public static String getCurrentWeekAfter(String weekFormat, SimpleDateFormat simpleDateFormat) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            String[] list = weekFormat.split("-");
            if (list == null || list.length <= 0) {
                return null;
            }
            date = simpleDateFormat.parse(list[0]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int week = c.get(Calendar.WEEK_OF_MONTH);
        c.set(Calendar.WEEK_OF_MONTH, week + 1);

        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String monday = simpleDateFormat.format(c.getTime());

        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 6);
        String sunday = simpleDateFormat.format(c.getTime());

        return monday + "-" + sunday;
    }

    //上个月
    public static String getCurrentMonthBefore(String monthFormat, SimpleDateFormat simpleDateFormat) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = simpleDateFormat.parse(monthFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, month - 1);

        String monthAfter = simpleDateFormat.format(c.getTime());
        return monthAfter;
    }

    //下个月
    public static String getCurrentMonthAfter(String monthFormat, SimpleDateFormat simpleDateFormat) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = simpleDateFormat.parse(monthFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, month + 1);

        String monthAfter = simpleDateFormat.format(c.getTime());
        return monthAfter;
    }

    //时间间隔
    public static String getCurrentTimeSlot(int time) {
        int timeAfter;
        timeAfter = time + 1;
        if (timeAfter > 24) {
            timeAfter = 1;
        }

        return time + ":00-" + timeAfter + ":00";
    }

    //当前月
    public static int getCurrentMonth(String monthFormat, SimpleDateFormat simpleDateFormat) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = simpleDateFormat.parse(monthFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int month = c.get(Calendar.MONTH);

        return month + 1;
    }

    //获取当前日期YYYY_MM_DD_SLASH_FORMAT格式
    public static String getCurrentDate(String dayFormat, SimpleDateFormat simpleDateFormat) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = simpleDateFormat.parse(dayFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        String currentDate = YYYY_MM_DD_SLASH_FORMAT.format(c.getTime());
        return currentDate;
    }

    //获取当月第一天日期
    public static String getCurrentMonthFirst(String formatString, SimpleDateFormat simpleDateFormat) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = simpleDateFormat.parse(formatString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        c.set(Calendar.DATE, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return YYYY_MM_DD_SLASH_FORMAT.format(c.getTime());
    }

    //获取当月最后一天日期
    public static String getCurrentMonthEnd(String formatString, SimpleDateFormat simpleDateFormat) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = simpleDateFormat.parse(formatString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        c.set(Calendar.DATE, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return YYYY_MM_DD_SLASH_FORMAT.format(c.getTime());
    }

    //获取当月最后一天日期
    public static int getCurrentMonthMax(String formatString, SimpleDateFormat simpleDateFormat) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = simpleDateFormat.parse(formatString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int max = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        return max;
    }

    //获取日期是周几
    public static int getCurrentHourOfDay(Date date) {
        Calendar c = Calendar.getInstance();

        c.setTime(date);
        int index = c.get(c.HOUR_OF_DAY);
        return index;
    }

    public static int getCurrentHourOfDay(long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        int index = c.get(c.HOUR_OF_DAY);
        return index;
    }

    //获取日期是周几
    public static int getCurrentDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();

        c.setTime(date);
        int index = c.get(c.DAY_OF_WEEK);
        if (index == 1) {
            index = 8;
        }
        return index - 1;
    }

    //获取日期是周几
    public static int getCurrentDayOfWeek(long time) {
        Calendar c = Calendar.getInstance();

        c.setTimeInMillis(time);
        int index = c.get(c.DAY_OF_WEEK);
        if (index == 1) {
            index = 8;
        }
        return index - 1;
    }

    //获取日期是本月第几天
    public static int getCurrentDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();

        c.setTime(date);
        int index = c.get(c.DAY_OF_MONTH);
        return index;
    }

    //获取数据显示时间
    public static String getCurrentShowDay(String formatString, SimpleDateFormat simpleDateFormat,SimpleDateFormat toSimpleDateFormat) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = simpleDateFormat.parse(formatString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        return toSimpleDateFormat.format(c.getTime());
    }

    //Date转时间戳
    public static long getMonthTimeFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    //时间转时间戳
    public static long getTimeFromDate(String formatString, SimpleDateFormat simpleDateFormat) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = simpleDateFormat.parse(formatString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        return c.getTimeInMillis();
    }

    public static String getNewFormatDateString(long time,SimpleDateFormat newFormat) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        return newFormat.format(c.getTime());
    }

    public static String getDateMin(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long resultTime = calendar.getTimeInMillis();
        Log.d("wlf", "getDateMin: " + resultTime);
        return getTimeOfFormat(resultTime, YYYY_MM_DD_HH_MM_SS_FORMAT);
    }

    public static String getDateMax(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(calendar.DATE, 1);
        long resultTime = calendar.getTimeInMillis();
        Log.d("wlf", "getDateMax: " + resultTime);
        return getTimeOfFormat(resultTime, YYYY_MM_DD_HH_MM_SS_FORMAT);
    }
}
