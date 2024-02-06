package com.example.android_test.internal.injection.module.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_test.art_details.ArtDetailsViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class ArtDetailsModule {
    @Provides
    @Suppress("UNCHECKED_CAST")
    fun provideArtDetailsViewModelFactory(
        artDetailsViewModelProvider: Provider<ArtDetailsViewModel>
    ): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return when (modelClass) {
                    ArtDetailsViewModel::class.java -> artDetailsViewModelProvider.get() as T
                    else -> throw IllegalArgumentException("modelClass $modelClass not handled")
                }
            }
        }
    }
}