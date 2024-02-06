package com.example.android_test.internal.injection.module.binding

import com.example.android_test.art_details.ArtDetailsActivity
import com.example.android_test.internal.injection.module.screens.ArtDetailsModule
import com.example.android_test.internal.injection.module.screens.MainModule
import com.example.android_test.museum.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ArtDetailsModule::class])
    abstract fun contributeArtDetailsActivity(): ArtDetailsActivity
}