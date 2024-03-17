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

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Date;

/**
 * Android-Week-View
 * create by NiMa.Wang on 2020/1/3
 */

public final class CalendarDay implements Parcelable {

    private transient Calendar _calendar;
    private transient Date _date;
    private final int day;
    private final int month;
    private final int year;

    @Deprecated
    public CalendarDay() {
        this(CalendarUtils.getInstance());
    }

    @Deprecated
    public CalendarDay(int var1, int var2, int var3) {
        year = var1;
        month = var2;
        day = var3;
    }

    public CalendarDay(Parcel var1) {
        this(var1.readInt(), var1.readInt(), var1.readInt());
    }

    @Deprecated
    public CalendarDay(Calendar var1) {
        this(CalendarUtils.getYear(var1), CalendarUtils.getMonth(var1), CalendarUtils.getDay(var1));
    }

    @Deprecated
    public CalendarDay(Date var1) {
        this(CalendarUtils.getInstance(var1));
    }

    public static final Creator<CalendarDay> CREATOR = new Creator<CalendarDay>() {
        @Override
        public CalendarDay createFromParcel(Parcel in) {
            return new CalendarDay(in);
        }

        @Override
        public CalendarDay[] newArray(int size) {
            return new CalendarDay[size];
        }
    };

    @NonNull
    public static CalendarDay from(int var0, int var1, int var2) {
        return new CalendarDay(var0, var1, var2);
    }

    public static CalendarDay from(@Nullable Calendar calendar) {
        if (calendar!=null){
            return from(CalendarUtils.getYear(calendar), CalendarUtils.getMonth(calendar), CalendarUtils.getDay(calendar));
        }
        return null;
    }

    public static CalendarDay from(@Nullable Date date) {
        if (date!=null){
            return from(CalendarUtils.getInstance(date));
        }
        return null;
    }

    private static int hashCode(int var0, int var1, int var2) {
        return var0 * 10000 + var1 * 100 + var2;
    }

    @NonNull
    public static CalendarDay today() {
        return from(CalendarUtils.getInstance());
    }

    public void copyTo(@NonNull Calendar calendar) {
        calendar.clear();
        calendar.set(year, month, day);
    }

    void copyToMonthOnly(@NonNull Calendar calendar) {
        calendar.clear();
        calendar.set(year, month, 1);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(day);
        dest.writeInt(month);
        dest.writeInt(year);
    }

    public boolean equals(Object calendarDay) {

        if(this==calendarDay){
            return true;
        }else{
            if(calendarDay != null && getClass() == calendarDay.getClass()){
                CalendarDay calendar = (CalendarDay) calendarDay;
                if (day == calendar.day && month == calendar.month&& year == calendar.year) {
                    return true;
                }
            }
            return false;
        }
    }

    @NonNull
    public Calendar getCalendar() {
        if (_calendar == null) {
            _calendar = CalendarUtils.getInstance();
            copyTo(_calendar);
        }

        return _calendar;
    }

    @NonNull
    public Date getDate() {
        if (_date == null) {
            _date = getCalendar().getTime();
        }

        return _date;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int hashCode() {
        return hashCode(year, month, day);
    }

    public boolean isAfter(@NonNull CalendarDay calendarDay) {
        boolean isafter = true;
        if (year == calendarDay.year) {
            if (month == calendarDay.month) {
                if (day <= calendarDay.day) {
                    isafter = false;
                }
            } else if (month <= calendarDay.month) {
                isafter = false;
            }
        } else if (year <= calendarDay.year) {
            isafter = false;
        }

        return isafter;
    }

    public boolean isBefore(@NonNull CalendarDay calendarDay) {
        boolean isbefore = true;
        if (calendarDay == null) {
            throw new IllegalArgumentException("other cannot be null");
        } else {
            if (year == calendarDay.year) {
                if (month == calendarDay.month) {
                    if (day >= calendarDay.day) {
                        isbefore = false;
                    }
                } else if (month >= calendarDay.month) {
                    isbefore = false;
                }
            } else if (year >= calendarDay.year) {
                isbefore = false;
            }

            return isbefore;
        }
    }

    public boolean isInRange(@Nullable CalendarDay start, @Nullable CalendarDay end) {
        if (start != null && end != null) {
            if (!start.isAfter(this) && !end.isBefore(this)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String toString() {
        return "CalendarDay{" + year + "-" + month + "-" + day + "}";
    }



}
