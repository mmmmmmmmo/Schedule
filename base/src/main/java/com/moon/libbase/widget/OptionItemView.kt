package com.moon.libbase.widget

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.moon.libbase.R
import com.moon.libbase.utils.extension.setMargin
import com.moon.libbase.utils.ui.dp
import org.jetbrains.anko.find
import org.jetbrains.anko.textColor

/**
 * @author ry
 * @date 2019-12-19
 */
class OptionItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        //单独一行
        private const val LOCATION_SINGLE = 0

        //多行下第一行位置
        private const val LOCATION_START = 1

        //多行下中间行位置
        private const val LOCATION_MIDDLE = 2

        //多行下最后行位置
        private const val LOCATION_END = 3
    }

    private val titleView: TextView
    private val subtitleView: TextView
    private val imageView: ImageView
    private val subImageView: ImageView
    private val divider: View
    private val indicatorView: ImageView
    private val update_dra: ImageView
    private val update_num: TextView
    private val rootItemView: View

    init {
        val inflate = View.inflate(getContext(), R.layout.option_item_view, this)
        titleView = inflate.find(R.id.titleView)
        subtitleView = inflate.find(R.id.subtitle)
        imageView = inflate.find(R.id.imageView)
        subImageView = inflate.find(R.id.sub_imageView)
        divider = inflate.find(R.id.divider)
        update_dra = inflate.find(R.id.update_dra)
        update_num = inflate.find(R.id.update_num)
        indicatorView = inflate.find(R.id.indicatorView)
        rootItemView = inflate.find(R.id.root)
        val typeArray = context.obtainStyledAttributes(
            attrs, R.styleable.OptionItemView, defStyleAttr, 0
        )
        val icon = typeArray.getDrawable(R.styleable.OptionItemView_oiv_icon)
        val subIcon = typeArray.getDrawable(R.styleable.OptionItemView_oiv_subicon)
        val icon_visibility = typeArray.getInt(R.styleable.OptionItemView_oiv_icon_visibility, 0)
        val title = typeArray.getString(R.styleable.OptionItemView_oiv_title)
        val subtitle = typeArray.getString(R.styleable.OptionItemView_oiv_subtitle)
        val subtitleHint = typeArray.getString(R.styleable.OptionItemView_oiv_subtitlehint)
        val showArrow = typeArray.getBoolean(R.styleable.OptionItemView_oiv_arrow, true)
        val showDivider = typeArray.getBoolean(R.styleable.OptionItemView_oiv_divider, false)
        val showRed = typeArray.getBoolean(R.styleable.OptionItemView_oiv_red, false)
        val location = typeArray.getInt(R.styleable.OptionItemView_oiv_location, LOCATION_SINGLE)
        typeArray.recycle()

        imageView.setImageDrawable(icon)
        if (subIcon != null) {
            subImageView.visibility = View.VISIBLE
            subImageView.setImageDrawable(subIcon)
        } else {
            subImageView.visibility = View.GONE
        }
        titleView.text = title
        subtitleView.text = subtitle
        subtitleView.hint = subtitleHint
        if (!showArrow) {
            indicatorView.visibility = View.GONE
        }
        if (!showDivider) {
            divider.visibility = View.GONE
        }
        if (showRed) {
            update_dra.visibility = View.VISIBLE
        }
        setLocationWithType(location)
        if (icon_visibility == 0) {
            imageView.visibility = View.VISIBLE
        } else if (icon_visibility == 1) {
            imageView.visibility = View.INVISIBLE
        } else if (icon_visibility == 2) {
            imageView.visibility = View.GONE
        }
    }

    private fun setLocationWithType(location: Int) {
        val radius = 10f.dp
        //设置左右边距

        val radiusArray = FloatArray(8)

        when (location) {
            LOCATION_SINGLE -> {
                radiusArray.fill(radius)
                rootItemView.setMargin(bottom = 10.dp)
            }
            LOCATION_START -> {
                radiusArray.fill(radius, toIndex = 4)
            }
            LOCATION_MIDDLE -> {
            }
            LOCATION_END -> {
                radiusArray.fill(radius, 4)
                rootItemView.setMargin(bottom = 10.dp)
            }
        }
        val drawable = ShapeDrawable(RoundRectShape(radiusArray, null, null))
        drawable.paint.color = Color.WHITE
        rootItemView.background = drawable
    }

    fun setTitle(name: String?) {
        titleView.text = name
    }

    fun setSubTitle(subTitle: String) {
        subtitleView.text = subTitle
    }

    fun setSubTitle(subTitle: String, color:Int) {
        subtitleView.text = subTitle
        subtitleView.textColor = color
    }

    fun getSubTitle(): String {
        return (subtitleView.text ?: "").toString()
    }

    fun setRedVisiable(boolean: Boolean, value: Int) {
        if (boolean) {
            update_dra.visibility = View.VISIBLE
            update_num.visibility = View.VISIBLE
            update_num.text = value.toString()
        } else {
            update_dra.visibility = View.GONE
            update_num.visibility = View.GONE
        }
    }

    fun setIconTint(tint: ColorStateList) {
        imageView.imageTintList = tint
    }

    fun setIconWidth(width: Int) {
        val layoutParams = imageView.layoutParams
        layoutParams.width = width
        imageView.layoutParams = layoutParams
    }

    fun setIconHeight(height: Int) {
        val layoutParams = imageView.layoutParams
        layoutParams.height = height
        imageView.layoutParams = layoutParams
    }

    fun showArrow(show: Boolean) {
        if (show) {
            indicatorView.visibility = View.VISIBLE
        } else {
            indicatorView.visibility = View.GONE
        }
    }

    fun getSubImageView(): ImageView {
        return subImageView
    }

}