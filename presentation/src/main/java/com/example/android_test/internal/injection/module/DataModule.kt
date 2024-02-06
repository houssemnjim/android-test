package com.example.android_test.internal.injection.module

import android.content.Context
import com.example.android_test.MuseumApplication
import com.example.android_test.internal.network.RetrofitFactory
import com.example.data.api.ApiService
import com.example.data.network.adapters.NetworkResultCallAdapterFactory
import com.example.data.repositories.ArtObjectDetailsRepositoryImpl
import com.example.data.repositories.MuseumRepositoryImpl
import com.example.domain.repositories.ArtObjectDetailsRepository
import com.example.domain.repositories.MuseumRepository
import com.example.domain.usecase.ArtObjectDetailsUseCase
import com.example.domain.usecase.MuseumUseCase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import javax.inject.Singleton

// A data module class where we can init our app dependencies
@Module
class DataModule {


    @Singleton
    @Provides
    fun provideContext(application: MuseumApplication): Context {
        return application.applicationContext
    }

    @Provides
    fun provideOkHttpClient(
        context: Context
    ): OkHttpClient.Builder {
        val cacheDir = File(context.cacheDir, "HttpResponseCache")
        val cache = Cache(cacheDir, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE)
        return OkHttpClient.Builder().cache(cache)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideApi(
        retrofitFactory: RetrofitFactory, okHttpBuilder: OkHttpClient.Builder
    ): ApiService {
        okHttpBuilder.addInterceptor { chain ->
            val request = chain.request().newBuilder().url(
                chain.request().url.newBuilder().addQueryParameter("key", KEY).build()
            ).build()
            chain.proceed(request)
        }
        val restAdapter = retrofitFactory.create(
            BASE_URL, NetworkResultCallAdapterFactory.create(), okHttpBuilder.build()
        )
        return restAdapter.create(ApiService::class.java)
    }

    companion object {
        const val BASE_URL = "https://www.rijksmuseum.nl/"
        const val KEY = "rIl6yb6x"
        const val HTTP_RESPONSE_DISK_CACHE_MAX_SIZE: Long = 20 * 1024 * 1024
    }


    @Provides
    fun provideMuseumRepository(museumRepositoryImpl: MuseumRepositoryImpl): MuseumRepository =
        museumRepositoryImpl

    @Provides
    fun provideMuseumUseCase(museumRepository: MuseumRepository): MuseumUseCase =
        MuseumUseCase(museumRepository)

    @Provides
    fun provideArtObjectDetailsRepository(detailsRepositoryImpl: ArtObjectDetailsRepositoryImpl): ArtObjectDetailsRepository =
        detailsRepositoryImpl

    @Provides
    fun provideArtObjectDetailsUseCase(artObjectDetailsRepository: ArtObjectDetailsRepository): ArtObjectDetailsUseCase =
        ArtObjectDetailsUseCase(artObjectDetailsRepository)

}