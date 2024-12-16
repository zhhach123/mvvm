package com.xdaoebike.smartbike.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.xdaoebike.smartbike.net.ApiService
import com.xdaoebike.smartbike.net.handler.Request
import com.xdaoebike.smartbike.net.model.RequestViewModel
import androidx.lifecycle.viewModelScope
import com.xdaoebike.smartbike.repository.BaseRepository
import com.xdaoebike.smartbike.util.GenericsUtil
import com.xdaoebike.smartbike.util.LogUtil
import com.xdaoebike.smartbike.widget.ProgressDialogUtil
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

open class BaseViewModel<Repository : BaseRepository> : RequestViewModel() {

    val repository: Repository by lazy {
        return@lazy GenericsUtil.getInstant(this)
    }
    internal val toastLiveData = MutableLiveData<String>()

    private fun showLoading() {
        apiLoading.postValue(true)
    }

    private fun hideLoading() {
        apiLoading.postValue(false)
    }

    fun showToast(message: String) {
        toastLiveData.postValue(message)
    }

    fun showError(error: Exception) {
        apiException.postValue(error)
    }

    override fun onApiStart() {
        super.onApiStart()
    }

    override fun onApiError(e: Exception?) {
        super.onApiError(e)
    }

    override fun onApiFinally() {
        super.onApiFinally()
    }

    fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) =
        viewModelScope.launch {
            try {
                block()
            } catch (e: Throwable) {
                error(e)
            }
        }

    fun launch(block: suspend () -> Unit) = viewModelScope.launch {
        try {
            block()
        } catch (e: Throwable) {
            LogUtil.e("$e")
        }
    }

}