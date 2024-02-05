package com.example.android_test.internal.injection.module

import com.example.android_test.internal.network.RetrofitFactory
import com.example.data.api.ApiService
import com.example.data.network.adapters.NetworkResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

// A data module class where we can init our app dependencies
@Module
class DataModule {

    @Provides
    fun provideApi(
        retrofitFactory: RetrofitFactory, okHttpBuilder: OkHttpClient.Builder
    ): ApiService {
        okHttpBuilder.addInterceptor { chain ->
            val request = chain.request().newBuilder().url(
                chain.request().url.newBuilder()
                    .addQueryParameter("key", KEY)
                    .build()
            ).build()
            chain.proceed(request)
        }
        val restAdapter = retrofitFactory.create(
            BASE_URL, NetworkResultCallAdapterFactory.create(),
            okHttpBuilder.build()
        )
        return restAdapter.create(ApiService::class.java)
    }

    companion object {
        const val BASE_URL = "https://www.rijksmuseum.nl/"
        const val KEY = "rIl6yb6x"
    }
}