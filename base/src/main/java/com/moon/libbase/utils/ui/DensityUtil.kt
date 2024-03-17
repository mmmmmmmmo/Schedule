package com.moon.libbase.utils.ui

import android.content.res.Resources
import android.util.TypedValue

/**
 * @author ry
 * @date 2019-05-24
 */
private val metrics = Resources.getSystem().displayMetrics

//dp2px
val Float.dp: Float
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, metrics)

val Int.dp: Int
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), metrics).toInt()

//sp2px
val Float.sp: Float
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, metrics)

val Int.sp: Int
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), metrics).toInt()

val Number.px: Number
    get() = this
//px2dp
val Number.px2dp: Int
    get() = (this.toFloat() / metrics.density).toInt()
//px2sp
val Number.px2sp: Int
    get() = (this.toFloat() / metrics.scaledDensity).toInt()

val screenWidth = metrics.widthPixels

val screenHeight = metrics.heightPixels
