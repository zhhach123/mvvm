package com.xdaoebike.smartbike.net.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


open class RequestViewModel : ViewModel() {

    open val apiException: MutableLiveData<Throwable> = MutableLiveData()
    open val apiLoading: MutableLiveData<Boolean> = MutableLiveData()


    private fun <Response> api(apiDSL: ViewModelDsl<Response>.() -> Unit) {
        ViewModelDsl<Response>().apply(apiDSL).launch(viewModelScope)
    }

    @JvmOverloads
    protected fun <Response> apiCallback(
        request: suspend () -> com.xdaoebike.smartbike.net.model.Response<Response>,
        onResponse: ((com.xdaoebike.smartbike.net.model.Response<Response>) -> Unit),
        onStart: (() -> Boolean)? = null,
        onError: ((java.lang.Exception) -> Boolean)? = null,
        onFinally: (() -> Boolean)? = null
    ) {

        api<Response> {

            onRequest {
                request.invoke()
            }

            onResponse {
                onResponse.invoke(it)
            }

            onStart {
                val override = onStart?.invoke()
                if (override == null || !override) {
                    onApiStart()
                }
                false
            }

            onError {
                val override = onError?.invoke(it)
                if (override == null || !override) {
                    onApiError(it)
                }
                false

            }

            onFinally {
                val override = onFinally?.invoke()
                if (override == null || !override) {
                    onApiFinally()
                }
                false
            }
        }
    }

    protected fun <T> apiDSL(apiDSL: ViewModelDsl<T>.() -> Unit) {
        api<T> {
            onRequest {
                ViewModelDsl<T>().apply(apiDSL).request()
            }

            onResponse {
                ViewModelDsl<T>().apply(apiDSL).onResponse?.invoke(it)
            }

            onStart {
                val override = ViewModelDsl<T>().apply(apiDSL).onStart?.invoke()
                if (override == null || !override) {
                    onApiStart()
                }
                override
            }

            onError { error ->
                val override = ViewModelDsl<T>().apply(apiDSL).onError?.invoke(error)
//                if (override == null || !override) {
//                    onApiError(error)
//                }

                onApiError(error)

                override

            }

            onFinally {
                val override = ViewModelDsl<T>().apply(apiDSL).onFinally?.invoke()
                if (override == null || !override) {
                    onApiFinally()
                }
                override
            }
        }
    }

    protected fun <Response> apiLiveData(
        context: CoroutineContext = EmptyCoroutineContext,
        timeoutInMs: Long = 3000L,
        request: suspend () -> Response
    ): LiveData<Result<Response>> {

        return androidx.lifecycle.liveData(context, timeoutInMs) {
            emit(Result.Start())
            try {
                emit(withContext(Dispatchers.IO) {
                    Result.Response(request())
                })
            } catch (e: Exception) {
                emit(Result.Error(e))
                return@liveData
            } finally {
                emit(Result.Finally())
            }
        }
    }

    protected open fun onApiStart() {
        apiLoading.value = true
    }

    protected open fun onApiError(e: Exception?) {
        apiLoading.value = false
        apiException.value = e!!

    }

    protected open fun onApiFinally() {
        apiLoading.value = false

    }
}


sealed class Result<T> {
    class Start<T> : Result<T>()
    class Finally<T> : Result<T>()
    data class Response<T>(val response: T) : Result<T>()
    data class Error<T>(val exception: Exception) : Result<T>()
}
