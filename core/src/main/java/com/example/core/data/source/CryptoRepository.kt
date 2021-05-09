package com.example.core.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.data.NetworkBoundResource
import com.example.core.data.Resource
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.Data
import com.example.core.data.source.remote.websocket.CryptoWebsocketService
import com.example.core.domain.model.Coin
import com.example.core.domain.model.Crypto
import com.example.core.domain.model.WebsocketResponse
import com.example.core.domain.repository.ICryptoRepository
import com.example.core.utils.DataMapper
import kotlinx.coroutines.CoroutineScope

import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

/**
 * Created on : 28/04/21 | 08.09
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class CryptoRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val cryptoWebsocketService: CryptoWebsocketService
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

    override fun getDataCoin(cs: CoroutineScope) : Flow<WebsocketResponse> {
        return cryptoWebsocketService.getCryptos()
    }


}