package com.moon.libcommon.dialog

import android.content.Context
import android.widget.Button
import android.widget.TextView
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.CenterPopupView
import com.moon.libcommon.R

class CommonOneSectionDialog constructor(context: Context) : CenterPopupView(context) {

    constructor(context: Context, bgRes:Int) : this(context) {
        bgResourse=bgRes
    }

    var bgResourse:Int?=null
    var title: String = ""
    var sureTitle: String? = null
    var clickListener: (() -> Unit)? = null

    var cancelClickListener: (() -> Unit)? = null

    var tv_title: TextView? = null
    var cancel: Button? = null
    var sure: Button? = null

    override fun getImplLayoutId(): Int {
        return R.layout.dialog_one_section
    }

    override fun onCreate() {
        super.onCreate()
        tv_title = findViewById(R.id.tv_title)
        sure = findViewById(R.id.sure)
        cancel = findViewById(R.id.cancel)
        tv_title?.text = title
        sure?.text = sureTitle ?: resources.getString(R.string.sure)
        cancel?.setOnClickListener {
            cancelClickListener?.invoke()
            dismiss()
        }
        sure?.setOnClickListener {
            clickListener?.invoke()
            dismiss()
        }

        if (bgResourse!=null){
            sure?.setBackgroundResource(bgResourse!!)
        }
    }

    fun setOKBtnText(txt:String){
        sure.let {
            sure?.setText(txt)
        }

    }



    companion object {
        fun show(
            context: Context,
            title: String,
            sureTitle: String? = null,
            listener: (() -> Unit)? = null
        ) {
            val normalDialogView = NormalDialogView(context)
            normalDialogView.title = title
            normalDialogView.clickListener = listener
            normalDialogView.sureTitle = sureTitle
            XPopup.Builder(context).asCustom(normalDialogView).show()
        }
    }
}