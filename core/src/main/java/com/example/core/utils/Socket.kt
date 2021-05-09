package com.example.core.utils

import android.util.Log

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.retryWhen
import okhttp3.*

/**
 * Created on : 08/05/21 | 23.13
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class Socket (private val client: OkHttpClient){

    companion object {
        private val TAG = "WEBSOCKET"
    }

    fun connect(url: String) = callbackFlow<String> {
        val request = Request.Builder().url(url).build()

        val webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                webSocket.send(
                    "{\n" +
                            "    \"action\": \"SubAdd\",\n" +
                            "    \"subs\": [\"2~Coinbase~BTC~USD\",\"2~Coinbase~LTC~USD\"]\n" +
                            "}"
                )
                Log.d(TAG, "Connected: $response")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                // Emit value to Flow
                offer(text)
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                if (code != 1000) close(SocketNetworkException("Network Failure"))
                Log.d(TAG, "Closed #$code")
                //disini lakukan unsubs
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                close(SocketNetworkException("Network Failure"))
                Log.d(TAG, "Network Failure = ${t.message}")
            }
        })

        // Wait for the Flow to finish
        awaitClose { webSocket.close(1000, "Closed") }
    }
        .retryWhen { cause, attempt ->
            // Exponential backoff of 1 second on each retry
            if (attempt > 1) delay(1000 * attempt)
            else if (attempt >= 8) delay(8000)

            Log.d(TAG, "Retrying #$attempt")

            // Do not retry for IllegalArgument or 3 attempts are reached
            cause is SocketNetworkException
        }

    class SocketNetworkException(message: String) : Exception(message)

}