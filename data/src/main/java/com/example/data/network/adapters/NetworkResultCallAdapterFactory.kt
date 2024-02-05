package com.example.data.network.adapters

import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Reference : https://proandroiddev.com/modeling-retrofit-responses-with-sealed-classes-and-coroutines-9d6302077dfe
 */

class NetworkResultCallAdapterFactory private constructor() : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *> {
        val callType = getParameterUpperBound(0, returnType as ParameterizedType)
        val resultType = (callType as ParameterizedType).actualTypeArguments[0]
        return NetworkResultCallAdapter(resultType)
    }

    companion object {
        fun create(): NetworkResultCallAdapterFactory = NetworkResultCallAdapterFactory()
    }
}