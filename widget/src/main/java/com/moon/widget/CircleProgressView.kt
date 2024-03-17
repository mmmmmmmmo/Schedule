package com.moon.widget

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import com.moon.libbase.utils.ui.dp
import com.moon.widget.CircleProgressDrawable.ProgressOrientation.Companion.PROGRESS_ORITAITON_ANTICLOCKWISE
import com.moon.widget.CircleProgressDrawable.ProgressOrientation.Companion.PROGRESS_ORITATION_CLOCKWISE
import com.moon.widget.CircleProgressDrawable.StartPointType.Companion.START_ANGLE_TOP
import org.jetbrains.anko.backgroundDrawable


class CircleProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null, defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {
    private val bgDrawable: CircleProgressDrawable = CircleProgressDrawable(
        5,
        5.dp,
        Color.parseColor("#FFFFFF"),
        Color.parseColor("#FF9060"), START_ANGLE_TOP
    )

    init {
        backgroundDrawable = bgDrawable
    }

    fun setProgress(progress: Int, maxProgress: Int? = null) {
        if (maxProgress != null) {
            bgDrawable.setMaxProgress(maxProgress)
        }
        bgDrawable.curProgress = progress
    }

}