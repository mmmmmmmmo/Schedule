package com.moon.libbase.utils.extension

import android.content.Context
import android.content.SharedPreferences
import com.moon.libbase.base.BaseApplication
import kotlin.reflect.KProperty

/**
 * SharedPreferences对象(kotlin代理)
 */
abstract class BaseKTPreference<T>(private val spName:String,private val keyName: String, private val default: T) {

    private val prefs: SharedPreferences by lazy { BaseApplication.instance.getSharedPreferences(spName, Context.MODE_PRIVATE) }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return getSharePreferences(keyName, default)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putSharePreferences(keyName, value)
    }

    private fun putSharePreferences(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("Type Error, cannot be saved!")
        }.apply()
    }

    @Suppress("UNCHECKED_CAST")
    private fun getSharePreferences(name: String, default: T): T = with(prefs) {
        val res: Any? = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("Type Error, cannot be saved!")
        }
        return res as T
    }
}