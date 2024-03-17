package com.moon.libbase.utils

import java.util.*

/**
 * 时间
 */
class TimeUtils {

    /**
     * 当天显示：具体时间
    昨天显示：昨天
    超出昨天，仍在本周，显示周几
    超出本周，显示具体日期
     */
    companion object {
        fun getTimeStr(time: Long): String {
            val target = Calendar.getInstance()
            target.timeInMillis = time
            val now = Calendar.getInstance()
            return if (time > System.currentTimeMillis() || now.get(Calendar.WEEK_OF_YEAR) != target.get(
                    Calendar.WEEK_OF_YEAR
                )
            ) {
                DateUtils.getTimeOfFormat(time, DateUtils.YY_MM_DD_FORMAT)
            } else {
                if (now.get(Calendar.DAY_OF_YEAR) == target.get(Calendar.DAY_OF_YEAR)) {
                    DateUtils.getHoursMin(time)
                } else {
                    DateUtils.getWeek(time)
                }
            }
        }

    }


}