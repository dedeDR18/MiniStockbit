package com.example.core.domain.model

/**
 * Created on : 08/05/21 | 00.47
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
abstract class BaseSubscribe(
    open val action: String,
    open val subs: List<String>
){
    companion object{
        const val SUBSCRIBE_EVENT = "subscribe"
        const val UNSUBSCRIBE_EVENT = "unsubscribe"
    }
}