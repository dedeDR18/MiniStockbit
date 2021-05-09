package com.example.core.data.source.remote.websocket

import android.util.Log
import com.example.core.domain.model.WebsocketResponse
import com.example.core.utils.Socket
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map


/**
 * Created on : 07/05/21 | 15.56
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
class CryptoWebsocketService(private val socket: Socket, val moshi: Moshi)  {

    companion object {
        const val URL = "wss://streamer.cryptocompare.com/v2?api_key=34ce39113c73f6616f8ae74400540382df9c08f6d24517907b9faab80494a778"
    }

    val adapter: JsonAdapter<WebsocketResponse> = moshi.adapter(WebsocketResponse::class.java)


    //define klo udah konek harus ngapain
    fun getCryptos(): Flow<WebsocketResponse> {
        return socket.connect(URL)
            .map {

                val ws = adapter.fromJson(it)
                Log.d("WEBSOCKET", "hasil from json = ${ws!!.TYPE}")
                ws
            }
            .flowOn(Dispatchers.IO)
    }

}