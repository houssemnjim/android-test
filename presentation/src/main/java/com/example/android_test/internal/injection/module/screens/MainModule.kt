package com.example.android_test.internal.injection.module.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_test.museum.MainViewModel
import com.example.data.repositories.MuseumRepositoryImpl
import com.example.domain.repositories.MuseumRepository
import com.example.domain.usecase.MuseumUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Provider

// a module to inject data inside our MainViewModel

@Module
class MainModule {
    @Provides
    @Suppress("UNCHECKED_CAST")
    fun provideMainViewModelFactory(
        mainViewModelProvider: Provider<MainViewModel>
    ): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return when (modelClass) {
                    MainViewModel::class.java -> mainViewModelProvider.get() as T
                    else -> throw IllegalArgumentException("modelClass $modelClass not handled")
                }
            }
        }
    }

}