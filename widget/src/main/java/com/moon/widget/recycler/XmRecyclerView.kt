package com.moon.widget.recycler

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.moon.widget.R

/**
 * @author ry
 * @date 2020-02-13
 */
class XmRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : RecyclerView(context, attrs) {

    var maxHeight = 0

    init {
        val array =
            context.obtainStyledAttributes(attrs, R.styleable.XmRecyclerView)
        maxHeight = array.getDimensionPixelSize(R.styleable.XmRecyclerView_xmMaxHeight, 0)
        array.recycle()
    }

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        var maxHeightSpec = heightSpec
        if (maxHeight > 0) {
            maxHeightSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST)
        }
        super.onMeasure(widthSpec, maxHeightSpec)
    }
}