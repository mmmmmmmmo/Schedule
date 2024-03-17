package com.moon.libbase.base

import android.graphics.Color
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.moon.libbase.R

/**
 * @author ry
 * @date 2020-01-10
 */
class BottomBehaviorHelper(val fragment: BottomSheetDialogFragment) {
    private var bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onSlide(bottomSheet: View, slideOffset: Float) {

        }

        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                BottomSheetBehavior.from(bottomSheet).state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

    }

    /**
     * 设置内容高度
     */
    fun setContentHeight(contentHeight: Int) {
        fragment.dialog?.let {
            val bottomSheet: FrameLayout = it.findViewById(androidx.navigation.ui.R.id.design_bottom_sheet)
            //全屏显示
            val params = bottomSheet.layoutParams
            params.height = contentHeight
            bottomSheet.layoutParams = params
            //背景透明
            bottomSheet.setBackgroundColor(Color.TRANSPARENT)

            val behavior = BottomSheetBehavior.from(bottomSheet)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.addBottomSheetCallback(bottomSheetCallback)
        }
    }
}