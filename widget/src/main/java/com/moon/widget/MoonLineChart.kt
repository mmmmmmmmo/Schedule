package com.moon.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.utils.MPPointD


class MoonLineChart @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    LineChart(context, attrs) {
    var enableDrawBgColor = false
    var bgList: ArrayList<BgColor> = ArrayList()

    /**
     * 重写onDraw方法，注意绘制顺序，先绘制背景色，再绘制叉号，最后绘制图表
     * @param canvas
     */

    init {

    }

    override fun onDraw(canvas: Canvas) {
        drawBgColor(canvas)
        super.onDraw(canvas)
    }

    /**
     * 设置背景时应以BgColor为单位，将多个BgColor添加到一个ArrayList中
     * 最后通过此方法给图表加上颜色背景
     * @param arrayList 背景色集合
     */
    fun setBgColor(arrayList: ArrayList<BgColor>) {
        bgList = arrayList
    }

     fun drawBgColor(canvas: Canvas) {
        if (enableDrawBgColor) {
            if (bgList.isNotEmpty()) {
                val paint = Paint()
                for (r in bgList) {
                    val pStart: MPPointD = this.getPixelForValues(
//                        contentRect.left,
                        this.xChartMin,
                        r.start,
                        YAxis.AxisDependency.LEFT
                    )
                    val pStop: MPPointD = this.getPixelForValues(
//                        contentRect.top,
                        this.xChartMax,
                        r.stop,
                        YAxis.AxisDependency.LEFT
                    )
                    paint.color = r.color
                    canvas.drawRect(
                        RectF(
                            contentRect.left,
                            pStart.y.toFloat(),
                            contentRect.right,
                            pStop.y.toFloat()
                        ), paint
                    )
                }
            }
        }
    }
}

/**
 * @param start 起始点
 * @param stop 结束点
 * @param color 背景颜色
 */
class BgColor(var stop: Float, var start: Float, var color: Int)
