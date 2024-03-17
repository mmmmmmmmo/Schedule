package com.moon.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

class SleepView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {
    var paint: Paint = Paint()
    var dataList = arrayListOf<SleepData>()
    var total = 0L

    //三种睡眠状态
    private val deepColor: Int = resources.getColor(R.color.deepColor)
    private val lightColor: Int = resources.getColor(R.color.lightColor)
    private val soberColor: Int = resources.getColor(R.color.soberColor)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (dataList.isEmpty()) {
            return
        }
        var preX = 0f
        var endx = 0f
        dataList.forEach {
            when (it.type) {
                SleepType.DEEP -> paint.color = deepColor
                SleepType.LIGHT -> paint.color = lightColor
                SleepType.SOBER -> paint.color = soberColor
            }
            endx = preX + it.time.toFloat() * width / total
            Log.d("wlf", "preX=$preX  ,  endx=$endx")
            if (endx > preX) {
                canvas?.drawRect(preX, 0f, endx, height.toFloat(), paint)
                preX = endx
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    fun setSleepDataList(sleepDataList: List<SleepData>) {
        if (sleepDataList.isNullOrEmpty()) {
            return
        }
        dataList.clear()
        dataList.addAll(sleepDataList)
        total = dataList.sumByLong { it.time }
        invalidate()
    }
}

inline fun <T> Iterable<T>.sumByLong(selector: (T) -> Long): Long {
    var sum = 0L
    for (element in this) {
        sum += selector(element)
    }
    return sum
}

class SleepData(var time: Long = 0, var type: Int = 0)
class SleepType {
    companion object {
        const val DEEP = 1//深睡
        const val LIGHT = 2//浅睡
        const val SOBER = 3//清醒
    }
}