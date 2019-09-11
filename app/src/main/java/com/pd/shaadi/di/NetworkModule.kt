package com.pd.shaadi.di

import com.pd.shaadi.OpenForTesting
import com.pd.shaadi.Util.BASE_URL
import com.pd.shaadi.model.network.ShaadiApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@OpenForTesting
class NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun providesMoshi(): Moshi =  Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient,moshi: Moshi):Retrofit{
        return Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesApiInterface(retrofit: Retrofit):ShaadiApiService = retrofit.create(ShaadiApiService::class.java)
}