package com.moon.libcommon.dialog

import android.content.Context
import android.widget.ProgressBar
import android.widget.TextView
import com.lxj.xpopup.core.CenterPopupView
import com.moon.libcommon.R


class DownloadingView constructor(context: Context) : CenterPopupView(context) {
    var clickListener: (() -> Unit)? = null
    var tv_title: TextView? = null
    var progressbar: ProgressBar? = null
    override fun getImplLayoutId(): Int {
        return R.layout.xpopup_downloading
    }

    override fun onCreate() {
        super.onCreate()
        tv_title = findViewById(R.id.tv_title)
        progressbar = findViewById(R.id.progressbar)
        tv_title?.text = context.resources.getString(R.string.downloading, 0) + "%"
    }

    fun setProgress(progress: Int) {
        tv_title?.text = context.resources.getString(R.string.downloading, progress) + "%"
        progressbar?.progress = progress
    }
}