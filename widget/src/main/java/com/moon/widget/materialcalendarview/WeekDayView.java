package com.moon.widget.materialcalendarview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.moon.widget.R;
import com.moon.widget.materialcalendarview.format.WeekDayFormatter;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;

import timber.log.Timber;

/**
 * Display a day of the week
 */
@SuppressLint("ViewConstructor") class WeekDayView extends AppCompatTextView {

  private WeekDayFormatter formatter = WeekDayFormatter.DEFAULT;
  private DayOfWeek dayOfWeek;

  public WeekDayView(final Context context, final LocalDate localDate) {
    super(context);
    dayOfWeek=localDate.getDayOfWeek();
    setGravity(Gravity.CENTER);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      setTextAlignment(TEXT_ALIGNMENT_CENTER);
    }

    setDayOfWeek(dayOfWeek);

    if(localDate.compareTo(CalendarDay.today().getDate())==0){
      Drawable drawable=getResources().getDrawable(R.drawable.today_line);
      drawable.setBounds( 0, 0, drawable.getMinimumWidth(),drawable.getMinimumHeight());
      setCompoundDrawablePadding(0);
      setCompoundDrawables(null,null,null,drawable );
    }
  }

  public void setWeekDayFormatter(@Nullable final WeekDayFormatter formatter) {
    this.formatter = formatter == null ? WeekDayFormatter.DEFAULT : formatter;
    setDayOfWeek(dayOfWeek);
  }

  public void setDayOfWeek(final DayOfWeek dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
    setText(formatter.format(dayOfWeek));
  }
}
