package com.pd.shaadi.di

import android.app.Application

import androidx.room.Room
import com.pd.shaadi.OpenForTesting
import com.pd.shaadicom.model.database.AppDatabase
import com.pd.shaadicom.model.database.AppDbDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@OpenForTesting
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun providesAppDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, "Shaadi_db").build()

    @Provides
    @Singleton
    fun providesAppDao(database: AppDatabase): AppDbDao = database.appDbdao()
}