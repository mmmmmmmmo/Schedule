package com.moon.libcommon.utils.extension

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.moon.libbase.utils.extension.showListItemAlert
import com.moon.libbase.utils.extension.showSingleItemAlert

/**
 * @author ry
 * @date 2020-01-11
 */


fun showMoonSingleItem(
    context: Context,
    items: Array<String>,
    listener: ((DialogInterface, Int) -> Unit)? = null,
    checkedItem: Int = -1,
    autoDismiss: Boolean = true
): AlertDialog {
    return showSingleItemAlert(
        context,
        items,
        checkedItem,
        DialogInterface.OnClickListener { dialog, which ->
            listener?.invoke(dialog, which)
            if (autoDismiss) dialog.dismiss()
        })
//    return showSingleItem(context, items, checkedItem, listener, R.style.MoonAlertDialogTheme)
}


fun showMoonListItem(
    context: Context,
    items: Array<String>,
    listener: DialogInterface.OnClickListener? = null
): AlertDialog {
    return showListItemAlert(context, items, listener)
}


