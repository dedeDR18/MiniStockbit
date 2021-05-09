package com.example.core.domain.usecase


import com.example.core.data.Resource
import com.example.core.domain.model.Crypto
import com.example.core.domain.model.WebsocketResponse
import com.example.core.domain.repository.ICryptoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

/**
 * Created on : 28/04/21 | 07.45
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class CryptoInteractor (private val cryptoRepository : ICryptoRepository) : CryptoUseCase {
    override fun getAllCryptoData(): Flow<Resource<List<Crypto>>> = cryptoRepository.getAllCryptoData()
    override fun getDataCoin(cs: CoroutineScope): Flow<WebsocketResponse> = cryptoRepository.getDataCoin(cs)


}