package com.example.core.domain.model

import com.squareup.moshi.JsonClass

/**
 * Created on : 09/05/21 | 14.00
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

@JsonClass(generateAdapter = true)
data class WebsocketResponse(
    val TYPE: Int,
    val MARKET: String?,
    val FROMSYMBOL: String?,
    val TOSYMBOL: String?,
    val PRICE: Double? = 0.0
    )