package com.moon.widget.materialcalendarview;

import com.moon.widget.materialcalendarview.CalendarDay;
import com.moon.widget.materialcalendarview.DayViewFacade;

/**
 * Decorate Day views with drawables and text manipulation
 */
public interface DayViewDecorator {

  /**
   * Determine if a specific day should be decorated
   *
   * @param day {@linkplain com.moon.widget.materialcalendarview.CalendarDay} to possibly decorate
   * @return true if this decorator should be applied to the provided day
   */
  boolean shouldDecorate(CalendarDay day);

  /**
   * Set decoration options onto a facade to be applied to all relevant days
   *
   * @param view View to decorate
   */
  void decorate(DayViewFacade view);
}
