package com.xdaoebike.smartbike.net.model

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModelDsl<T> {
    internal lateinit var request: suspend () -> Response<T>

    internal var onStart: (() -> Boolean?)? = null

    internal var onResponse: ((Response<T>) -> Unit)? = null

    internal var onError: ((Exception) -> Boolean?)? = null

    internal var onFinally: (() -> Boolean?)? = null

    infix fun onStart(onStart: (() -> Boolean?)?) {
        this.onStart = onStart
    }

    infix fun onRequest(request: suspend () -> Response<T>) {
        this.request = request
    }

    infix fun onResponse(onResponse: ((Response<T>) -> Unit)?) {
        this.onResponse = onResponse
    }

    infix fun onError(onError: ((Exception) -> Boolean?)?) {
        this.onError = onError
    }

    infix fun onFinally(onFinally: (() -> Boolean?)?) {
        this.onFinally = onFinally
    }

    internal fun launch(viewModelScope: CoroutineScope) {
        viewModelScope.launch(context = Dispatchers.Main) {
            onStart?.invoke()
            try {
                val response = withContext(Dispatchers.IO) {
                    request()
                }
                onResponse?.invoke(response)
            } catch (e: Exception) {
                e.printStackTrace()
                onError?.invoke(e)
            } finally {
                onFinally?.invoke()
            }
        }
    }


}