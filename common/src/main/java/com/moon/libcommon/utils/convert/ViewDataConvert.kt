package com.moon.libcommon.utils.convert

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.moon.libbase.utils.databinding.loadUrl
import com.moon.libcommon.R
import com.moon.libcommon.http.ApiConfig


/**
 * @author ry
 * @date 2020-01-14
 */

@BindingAdapter(value = ["loadAvatarUrl", "circleCrop"], requireAll = false)
fun loadAvatarUrl(
    view: ImageView,
    url: String?, circleCrop: Boolean? = true
) {
    val defIcon = ContextCompat.getDrawable(view.context, R.drawable.icon_default)
    loadUrl(view, ApiConfig.getResource(url), defIcon, defIcon, circleCrop ?: true)
}






