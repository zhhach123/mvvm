package com.xdaoebike.smartbike.repository

import com.xdaoebike.smartbike.net.ApiService
import com.xdaoebike.smartbike.net.handler.Request

open class BaseRepository {
    companion object {
        internal val service by lazy { Request.apiService(ApiService::class.java) }
    }
}