package com.moon.libbase.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.InputFilter
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.moon.libbase.R
import com.moon.libbase.utils.ui.dp
import com.moon.libbase.utils.ui.sp
import timber.log.Timber

/**
 * @author ry
 * @date 2019-12-31
 */
class XmEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = androidx.appcompat.R.attr.editTextStyle
) : AppCompatEditText(context, attrs, defStyleAttr) {

    private var textLength: Int
    private val isLimit
        get() = textLength > 0
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    //是否是显示当前文本长度/总长度
    private var showCurrentLength = false
    private var drawLimit = true

    init {
        val array =
            context.obtainStyledAttributes(attrs, R.styleable.XmEditText, defStyleAttr, 0)
        textLength = array.getInteger(R.styleable.XmEditText_limitLength, -1)
        showCurrentLength = array.getBoolean(R.styleable.XmEditText_showCurLength, false)
        drawLimit = array.getBoolean(R.styleable.XmEditText_drawLimit, true)
        array.recycle()

        textPaint.color = ContextCompat.getColor(getContext(), R.color.xm_text_secondary)
        textPaint.textSize = 11f.sp



        setLengthFilter()
    }

    fun setLimitLength(length: Int) {
        textLength = length
        setLengthFilter()
    }

    private fun setLengthFilter() {
        val curFilter = filters

        val newFilter = if (textLength > 0) {
            replaceFilterInstance(curFilter, InputFilter.LengthFilter(textLength))
        } else {
            delFilterInstance(curFilter, InputFilter.LengthFilter::class.java)
        }
        filters = newFilter
    }

    private fun replaceFilterInstance(
        curFilter: Array<InputFilter>,
        addFilter: InputFilter
    ): Array<InputFilter> {
        val index = curFilter.indexOfFirst {
            it::class.java == InputFilter.LengthFilter::class.java
        }
        if (index == -1) {
            return curFilter + addFilter
        }
        curFilter[index] = InputFilter.LengthFilter(textLength)
        return curFilter
    }

    private fun delFilterInstance(
        curFilter: Array<InputFilter>,
        filterClass: Class<out InputFilter>
    ): Array<InputFilter> {
        val index = curFilter.indexOfFirst {
            it::class.java == filterClass
        }
        if (index == -1) return curFilter

        System.arraycopy(curFilter, index + 1, curFilter, index, curFilter.size - 1 - index)
        return Array(filters.size - 1) { i -> curFilter[i] }
    }


    override fun onDraw(canvas: Canvas) {
        //先画剩余文字长度
        if (drawLimit && isLimit) {
            drawLimitText(canvas)
        }
        super.onDraw(canvas)
    }


    private fun drawLimitText(canvas: Canvas) {
        val curLength = text?.length ?: 0
        val drawText = if (showCurrentLength) {
            "${curLength}/${textLength}"
        } else {
            (textLength - curLength).toString()
        }
        val textWidth = textPaint.measureText(drawText)

        if(curLength>=textLength){
            textPaint.color = ContextCompat.getColor(context, R.color.xm_text_error)
        }else{
            textPaint.color = ContextCompat.getColor(context, R.color.xm_text_secondary)
        }
        canvas.drawText(
            drawText,
            width - textWidth - paddingRight,
            height.toFloat() - 4.dp,
            textPaint
        )
    }
}