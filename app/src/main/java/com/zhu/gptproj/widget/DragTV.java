package com.zhu.gptproj.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import java.util.Random;


/**
 * @Description TODO
 * @systemUser Zhuyuandong
 * @Author
 * @Date 05-24-2023 周三 15:58
 */
public class DragTV extends AppCompatTextView {

    private String thingContent;//显示内容
    private float startx;// down事件发生时，手指相对于view左上角x轴的距离
    private float starty;// down事件发生时，手指相对于view左上角y轴的距离
    private float endx; // move事件发生时，手指相对于view左上角x轴的距离
    private float endy; // move事件发生时，手指相对于view左上角y轴的距离
    private int left; // DragTV左边缘相对于父控件的距离
    private int top; // DragTV上边缘相对于父控件的距离
    private int right; // DragTV右边缘相对于父控件的距离
    private int bottom; // DragTV底边缘相对于父控件的距离
    private int hor; // 触摸情况下，手指在x轴方向移动的距离
    private int ver; // 触摸情况下，手指在y轴方向移动的距离

    public DragTV(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public DragTV(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }

    public DragTV(Context context) {
        this(context, null);
        // TODO Auto-generated constructor stub
    }

//    #00A8E8：这是一种清新的青色，具有轻松和冷静的感觉。
//            #009688：这是一种深绿色，给人带来平静和自然的感觉。
//            #3F51B5：这是一种浓郁的蓝紫色，它既冷峻又具有一定的深度。
//            #7986CB：这是一种柔和的紫色，给人一种宁静和温和的感觉。
//            #2196F3：这是一种明亮的天蓝色，适合用于轻快和现代的背景。
//            #4DD0E1：这是一种清新的浅蓝色，给人带来宁静和轻松的感觉。
    public DragTV(Context context, int color, String str){
        super(context,null);
        setText(str);
        int backColor = android.graphics.Color.parseColor("#009688");
        setBackgroundColor(backColor);
    }

    public void setThingContent(String str){
        thingContent=str;
    }





    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 手指刚触摸到屏幕的那一刻，手指相对于View左上角水平和竖直方向的距离:startX startY
                startx = event.getX();
                starty = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                // 手指停留在屏幕或移动时，手指相对与View左上角水平和竖直方向的距离:endX endY
                endx = event.getX();
                endy = event.getY();
                // 获取此时刻 View的位置。
                left = getLeft();
                top = getTop();
                right = getRight();
                bottom = getBottom();


                // 手指移动的水平距离
                hor = (int) (endx - startx);
                // 手指移动的竖直距离
                ver = (int) (endy - starty);
                // 当手指在水平或竖直方向上发生移动时，重新设置View的位置（layout方法）
                if (hor != 0 || ver != 0) {
                    layout(left + hor, top + ver, right + hor, bottom + ver);
                }
                break;
            case MotionEvent.ACTION_UP:
                Boolean isSlide=(left<=1||top<=1||right<=1||bottom<=1);
                left=left<=0?0:left;
                top=top<=0?0:top;
                right=top<=0?0:right;
                bottom=bottom<=0?0:bottom;
                layout(left + hor, top + ver, right + hor, bottom + ver);
                break;
            default:
                break;
        }
        return true;
    }
}
