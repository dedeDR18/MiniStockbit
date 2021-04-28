package com.example.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.source.local.entity.CryptoEntity

/**
 * Created on : 28/04/21 | 08.52
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

@Database(entities = [CryptoEntity::class], version = 1, exportSchema = false)
abstract class CryptoDatabase : RoomDatabase(){
    abstract fun cryptoDao() : CrytoDao
}