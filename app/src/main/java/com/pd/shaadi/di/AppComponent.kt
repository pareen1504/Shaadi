package com.pd.shaadi.di


import com.pd.shaadi.ShaadiApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        AppModule::class,
        NetworkModule::class
    )
)

interface AppComponent : AndroidInjector<ShaadiApplication>
