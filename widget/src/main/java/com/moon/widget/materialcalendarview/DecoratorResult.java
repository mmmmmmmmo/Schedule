package com.moon.widget.materialcalendarview;

import com.moon.widget.materialcalendarview.DayViewDecorator;
import com.moon.widget.materialcalendarview.DayViewFacade;

class DecoratorResult {
  public final DayViewDecorator decorator;
  public final com.moon.widget.materialcalendarview.DayViewFacade result;

  DecoratorResult(DayViewDecorator decorator, DayViewFacade result) {
    this.decorator = decorator;
    this.result = result;
  }
}
