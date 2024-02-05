package com.example.data.network.adapters

import com.example.domain.commons.Failure
import com.example.domain.commons.Success
import com.example.domain.commons.Result
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

/**
 * Reference : https://proandroiddev.com/modeling-retrofit-responses-with-sealed-classes-and-coroutines-9d6302077dfe
 */

class NetworkResultCall<T : Any>(
    private val proxy: Call<T>
) : Call<Result<T>> {

    override fun enqueue(callback: Callback<Result<T>>) {
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val networkResult = handleResponse(response)
                callback.onResponse(this@NetworkResultCall, Response.success(networkResult))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                callback.onResponse(this@NetworkResultCall, Response.success(Failure(t.message)))
            }
        })
    }

    override fun execute(): Response<Result<T>> = throw NotImplementedError()
    override fun clone(): Call<Result<T>> = NetworkResultCall(proxy.clone())
    override fun request(): Request = proxy.request()
    override fun timeout(): Timeout = proxy.timeout()
    override fun isExecuted(): Boolean = proxy.isExecuted
    override fun isCanceled(): Boolean = proxy.isCanceled
    override fun cancel() {
        proxy.cancel()
    }

    private fun <T : Any> handleResponse(
        response: Response<T>
    ): Result<T> {
        return try {
            if (response.isSuccessful) {
                response.body()?.let { safeBody -> Success(safeBody) } ?: run { Failure() }
            } else {
                Failure()
            }
        } catch (e: HttpException) {
            Failure(e.message)
        } catch (e: Throwable) {
            Failure(e.message)
        }
    }
}
