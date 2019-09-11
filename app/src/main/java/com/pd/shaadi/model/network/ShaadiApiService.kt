package com.pd.shaadi.model.network

import com.pd.shaadi.OpenForTesting
import com.pd.shaadicom.model.database.ResponseData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

@OpenForTesting
interface ShaadiApiService {
    @GET("api/")
    fun getreposne(@Query("results") results: Int?): Observable<ResponseData>
}