package com.xdaoebike.smartbike.util

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.util.Log
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import com.xdaoebike.smartbike.BuildConfig


@SuppressLint("StaticFieldLeak")
object LogUtil {
    private var mContext: Context? = null
    fun init(context: Context) {
        mContext = context
    }

    val debug = BuildConfig.DEBUG
    private const val tag = "ZD"
    fun v(msg: String) {
        v(tag, msg)
    }

    fun v(tag: String, msg: String) {
        Log.v(tag, msg)
    }

    fun d(msg: String) {
        d(tag, msg)
    }

    fun d(tag: String, msg: String) {
        Log.d(tag, msg)
    }

    fun i(msg: String) {
        i(tag, msg)
    }

    fun i(tag: String, msg: String) {
        Log.i(tag, msg)
    }

    fun w(msg: String) {
        w(tag, msg)
    }

    fun w(tag: String, msg: String) {
        Log.w(tag, msg)
    }

    fun e(msg: String) {
        if (!debug)return
        e(tag, msg)
    }

    fun e(tag: String, msg: String) {
        Log.e(tag, msg)
    }

}
