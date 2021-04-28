package com.example.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created on : 28/04/21 | 07.18
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
@Entity(tableName = "cryptoentities")
data class CryptoEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idCoin")
    var idCoin: String,

    @ColumnInfo(name = "coinName")
    var coinName: String,

    @ColumnInfo(name = "coinFullname")
    var coinFullname: String,

    @ColumnInfo(name = "coinPrice")
    var coinPrice: String,

    @ColumnInfo(name = "coinAlgorithm")
    var coinAlgorithm: String,

    @ColumnInfo(name = "coinMarket")
    var coinMarket: String
)