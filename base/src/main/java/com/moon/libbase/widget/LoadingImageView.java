package com.moon.libbase.widget;


import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

public class LoadingImageView extends AppCompatImageView {

    public LoadingImageView(Context context) {
        super(context);
    }

    public LoadingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getVisibility() == VISIBLE) {
            animator(true);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animator(false);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (VISIBLE == visibility) {
            animator(true);
        } else {
            animator(false);
        }
    }

    /**
     * 启动或停止动画
     *
     * @param start true为启动动画； false为停止动画
     */
    public void animator(boolean start) {
        try {
            AnimationDrawable animationDrawable = (AnimationDrawable) getDrawable();
            if (animationDrawable == null) {
                return;
            }
            animationDrawable.stop();
            if (start) {
                animationDrawable.start();
            }
        } catch (Exception e) {

        }
    }
}
