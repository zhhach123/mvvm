package com.xdaoebike.smartbike.viewmodel

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.xdaoebike.smartbike.net.model.Response
import com.xdaoebike.smartbike.repository.TestRepository
import com.xdaoebike.smartbike.util.FleetingLiveData

class TestViewModel : BaseViewModel<TestRepository>() {

    fun test(id: String) = launch {
        apiDSL {
            onRequest {
                repository.test(id)
            }
            onResponse {
                testSuccess.postValue(it)
            }
        }
    }

    val testSuccess: FleetingLiveData<Response<Any>> by lazy {
        FleetingLiveData()
    }
}