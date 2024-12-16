package com.xdaoebike.smartbike.util

import java.lang.reflect.ParameterizedType

object GenericsUtil {
    fun <T> getInstant(obj: Any, argIndex: Int = 0): T {
        val type = obj.javaClass.genericSuperclass
        if (type !is ParameterizedType) {
            throw IllegalArgumentException("Can not find genericSuperclass in ${javaClass.simpleName} ")
        }

        val tempObj: Class<T> = type.actualTypeArguments[argIndex] as Class<T>
        return tempObj.getDeclaredConstructor().newInstance()
    }
}