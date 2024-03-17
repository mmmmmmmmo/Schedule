package com.moon.widget.stateview;

import android.animation.Animator;
import android.view.View;

/**
 * @author ry
 * @date 2019-12-21
 */
public interface AnimatorProvider {
    Animator showAnimation(View view);

    Animator hideAnimation(View view);
}
