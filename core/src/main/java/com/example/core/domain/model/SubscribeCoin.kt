package com.example.core.domain.model

/**
 * Created on : 08/05/21 | 00.49
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

data class SubscribeCoin(
    override val action: String,
    override val subs: List<String>
) : BaseSubscribe(action,subs)