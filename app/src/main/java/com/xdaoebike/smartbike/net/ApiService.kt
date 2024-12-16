package com.xdaoebike.smartbike.net

import com.google.gson.JsonObject
import com.xdaoebike.smartbike.net.model.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/userCenter/user/getUserInfoById")
    suspend fun test(@Query("userId") userId: String): Response<Any>
}