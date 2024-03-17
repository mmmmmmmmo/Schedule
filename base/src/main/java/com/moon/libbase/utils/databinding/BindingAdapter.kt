package com.moon.libbase.utils.databinding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.moon.libbase.utils.glide.BlurTransformation
import com.moon.libbase.utils.glide.BlurTransformation.Companion.DEFAULT_DOWN_SAMPLING
import com.moon.libbase.utils.glide.BlurTransformation.Companion.MAX_RADIUS
import com.moon.libbase.utils.ui.dp

/**
 * @author ry
 * @date 2019-05-21
 */

/**
 * 加载图片
 */
@BindingAdapter(
    value = ["loadUrl", "placeholder", "error", "circleCrop", "corners"],
    requireAll = false
)
fun loadUrl(
    view: ImageView,
    url: String?,
    placeholder: Drawable? = null,
    error: Drawable? = null,
    circleCrop: Boolean = false,
    corners: Int? = null
) {
    //填充占位图和错误图，如果没有错误图，已占位图展示
    var options = RequestOptions()
        .placeholder(placeholder)
        .error(error ?: placeholder)
    if (circleCrop) {
        options = options.circleCrop().override(Target.SIZE_ORIGINAL)
    }
    if (corners != null) {
        options = options.transform(RoundedCorners(corners.dp)).override(Target.SIZE_ORIGINAL)
    }
    val factory =
        DrawableCrossFadeFactory.Builder(500).setCrossFadeEnabled(true).build()
    Glide.with(view).load(url)
        .transition(DrawableTransitionOptions.withCrossFade(factory))
        .apply(options).into(view)
}


/**
 * 加载模糊图片
 * @param radius 模糊半径
 * @param sampling 取样点比例
 */
@BindingAdapter(value = ["loadBlurUrl", "radius", "sampling"], requireAll = false)
fun loadBlurUrl(view: ImageView, url: String?, radius: Int?, sampling: Int?) {
    if (url == null) return
    val builder = Glide.with(view).load(url)
        .transition(DrawableTransitionOptions.withCrossFade(500))
    val trans = BlurTransformation(
        radius ?: MAX_RADIUS,
        sampling ?: DEFAULT_DOWN_SAMPLING
    )
    val options: RequestOptions =
        RequestOptions.bitmapTransform(trans).override(Target.SIZE_ORIGINAL)
    builder.apply(options).into(view)
}