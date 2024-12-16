package com.xdaoebike.smartbike

import android.app.Application
import android.content.Context
import com.xdaoebike.smartbike.net.handler.Request
import com.xdaoebike.smartbike.util.LogUtil
import java.lang.ref.WeakReference
import kotlin.properties.Delegates

class App : Application() {
    companion object {
        lateinit var appContext: WeakReference<Context>
        private var instance: App by Delegates.notNull()
        fun instance() = instance
    }

    fun getContext(): Context {
        return this
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appContext = WeakReference(applicationContext)
        LogUtil.init(this)
        Request.init(this, BuildConfig.baseUrl)
    }
}