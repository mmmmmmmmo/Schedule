package com.moon.widget

import android.graphics.*
import android.graphics.drawable.Drawable
import com.moon.widget.CircleProgressDrawable.ProgressOrientation.Companion.PROGRESS_ORITAITON_ANTICLOCKWISE
import com.moon.widget.CircleProgressDrawable.ProgressOrientation.Companion.PROGRESS_ORITATION_CLOCKWISE


class CircleProgressDrawable(
    // 总进度.
    private var mMaxProgress: Int,
    // 圆环宽度.
    private val mRingWidth: Int,
    //实际进度的颜色
    private val ringColor: Int,
    //未填充的进度颜色
    var unringColor: Int,
    // 开始角度.
    private val mStartAngle: Int,
    var mOritation: Int = PROGRESS_ORITATION_CLOCKWISE,
    var padding: Int = 0
) :
    Drawable() {
    companion object {
        private const val TAG = "CircleProgressDrawable"
    }

    // 画笔.
    private var mPaint: Paint

    // 当前进度.
    private var mCurProgress = 0

    var arrow: Drawable? = null
   // var arrowtint: Int? = null

    init {
        mPaint = Paint()
        // 描边
        mPaint.style = Paint.Style.STROKE
        // 设置颜色
        mPaint.color = ringColor
        // 设置抗锯齿
        mPaint.isAntiAlias = true
        // 设置圆环宽度
        mPaint.strokeWidth = mRingWidth.toFloat()
        // 设置圆角
        mPaint.strokeCap = Paint.Cap.ROUND
    }

    /**
     * 进度条开始位置.
     */
    interface StartPointType {
        companion object {
            // 右
            const val START_ANGLE_RIGHT = 0

            // 下
            const val START_ANGLE_BOTTOM = 90

            // 左
            const val START_ANGLE_LEFT = 180

            // 上
            const val START_ANGLE_TOP = 270
        }
    }

    /**
     * 进度条方向
     */
    interface ProgressOrientation {
        companion object {
            // 顺时针.
            const val PROGRESS_ORITATION_CLOCKWISE = 0x00

            // 逆时针.
            const val PROGRESS_ORITAITON_ANTICLOCKWISE = 0x01
        }
    }

    fun setOrientation(orientation: Int) {
        mOritation =
            if (orientation == ProgressOrientation.PROGRESS_ORITAITON_ANTICLOCKWISE || orientation == ProgressOrientation.PROGRESS_ORITATION_CLOCKWISE) {
                orientation
            } else {
                throw RuntimeException("Orientation value must be from interface ProgressOrientation !")
            }
        this.curProgress = curProgress
    }

    /**
     * 设置进度百分比 .
     * @param curProgress 当前进度.
     */
    var curProgress: Int
        get() = mCurProgress
        set(curProgress) {
            // 赋值
            mCurProgress = if (mOritation == ProgressOrientation.PROGRESS_ORITATION_CLOCKWISE) {
                // 顺时针.
                if (curProgress > mMaxProgress) mMaxProgress else curProgress
            } else {
                // 逆时针
                -if (curProgress > mMaxProgress) mMaxProgress else curProgress
            }
            // 重绘
            invalidateSelf()
        }

    override fun draw(canvas: Canvas) {

        canvas.drawColor(Color.parseColor("#00FFFFFF"))
        // 获取尺寸
        val bounds: Rect = bounds
        // 计算半径
        val radius: Int = (Math.min(bounds.width(), bounds.height()) - mRingWidth - padding) / 2
        // 计算矩形位置.
        val offsetX: Int = (bounds.width() - radius * 2) / 2
        val offsetY: Int = (bounds.height() - radius * 2) / 2
        val rect = RectF(
            offsetX.toFloat(),
            offsetY.toFloat(),
            (offsetX + radius * 2).toFloat(),
            (offsetY + radius * 2).toFloat()
        )
        if (arrow != null) {
            val width = radius / 2
            val left = offsetX + radius - width / 2
            val rect = Rect(
                left,
                offsetY + radius / 2,
                (left + width),
                (offsetY + radius + radius / 2)
            )
            /*arrowtint?.let {
                arrow!!.setTint(it)
                Timber.d("arrowtint" + arrowtint)
            }*/
            arrow!!.bounds = rect
            arrow!!.draw(canvas)
        }
        // 1. 绘制圆环
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = mRingWidth.toFloat()
        mPaint.color = unringColor
        // 计算角度.
        val angle = (mCurProgress * 1.0 / mMaxProgress * 360).toInt()
        //以3点钟为0点，270度是最正上方12点位置的度数，angle是扫过多少度
        canvas.drawArc(rect, (angle - 90).toFloat(), 360 - angle.toFloat(), false, mPaint)
        // 2. 绘制进度圆环.
        mPaint.strokeWidth = mRingWidth.toFloat()
        mPaint.color = ringColor
        canvas.drawArc(rect, mStartAngle.toFloat(), angle.toFloat(), false, mPaint)
        // 3. 绘制内圆
        /*  val innerMaxCircleRadius = radius - mRingWidth / 2 + 1.toFloat()
          val innerCurCircleRadius: Float =
              (innerMaxCircleRadius * (mCurProgress * 1.0 / mMaxProgress)).toFloat()
          mPaint.style = Paint.Style.FILL
          canvas.drawCircle(centerX.toFloat(),
              centerY.toFloat(), Math.abs(innerCurCircleRadius), mPaint)*/
    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSPARENT
    }

    override fun getIntrinsicHeight(): Int {
        return bounds.height()
    }

    override fun getIntrinsicWidth(): Int {
        return bounds.width()
    }

    fun setMaxProgress(maxProgress: Int) {
        mMaxProgress = maxProgress
    }

}