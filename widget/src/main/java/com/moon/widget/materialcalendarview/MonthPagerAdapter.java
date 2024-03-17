package com.moon.widget.materialcalendarview;

import androidx.annotation.NonNull;

import com.moon.widget.materialcalendarview.CalendarDay;
import com.moon.widget.materialcalendarview.CalendarPagerAdapter;
import com.moon.widget.materialcalendarview.DateRangeIndex;
import com.moon.widget.materialcalendarview.MaterialCalendarView;
import com.moon.widget.materialcalendarview.MonthView;

import org.threeten.bp.Period;

/**
 * Pager adapter backing the calendar view
 */
public class MonthPagerAdapter extends CalendarPagerAdapter<com.moon.widget.materialcalendarview.MonthView> {

  MonthPagerAdapter(MaterialCalendarView mcv) {
    super(mcv);
  }

  @Override protected com.moon.widget.materialcalendarview.MonthView createView(int position) {
    return new com.moon.widget.materialcalendarview.MonthView(mcv, getItem(position), mcv.getFirstDayOfWeek(), showWeekDays);
  }

  @Override protected int indexOf(com.moon.widget.materialcalendarview.MonthView view) {
    com.moon.widget.materialcalendarview.CalendarDay month = view.getMonth();
    return getRangeIndex().indexOf(month);
  }

  @Override protected boolean isInstanceOfView(Object object) {
    return object instanceof com.moon.widget.materialcalendarview.MonthView;
  }

  @Override protected com.moon.widget.materialcalendarview.DateRangeIndex createRangeIndex(com.moon.widget.materialcalendarview.CalendarDay min, com.moon.widget.materialcalendarview.CalendarDay max) {
    return new Monthly(min, max);
  }

  public static class Monthly implements com.moon.widget.materialcalendarview.DateRangeIndex {

    /**
     * Minimum date with the first month to display.
     */
    private final com.moon.widget.materialcalendarview.CalendarDay min;

    /**
     * Number of months to display.
     */
    private final int count;

    public Monthly(@NonNull final com.moon.widget.materialcalendarview.CalendarDay min, @NonNull final com.moon.widget.materialcalendarview.CalendarDay max) {
      this.min = com.moon.widget.materialcalendarview.CalendarDay.from(min.getYear(), min.getMonth(), 1);
      this.count = indexOf(max) + 1;
    }

    @Override public int getCount() {
      return count;
    }

    @Override public int indexOf(final com.moon.widget.materialcalendarview.CalendarDay day) {
      return (int) Period
          .between(min.getDate().withDayOfMonth(1), day.getDate().withDayOfMonth(1))
          .toTotalMonths();
    }

    @Override public com.moon.widget.materialcalendarview.CalendarDay getItem(final int position) {
      return CalendarDay.from(min.getDate().plusMonths(position));
    }
  }
}
