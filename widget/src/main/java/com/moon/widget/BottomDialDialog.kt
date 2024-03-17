package com.moon.widget

import com.moon.libbase.base.BaseBottomSheetDialogFragment
import com.moon.widget.databinding.DialogBottomDialBinding

class BottomDialDialog() : BaseBottomSheetDialogFragment<DialogBottomDialBinding>() {


    constructor(phone: String?) : this() {
        callPhone = phone
    }

    var callPhone: String? = null

    override val layoutId: Int
        get() = R.layout.dialog_bottom_dial

    var clickListener: ((String) -> Unit)? = null

    override fun initView() {
        super.initView()
    }

    override fun onResume() {
        super.onResume()
        dataBinding.dialPhone.text = callPhone
    }

    override fun initListener() {
        super.initListener()
        dataBinding.dialPhone.setOnClickListener {
            clickListener?.invoke(dataBinding.dialPhone.text.toString())
        }

        dataBinding.cancel.setOnClickListener {
            dismiss()
        }
    }
}