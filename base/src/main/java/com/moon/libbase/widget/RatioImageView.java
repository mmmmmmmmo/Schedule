package com.moon.libbase.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.appcompat.widget.AppCompatImageView;

import com.moon.libbase.R;

/**
 * @author ry
 * @date 2019-10-16
 * 宽高比 imageView
 */
public class RatioImageView extends AppCompatImageView {

    public static final float RATIO_DEF = 1.0f;
    float ratio;

    public RatioImageView(Context context) {
        this(context,null);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatioImageView);
        ratio = typedArray.getFloat(R.styleable.RatioImageView_ratio, RATIO_DEF);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        if (ratio != 0) {
            int ratioHeight = (int) (measuredWidth / ratio);
            setMeasuredDimension(measuredWidth, ratioHeight);
        }
    }
}
