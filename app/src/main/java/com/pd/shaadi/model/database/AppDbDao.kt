package com.pd.shaadicom.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pd.shaadi.OpenForTesting
import com.pd.shaadi.model.database.DbData

@Dao
@OpenForTesting
interface AppDbDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(mdata: DbData)

    @Query("UPDATE shaadimatches SET selection = 'accept' WHERE id = :id ")
    fun acceptProfile(id: String)

    @Query("UPDATE shaadimatches SET selection = 'reject' WHERE id = :id ")
    fun rejectProfile(id: String)

    @Query("SELECT * FROM shaadimatches")
    fun getAllDbData(): LiveData<List<DbData>>

    @Query("DELETE FROM shaadimatches WHERE id = :id ")
    fun delete(id: String)
}