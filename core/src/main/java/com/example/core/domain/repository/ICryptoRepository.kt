package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.Crypto
import kotlinx.coroutines.flow.Flow


/**
 * Created on : 28/04/21 | 07.36
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
interface ICryptoRepository {
    fun getAllCryptoData() : Flow<Resource<List<Crypto>>>
}