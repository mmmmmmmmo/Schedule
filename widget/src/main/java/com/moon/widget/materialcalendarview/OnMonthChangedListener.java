package com.moon.widget.materialcalendarview;

import com.moon.widget.materialcalendarview.CalendarDay;
import com.moon.widget.materialcalendarview.MaterialCalendarView;

/**
 * The callback used to indicate the user changes the displayed month
 */
public interface OnMonthChangedListener {

  /**
   * Called upon change of the selected day
   *
   * @param widget the view associated with this listener
   * @param date the month picked, as the first day of the month
   */
  void onMonthChanged(MaterialCalendarView widget, CalendarDay date);
}
