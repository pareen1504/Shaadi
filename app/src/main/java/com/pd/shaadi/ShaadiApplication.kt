package com.pd.shaadi

import android.app.Application
import com.pd.shaadi.di.AppModule
import com.pd.shaadi.di.DaggerAppComponent
import com.pd.shaadi.di.NetworkModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

@OpenForTesting
class ShaadiApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidinjector : DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .build()
            .inject(this)
    }

    override fun androidInjector() = androidinjector
}