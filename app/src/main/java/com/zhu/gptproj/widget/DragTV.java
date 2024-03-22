package com.zhu.gptproj.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
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
    private int
            lastX, lastY,//记录上一次的x,y坐标
            mLeft, mTop, mRight, mBottom,//记录当前元素的的上下左右(相对于父元素)
            startX, startY,//记录初始x,y坐标
            endX, endY;//移动结束x,y坐标，用于计算是否点击事件
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
    public DragTV(Context context, int color, String str) {
        super(context, null);
        setText(str);
        setPadding(20, 10, 20, 10);
        int backColor = android.graphics.Color.parseColor("#009688");
        setBackgroundColor(backColor);
    }

    public void setThingContent(String str) {
        thingContent = str;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        int action = event.getAction();
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();

        switch (action) {
            case MotionEvent.ACTION_DOWN://按下记录初始坐标
                startX = lastX = rawX;
                startY = lastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:

                //计算偏移量
                int dx = rawX - lastX;
                int dy = rawY - lastY;

                /*
                //限制只能x轴方向拖动
                if (onlyX)
                {
                    dy = 0;
                //限制只能y轴方向拖动
                }else if (onlyY)
                {
                    dx = 0;
                }*/

                //计算控件坐标距离父控件原点各方向距离
                mLeft = getLeft() + dx;
                mRight = getRight() + dx;
                mTop = getTop() + dy;
                mBottom = getBottom() + dy;

                layout(mLeft, mTop, mRight, mBottom);

                //拖动时背景半透明
                //setBackground(getResources().getDrawable(R.drawable.draglinearlayout_press));

                lastX = rawX;
                lastY = rawY;

                break;
            case MotionEvent.ACTION_UP:

                endX = rawX;
                endY = rawY;

                //拖动完毕背景恢复
                //setBackground(getResources().getDrawable(R.drawable.draglinearlayout_up));

                //点击事件
                if (Math.abs(endX - startX) < 3 || Math.abs(endY - startY) < 3) {
                    this.performClick();
                }

                //拖动完毕，更新view在父布局中的位置参数，避免刷新父布局view又回到原点
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getLayoutParams();
                params.setMargins(mLeft, mTop, 0, 0);

                //这里指定下宽高，否则如果布局文件指定了match_parent/wrap_content，会导致自动伸缩宽高
                params.width = getMeasuredWidth();
                params.height = getMeasuredHeight();

                setLayoutParams(params);

                break;
        }

        return true;
    }
}
