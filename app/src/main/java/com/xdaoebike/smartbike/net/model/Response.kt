package com.xdaoebike.smartbike.net.model

import android.text.TextUtils

class Response<T> {
    var code //状态码  0：失败  1：成功
            = 0
    var msg // 显示的信息
            : String? = null
    var message: String? = null
        get() {
            if (!TextUtils.isEmpty(msg) || "null" != msg) {
                return msg
            }
            if (!TextUtils.isEmpty(massage) || "null" != massage) {
                return massage
            }
            return if (!TextUtils.isEmpty(field) || "null" != field) {
                field
            } else errorMsg
        }
    var massage // 显示的信息
            : String? = null
    var errorMsg // 显示错误信息
            : String? = null
    var data // 业务数据
            : T? = null
        private set

    fun setResults(results: T) {
        this.data = results
    }

    override fun toString(): String {
        return "Response{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data.toString() +
                '}'
    }
}