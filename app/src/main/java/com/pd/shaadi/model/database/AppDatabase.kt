package com.pd.shaadicom.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pd.shaadi.model.database.DbData


@Database(entities = arrayOf(DbData::class), version = 1,exportSchema = false)
abstract class AppDatabase :RoomDatabase() {
    abstract fun appDbdao():AppDbDao
}