package com.example.core.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize

/**
 * Created on : 28/04/21 | 07.38
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

@Parcelize
data class Crypto(

    val idCoin: String,
    val coinName: String,
    var coinFullname: String,
    var coinPrice: String,
    var coinAlgorithm: String,
    var coinMarket: String

) : Parcelable