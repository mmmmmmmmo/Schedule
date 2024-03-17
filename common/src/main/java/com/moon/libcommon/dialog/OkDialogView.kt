package com.moon.libcommon.dialog

import android.content.Context
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.TextView
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.CenterPopupView
import com.moon.libcommon.R

class OkDialogView constructor(context: Context) : CenterPopupView(context) {
    var title: String = ""
    var content: String = ""
    var clickListener: (() -> Unit)? = null

    var tv_title: TextView? = null
    var tv_content: TextView? = null
    var ok: TextView? = null
    override fun getImplLayoutId(): Int {
        return R.layout.xpopup_ok
    }

    override fun onCreate() {
        super.onCreate()
        tv_title = findViewById(R.id.tv_title)
        tv_content = findViewById(R.id.tv_content)
        ok = findViewById(R.id.ok)
        
        if (title.isNullOrBlank()) {
            tv_title?.visibility = View.GONE
        } else {
            tv_title?.text = title
        }
        tv_content?.text = content
        tv_content?.movementMethod = ScrollingMovementMethod.getInstance()
        ok?.setOnClickListener {
            clickListener?.invoke()
            dismiss()
        }

    }

    companion object {
        fun show(
            context: Context,
            title: String?,
            content: String,
            listener: (() -> Unit)? = null
        ) {
            val okDialogView = OkDialogView(context)
            okDialogView.title = title ?: ""
            okDialogView.content = content
            okDialogView.clickListener = listener
            XPopup.Builder(context).asCustom(okDialogView).show()
        }
    }
}