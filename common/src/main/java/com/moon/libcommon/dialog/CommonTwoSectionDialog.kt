package com.moon.libcommon.dialog

import android.content.Context
import android.widget.Button
import android.widget.TextView
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.CenterPopupView
import com.moon.libcommon.R

class CommonTwoSectionDialog constructor(context: Context) : CenterPopupView(context) {

    constructor(context: Context, bgRes:Int) : this(context) {
        bgResourse=bgRes
    }

    var bgResourse:Int?=null
    var title: String = ""
    var contentText:String=""
    var sureTitle: String? = null
    var content:TextView?=null
    var clickListener: (() -> Unit)? = null
    var cancelTitle:String=""
    var cancelClickListener: (() -> Unit)? = null

    var tv_title: TextView? = null
    var cancel: Button? = null
    var sure: Button? = null


    override fun getImplLayoutId(): Int {
        return R.layout.dialog_common_two_section
    }

    override fun onCreate() {
        super.onCreate()
        tv_title = findViewById(R.id.tv_title)
        sure = findViewById(R.id.sure)
        content=findViewById(R.id.content)
        cancel = findViewById(R.id.cancel)
        tv_title?.text = title
        content?.text=contentText
        sure?.text = sureTitle ?: resources.getString(R.string.sure)
        if (!cancelTitle.isNullOrEmpty()){
            cancel?.text=cancelTitle
        }

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
            content: String,
            sureTitle: String? = null,
            listener: (() -> Unit)? = null,
            cancelListener: (() -> Unit)? = null
        ) {
            val normalDialogView = CommonTwoSectionDialog(context)
            normalDialogView.title = title
            normalDialogView.clickListener = listener
            normalDialogView.cancelClickListener = cancelListener
            normalDialogView.contentText = content
            normalDialogView.sureTitle = sureTitle
            XPopup.Builder(context).asCustom(normalDialogView).show()
        }
    }
}