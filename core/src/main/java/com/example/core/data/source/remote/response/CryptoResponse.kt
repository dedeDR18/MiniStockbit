package com.example.core.data.source.remote.response

data class CryptoResponse(
    val Data: List<Data>,
    val HasWarning: Boolean,
    val Message: String,
    val MetaData: MetaData,
    val RateLimit: RateLimit,
    val SponsoredData: List<SponsoredData>,
    val Type: Int
)