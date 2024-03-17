package com.moon.libbase.widget

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.forEach
import androidx.core.widget.TextViewCompat
import com.moon.libbase.R
import timber.log.Timber

/**
 * @author ry
 * @date 2020/5/15
 */
class XmToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = androidx.appcompat.R.attr.toolbarStyle
) : Toolbar(context, attrs, defStyleAttr) {
    private lateinit var centerTextView: TextView

    init {
        ensureCenterTextView()
        val layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
            Gravity.CENTER
        )
        addView(centerTextView, layoutParams)
    }

    private fun generatorTextStyle() {
        centerTextView.setTextColor(ContextCompat.getColor(context, R.color.xm_text_primary))
        centerTextView.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            context.resources.getDimension(R.dimen.xm_text_size_xl)
        )
        centerTextView.typeface= Typeface.DEFAULT_BOLD
        centerTextView.setSingleLine()
        centerTextView.ellipsize = TextUtils.TruncateAt.END
        val textAppearance = getReflectTextAppearance()
        if (textAppearance != 0) {
            TextViewCompat.setTextAppearance(centerTextView, textAppearance)
        }
        val textColor = getReflectTextColor()
        if (textColor != null) {
            centerTextView.setTextColor(textColor)
        }
        //centerTextView.typeface = Typeface.DEFAULT_BOLD
    }

    //反射获取文本颜色值
    private fun getReflectTextColor(): ColorStateList? {
        val declaredFields = Toolbar::class.java.declaredFields
        val field = declaredFields.find {
            it.name == "mTitleTextColor"
        }
        field?.isAccessible = true
        val colorStateList = field?.get(this) as? ColorStateList
        return colorStateList
    }

    //反射获取文本样式值
    private fun getReflectTextAppearance(): Int {
        val declaredFields = Toolbar::class.java.declaredFields
        val field = declaredFields.find {
            it.name == "mTitleTextAppearance"
        }
        field?.isAccessible = true
        val textAppearance = field?.get(this) as? Int
        return textAppearance ?: 0
    }

    override fun setTitle(title: CharSequence?) {
        //屏蔽父类方法
        super.setTitle(" ")
        ensureCenterTextView()
        centerTextView.text = title
    }


    private fun ensureCenterTextView() {
        if (!this::centerTextView.isInitialized) {
            centerTextView = TextView(context)
            generatorTextStyle()
        }
    }
}