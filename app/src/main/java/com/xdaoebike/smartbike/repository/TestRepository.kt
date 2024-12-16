package com.xdaoebike.smartbike.repository

import com.google.gson.JsonObject
import com.xdaoebike.smartbike.net.model.Response

class TestRepository : BaseRepository() {

    suspend fun test(id: String): Response<Any> {
        return service.test(id)
    }
}