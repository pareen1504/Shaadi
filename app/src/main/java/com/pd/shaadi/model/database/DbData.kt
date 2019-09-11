package com.pd.shaadi.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shaadimatches")
data class DbData(
    @PrimaryKey
    val id: String,
    val firstname: String,
    val lastname: String,
    val location: String,
    val img_url:String,
    var selection: String
)