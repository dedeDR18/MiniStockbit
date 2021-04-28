package com.example.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.source.local.entity.CryptoEntity
import com.example.core.domain.model.Crypto
import kotlinx.coroutines.flow.Flow

/**
 * Created on : 28/04/21 | 07.16
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
@Dao
interface CrytoDao {

    @Query("SELECT * FROM cryptoentities")
    fun getAllMovies(): Flow<List<CryptoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrypto(crypto: List<CryptoEntity>)

}