package com.moon.widget

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import com.moon.widget.CircleProgressDrawable.ProgressOrientation.Companion.PROGRESS_ORITAITON_ANTICLOCKWISE
import com.moon.widget.CircleProgressDrawable.ProgressOrientation.Companion.PROGRESS_ORITATION_CLOCKWISE


class SleepCircleProgressView @JvmOverloads constructor(
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
        private const val TAG = "CircleProgressDrawable"
    }

    // 画笔.
    private var mPaint: Paint = Paint()

    //三种睡眠状态
    private val deepColor: Int = resources.getColor(R.color.deepColor)
    private val lightColor: Int = resources.getColor(R.color.lightColor)
    private val soberColor: Int = resources.getColor(R.color.soberColor)

    private var deepProgress: Long = 0//深睡时间
    private var lightProgress: Long = 0//浅睡时间
    private var soberProgress: Long = 0//清醒时间

    init {
        // 描边
        mPaint.style = Paint.Style.STROKE
        // 设置颜色
        mPaint.color = lightColor
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
//        mPaint.strokeWidth = mRingWidth.toFloat()
//        mPaint.color = lightColor
        // 计算角度.
        val lightangle = (lightProgress * 1.0 / mMaxProgress * 360).toInt()
        //以3点钟为0点，270度是最正上方12点位置的度数，angle是扫过多少度
//        canvas.drawArc(rect, (lightangle - 90).toFloat(), 360 - lightangle.toFloat(), false, mPaint)
        // 2. 绘制进度圆环.
        mPaint.strokeWidth = mRingWidth.toFloat()
        mPaint.color = lightColor
        canvas.drawArc(rect, mStartAngle.toFloat(), lightangle.toFloat(), false, mPaint)

        val deepangle = (deepProgress * 1.0 / mMaxProgress * 360).toInt()
        mPaint.strokeWidth = mRingWidth.toFloat()
        mPaint.color = deepColor
        canvas.drawArc(
            rect,
            mStartAngle.toFloat() + lightangle,
            deepangle.toFloat(),
            false,
            mPaint
        )

        val soberAngle = 360 - lightangle - deepangle
        mPaint.strokeWidth = mRingWidth.toFloat()
        mPaint.color = soberColor
        canvas.drawArc(
            rect,
            mStartAngle.toFloat() + lightangle + deepangle,
            soberAngle.toFloat(),
            false,
            mPaint
        )

        // 3. 绘制内圆
        /*  val innerMaxCircleRadius = radius - mRingWidth / 2 + 1.toFloat()
          val innerCurCircleRadius: Float =
              (innerMaxCircleRadius * (mCurProgress * 1.0 / mMaxProgress)).toFloat()
          mPaint.style = Paint.Style.FILL
          canvas.drawCircle(centerX.toFloat(),
              centerY.toFloat(), Math.abs(innerCurCircleRadius), mPaint)*/
    }


    fun setProgress(deep: Long, light: Long, sober: Long) {
        deepProgress = deep
        lightProgress = light
        soberProgress = sober
        mMaxProgress = deepProgress + lightProgress + soberProgress
        invalidate()
    }

}