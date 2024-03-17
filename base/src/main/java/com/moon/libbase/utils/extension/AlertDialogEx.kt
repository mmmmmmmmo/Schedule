package com.moon.libbase.utils.extension

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.IdRes
import com.moon.libbase.R

/**
 * @author ry
 * @date 2020-01-10
 */
fun showNormalAlert(
    context: Context,
    title: CharSequence? = null,
    message: CharSequence? = null,
    posListener: DialogInterface.OnClickListener? = null,
    canListener: DialogInterface.OnClickListener? = null,
    cancelable: Boolean = true,
    posText: String? = null,
    negText: String? = null
): AlertDialog {
    return AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(posText ?: context.getString(R.string.ok), posListener)
        .setNegativeButton(negText ?: context.getString(R.string.cancel), canListener)
        .setCancelable(cancelable)
        .show()
}

fun showNormalAlertWithResId(
    context: Context,
    title: Int? = null,
    message: Int? = null,
    posListener: DialogInterface.OnClickListener? = null,
    canListener: DialogInterface.OnClickListener? = null,
    cancelable: Boolean = true,
    posTextId: Int? = null,
    negTextId: Int? = null
): AlertDialog {
    val titleString = if (title == null) null else context.getString(title)
    val messageString = if (message == null) null else context.getString(message)
    val posTextString = if (posTextId == null) null else context.getString(posTextId)
    val negTextString = if (negTextId == null) null else context.getString(negTextId)

    return showNormalAlert(
        context,
        titleString,
        messageString,
        posListener,
        canListener,
        cancelable,
        posTextString,
        negTextString
    )
}

fun AlertDialog.setTextColor(@IdRes id: Int, @ColorInt color: Int) {
    val view = findViewById<TextView?>(id)
    view?.setTextColor(color)
}

fun AlertDialog.setPositiveTextColor(@ColorInt color: Int) {
    setTextColor(android.R.id.button1, color)
}


fun AlertDialog.setNegativeTextColor(@ColorInt color: Int) {
    setTextColor(android.R.id.button2, color)
}


fun showSingleItemAlert(
    context: Context,
    items: Array<String>,
    checkedItem: Int = -1,
    listener: DialogInterface.OnClickListener? = null,
    themeId: Int = 0
): AlertDialog {
    return AlertDialog.Builder(context, themeId)
        .setSingleChoiceItems(items, checkedItem, listener)
        .show()
}

fun showListItemAlert(
    context: Context,
    items: Array<String>,
    listener: DialogInterface.OnClickListener? = null,
    themeId: Int = 0
): AlertDialog {
    return AlertDialog.Builder(context, themeId)
        .setItems(items,listener)
        .show()
}
