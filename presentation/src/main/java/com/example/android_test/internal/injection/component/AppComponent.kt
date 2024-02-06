package com.example.android_test.internal.injection.component

import com.example.android_test.MuseumApplication
import com.example.android_test.internal.injection.module.DataModule
import com.example.android_test.internal.injection.module.binding.ActivityBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        DataModule::class,
        ActivityBindingModule::class,
        AndroidSupportInjectionModule::class,
    ]
)
interface AppComponent : AndroidInjector<MuseumApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: MuseumApplication): AppComponent
    }

}