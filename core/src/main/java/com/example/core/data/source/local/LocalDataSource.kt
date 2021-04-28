package com.example.core.data.source.local

import com.example.core.data.source.local.entity.CryptoEntity
import com.example.core.data.source.local.room.CrytoDao
import kotlinx.coroutines.flow.Flow

/**
 * Created on : 28/04/21 | 07.30
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class LocalDataSource (private val dao: CrytoDao){
    fun getAllData() : Flow<List<CryptoEntity>> = dao.getAllMovies()
    suspend fun insertCrypto(listCrypto: List<CryptoEntity>) = dao.insertCrypto(listCrypto)
}