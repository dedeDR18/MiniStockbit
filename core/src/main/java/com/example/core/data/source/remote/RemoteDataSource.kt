package com.example.core.data.source.remote

import android.util.Log
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.CryptoResponse
import com.example.core.data.source.remote.response.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

/**
 * Created on : 28/04/21 | 07.53
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
class RemoteDataSource (private val apiService: ApiService){
    suspend fun getData () : Flow<ApiResponse<List<Data>>> {
        return flow {
            try {
                val response = apiService.getData()
                val dataArray = response.Data
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.Data))
                } else {
                    emit(ApiResponse.Empty)
                }

            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}