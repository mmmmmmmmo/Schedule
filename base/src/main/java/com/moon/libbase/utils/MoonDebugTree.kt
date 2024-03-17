package com.moon.libbase.utils

import timber.log.Timber

/**
 * @author ry
 * @date 2020-01-04
 */
class MoonDebugTree : Timber.DebugTree() {
    companion object {
        const val MOON_TAG = "MoonTag"
    }

    override fun createStackElementTag(element: StackTraceElement): String? {
        val stackTag = super.createStackElementTag(element)
        return "$MOON_TAG $stackTag"
    }
}