package com.moon.widget.utils

import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.RectF
import android.view.Gravity
import android.view.View
import android.view.View.GONE
import android.widget.Button
import android.widget.TextView
import com.app.hubert.guide.model.GuidePage
import com.app.hubert.guide.model.HighLight
import com.app.hubert.guide.model.HighlightOptions
import com.app.hubert.guide.model.RelativeGuide
import com.moon.libbase.utils.ui.dp
import com.moon.widget.R
import org.jetbrains.anko.find

/**
 * @author ry
 * @date 2019-10-15
 */
/**
 * 封装功能引导页page
 */
fun getCommonPage(
    view: View,
    padding: Int = 0,
    relativeLayout: Int? = null,
    gravity: Int = Gravity.TOP,
    expandSize: Float = 15.dp.toFloat()
): GuidePage {
    val builder = HighlightOptions.Builder().setOnHighlightDrewListener { canvas, rectF ->
        val paint = Paint()
        paint.color = Color.WHITE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 1.dp.toFloat()
        paint.pathEffect = DashPathEffect(floatArrayOf(30f, 5f), 0f)
        canvas.drawRect(expandRectF(rectF, expandSize), paint)
    }
    if (relativeLayout != null) {
        builder.setRelativeGuide(RelativeGuide(relativeLayout, gravity))
    }
    return GuidePage.newInstance()
        .addHighLightWithOptions(view, HighLight.Shape.RECTANGLE, 0, padding, builder.build())
        .setLayoutRes(R.layout.layout_guide)
        .setEverywhereCancelable(false)
        .setBackgroundColor(Color.parseColor("#6604040f"))
}

/**
 * 扩大虚线矩形绘制区域
 * @param rectF view的绘制区域
 * @param size 扩大的像素数量
 */
private fun expandRectF(rectF: RectF, size: Float): RectF {
    val result = RectF()
    result.left = rectF.left - size / 2
    result.top = rectF.top - size / 2
    result.right = rectF.right + size / 2
    result.bottom = rectF.bottom + size / 2
    return result
}

/**
 * 设置下一步和跳过的点击事件
 */
fun GuidePage.setListener(
    listIndex: Int, pageSize: Int, nextText: String,
    nextListener: () -> Unit, skipListener: () -> Unit
): GuidePage {
    return this.setOnLayoutInflatedListener { view, controller ->
        val nextBtn = view.find<Button>(R.id.btn_next)
        if (listIndex == pageSize - 1) {
            nextBtn.text = nextText
        }
        nextBtn.setOnClickListener {
            if (listIndex == pageSize - 1) {
                controller.remove()
            } else {
                controller.showPage(listIndex + 1)
            }
            nextListener.invoke()
        }
        val skipBtn = view.find<TextView>(R.id.tv_skip)
        skipBtn.setOnClickListener {
            controller.remove()
            skipListener.invoke()

        }
    }
}

fun GuidePage.setListener2(
    listIndex: Int, pageSize: Int, nextText: String,
    nextShow: Int=View.VISIBLE,
    skipShow: Int=View.VISIBLE,
    nextListener: () -> Unit,
    skipListener: () -> Unit
): GuidePage {
    return this.setOnLayoutInflatedListener { view, controller ->
        val nextBtn = view.find<Button>(R.id.btn_next)
        if (listIndex == pageSize - 1) {
            nextBtn.text = nextText
        }
        nextBtn.visibility=nextShow
        nextBtn.setOnClickListener {
            if (listIndex == pageSize - 1) {
                controller.remove()
            } else {
                controller.showPage(listIndex + 1)
            }
            nextListener.invoke()
        }
        val skipBtn = view.find<TextView>(R.id.tv_skip)
        skipBtn.visibility=skipShow
        skipBtn.setOnClickListener {
            controller.remove()
            skipListener.invoke()

        }
    }
}

fun GuidePage.setListenerGone(
    listIndex: Int, pageSize: Int, nextText: String,
    okListener: () -> Unit
): GuidePage {
    return this.setOnLayoutInflatedListener { view, controller ->
        val nextBtn = view.find<Button>(R.id.btn_next)
        if (listIndex == pageSize - 1) {
            nextBtn.text = nextText
        }
        nextBtn.visibility = GONE
        view.setOnClickListener {
            if (listIndex == pageSize - 1) {
                controller.remove()
            } else {
                controller.showPage(listIndex + 1)
            }
            okListener.invoke()
        }
        val skipBtn = view.find<TextView>(R.id.tv_skip)
        skipBtn.visibility = GONE
    }
}
