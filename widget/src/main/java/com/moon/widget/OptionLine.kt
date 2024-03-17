package com.moon.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.view.marginStart
import com.moon.libbase.R
import com.moon.libbase.utils.extension.setMargin
import com.moon.libbase.utils.ui.dp
import org.jetbrains.anko.find

class OptionLine @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        const val TYPE_NORMAL = 1
        const val TYPE_AVATAR = 2

        //单独一行
        const val LOCATION_SINGLE = 0

        //多行下第一行位置
        const val LOCATION_START = 1

        //多行下中间行位置
        const val LOCATION_MIDDLE = 2

        //多行下最后行位置
        const val LOCATION_END = 3
    }

    private val titleView: TextView
    private val subtitleView: TextView
    private val indicatorView: ImageView
    private val avatarView: ImageView
    private val rootLineView: ConstraintLayout

    init {
        View.inflate(getContext(), R.layout.option_line, this).apply {
            titleView = find(R.id.titleView)
            subtitleView = find(R.id.subtitle)
            indicatorView = find(R.id.indicatorView)
            avatarView = find(R.id.avatarView)
            rootLineView = find(R.id.rootView)
        }

        val typeArray = context.obtainStyledAttributes(
            attrs, R.styleable.OptionLine, defStyleAttr, 0
        )
        val title = typeArray.getString(R.styleable.OptionLine_ol_title)
        val subtitle = typeArray.getString(R.styleable.OptionLine_ol_subtitle)
        val lineType = typeArray.getInt(R.styleable.OptionLine_ol_type, TYPE_NORMAL)
        val location = typeArray.getInt(R.styleable.OptionLine_ol_location, LOCATION_SINGLE)
        val showArrow = typeArray.getBoolean(R.styleable.OptionLine_ol_arrow, true)
        typeArray.recycle()

        titleView.text = title
        if (!subtitle.isNullOrEmpty()) {
            subtitleView.text = subtitle
        }

        setViewWithType(lineType)
        setLocationWithType(location)
        showArrow(showArrow)
    }

    /**
     * 根据位置类型设置不同的背景
     */
    private fun setLocationWithType(location: Int) {
        val radius = 10f.dp
        //设置左右边距

        val radiusArray = FloatArray(8)

        when (location) {
            LOCATION_SINGLE -> {
                radiusArray.fill(radius)
                rootLineView.setMargin(bottom = 10.dp)
            }
            LOCATION_START -> {
                radiusArray.fill(radius, toIndex = 4)
            }
            LOCATION_MIDDLE -> {
            }
            LOCATION_END -> {
                radiusArray.fill(radius, 4)
                rootLineView.setMargin(bottom = 10.dp)
            }
        }
        val drawable = ShapeDrawable(RoundRectShape(radiusArray, null, null))
        drawable.paint.color = Color.WHITE
        rootLineView.background = drawable
    }

    /**
     *  根据类型隐藏相应控件
     */
    private fun setViewWithType(type: Int) {
        when (type) {
            TYPE_NORMAL -> {
                avatarView.isGone = true
            }
            TYPE_AVATAR -> {
                subtitleView.isGone = true
            }
        }
    }

    fun showArrow(show: Boolean) {
        indicatorView.isVisible = show
    }

    fun changeType(type: Int) {
        setViewWithType(type)
    }

    fun setSubTitle(subtitle: String?) {
        subtitleView.text = subtitle
    }

    fun getSubTitle(): String {
        return subtitleView.text?.toString() ?: ""
    }

    fun getAvatarView(): ImageView {
        return avatarView
    }


}