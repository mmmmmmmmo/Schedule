package com.moon.widget

import com.moon.libbase.utils.DateUtils
import com.moon.widget.materialcalendarview.CalendarDay
import timber.log.Timber
import java.time.Year
import java.util.*

/**
 * @author ry
 * @date 2020-02-15
 */
fun CalendarDay.toCalendar(): Calendar {
    val instance = Calendar.getInstance()
    instance.set(this.year, this.month - 1, this.day)
    instance.set(Calendar.HOUR_OF_DAY, 0)
    instance.set(Calendar.MINUTE, 0)
    instance.set(Calendar.SECOND, 0)
    instance.set(Calendar.MILLISECOND, 0)
    return instance
}

fun Calendar.toCalendarDay(): CalendarDay {
    return CalendarDay.from(
        this[Calendar.YEAR],
        this[Calendar.MONTH] + 1,
        this[Calendar.DAY_OF_MONTH]
    )
}

fun Date.toCalendarDay(): CalendarDay {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return CalendarDay.from(
        calendar[Calendar.YEAR],
        calendar[Calendar.MONTH] + 1,
        calendar[Calendar.DAY_OF_MONTH]
    )
}

fun Calendar.getWeekFirstDay(): CalendarDay {
    this.add(
        Calendar.DAY_OF_WEEK,
        2 - DateUtils.getWeekDay(this.get(Calendar.DAY_OF_WEEK))
    ) //每周的第一天
    return CalendarDay.from(
        this[Calendar.YEAR],
        this[Calendar.MONTH] + 1,
        this[Calendar.DAY_OF_MONTH]
    )
}

fun Calendar.getTwoWeekFirstDay(): CalendarDay {
    this.add(
        Calendar.DAY_OF_WEEK,
        2 - DateUtils.getWeekDay(this.get(Calendar.DAY_OF_WEEK))
    ) //每周的第一天
    this.add(Calendar.DAY_OF_YEAR, -7)//往前减7天
    return CalendarDay.from(
        this[Calendar.YEAR],
        this[Calendar.MONTH] + 1,
        this[Calendar.DAY_OF_MONTH]
    )
}