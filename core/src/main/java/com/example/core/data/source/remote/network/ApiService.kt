package com.example.core.data.source.remote.network

import com.example.core.BuildConfig
import com.example.core.data.source.remote.response.CryptoResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created on : 28/04/21 | 06.54
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

interface ApiService {
    @Headers("authorization: Apikey{${BuildConfig.API_KEY}}")
    @GET("data/top/totaltoptiervolfull?")
    suspend fun getData(
        @Query("limit") limit:Int = 50,
        @Query("tsym") tsym:String = "USD",
    ) : CryptoResponse
}
