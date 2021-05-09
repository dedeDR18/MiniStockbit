package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Crypto
import com.example.core.domain.model.WebsocketResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

/**
 * Created on : 28/04/21 | 07.41
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

interface CryptoUseCase {
    fun getAllCryptoData() : Flow<Resource<List<Crypto>>>
    fun getDataCoin(cs: CoroutineScope) : Flow<WebsocketResponse>

}