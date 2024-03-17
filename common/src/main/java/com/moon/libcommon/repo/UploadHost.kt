package com.moon.libcommon.repo

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import org.jetbrains.anko.support.v4.startActivityForResult

interface UploadHost {

    fun getContext(): Context

    fun startActivityForResult(intent: Intent, requestCode: Int)

    fun getHostActivity(): FragmentActivity
}

class ActivityHost(val activity: FragmentActivity) : UploadHost {
    override fun getContext(): Context {
        return activity
    }

    override fun startActivityForResult(intent: Intent, requestCode: Int) {
        activity.startActivityForResult(intent, requestCode)
    }

    override fun getHostActivity(): FragmentActivity {
        return activity
    }
}

class FragmentHost(val fragment: Fragment) : UploadHost {
    override fun getContext(): Context {
        return fragment.requireContext()
    }

    override fun startActivityForResult(intent: Intent, requestCode: Int) {
        fragment.startActivityForResult(intent, requestCode)
    }

    override fun getHostActivity(): FragmentActivity {
        return fragment.requireActivity()
    }

}