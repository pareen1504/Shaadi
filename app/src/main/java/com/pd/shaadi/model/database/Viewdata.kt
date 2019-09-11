package com.pd.shaadicom.model.database

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ResponseData(val results: List<Viewdata>)

data class Viewdata(
    val login: login,
    val name: name,
    val location: location,
    val picture: picture
)

@Json(name = "login")
data class login(val salt: String, val username: String)

@Json(name = "name")
data class name(val first: String, val last: String)

@Json(name = "location")
data class location(val city: String, val state: String)

@Json(name = "picture")
data class picture(val thumbnail: String, val medium: String)

