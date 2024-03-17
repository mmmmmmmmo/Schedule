package com.moon.widget

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import com.moon.widget.CircleProgressDrawable.ProgressOrientation.Companion.PROGRESS_ORITAITON_ANTICLOCKWISE
import com.moon.widget.CircleProgressDrawable.ProgressOrientation.Companion.PROGRESS_ORITATION_CLOCKWISE


class TestingCircleProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null, defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {
    // 总进度.
    private var mMaxProgress: Long = 0

    // 圆环宽度.
    private val mRingWidth: Int = 50

    // 开始角度.
    private val mStartAngle: Int = 270
    var padding: Int = 0

    companion object {
        private const val TAG = "TestingCircleProgressView"
    }

    // 画笔.
    private var mPaint: Paint = Paint()

    //三种检测结果
    private val healthColor: Int = resources.getColor(R.color.healthColor)
    private val observeColor: Int = resources.getColor(R.color.observeColor)
    private val seriousColor: Int = resources.getColor(R.color.seriousColor)
    private val lowColor: Int = resources.getColor(R.color.lowColor)
    private val yellowColor: Int = resources.getColor(R.color.yellowColor)
    private val color = arrayOf(healthColor,observeColor,seriousColor,lowColor,yellowColor)
    init {
        // 描边
        mPaint.style = Paint.Style.STROKE
        // 设置颜色
        mPaint.color = observeColor
        // 设置抗锯齿
        mPaint.isAntiAlias = true
        // 设置圆环宽度
        mPaint.strokeWidth = mRingWidth.toFloat()
        // 设置圆角
        mPaint.strokeCap = Paint.Cap.BUTT
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        canvas.drawColor(Color.parseColor("#00FFFFFF"))
        // 计算半径
        val radius: Int = (width.coerceAtMost(height) - mRingWidth - padding) / 2
        // 计算矩形位置.
        val offsetX: Int = (width - radius * 2) / 2
        val offsetY: Int = (height - radius * 2) / 2
        val rect = RectF(
            offsetX.toFloat(),
            offsetY.toFloat(),
            (offsetX + radius * 2).toFloat(),
            (offsetY + radius * 2).toFloat()
        )
        // 1. 绘制圆环
        mPaint.style = Paint.Style.STROKE
        // 计算角度.
        mPaint.strokeWidth = mRingWidth.toFloat()
        canvas.drawArc(rect, mStartAngle.toFloat(), 360.toFloat(), false, mPaint)
    }


    fun setColor(colorIndex: Int) {
        mPaint.color = color[colorIndex]
        invalidate()
    }

}