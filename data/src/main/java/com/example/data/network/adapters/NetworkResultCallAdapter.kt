package com.example.data.network.adapters

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

/**
 * Reference : https://proandroiddev.com/modeling-retrofit-responses-with-sealed-classes-and-coroutines-9d6302077dfe
 */

class NetworkResultCallAdapter(
    private val resultType: Type
) : CallAdapter<Type, Call<Result<Type>>> {

    override fun responseType(): Type = resultType

    override fun adapt(call: Call<Type>): Call<Result<Type>> {
        return NetworkResultCall(call)
    }
}