package com.example.core.utils

import com.example.core.domain.model.WebsocketResponse
import com.squareup.moshi.FromJson


/**
 * Created on : 09/05/21 | 15.23
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
internal class WebsocketMoshiAdapter {
    @FromJson
    fun responseFromJson(res: WebsocketResponse): WebsocketResponse {
        var websocketResponse = WebsocketResponse(
            TYPE = res.TYPE,
            MARKET = res.MARKET,
            FROMSYMBOL = res.FROMSYMBOL,
            TOSYMBOL = res.TOSYMBOL,
            PRICE = res.PRICE
        )
        return websocketResponse
    }

}