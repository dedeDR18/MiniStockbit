package com.example.core.data.source

import com.example.core.data.NetworkBoundResource
import com.example.core.data.Resource
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.CryptoResponse
import com.example.core.data.source.remote.response.Data
import com.example.core.domain.model.Crypto
import com.example.core.domain.repository.ICryptoRepository
import com.example.core.utils.AppExecutors
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created on : 28/04/21 | 08.09
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class CryptoRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ICryptoRepository {
    override fun getAllCryptoData(): Flow<Resource<List<Crypto>>> =
        object : NetworkBoundResource<List<Crypto>, List<Data>>() {
            override fun loadFromDB(): Flow<List<Crypto>> {
                return localDataSource.getAllData().map { data ->
                    DataMapper.mapEntitiesToDomain(data)
                }
            }

            override fun shouldFetch(data: List<Crypto>?): Boolean
            = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<Data>>> {
                return remoteDataSource.getData()
            }

            override suspend fun saveCallResult(data: List<Data>) {
                val cryptoList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertCrypto(cryptoList)
            }

        }.asFlow()

}