package com.example.core.data.source.remote.response

data class CoinInfoX(
    val Algorithm: String,
    val AssetLaunchDate: String,
    val BlockNumber: Int,
    val BlockReward: Int,
    val BlockTime: Int,
    val DocumentType: String,
    val FullName: String,
    val Id: String,
    val ImageUrl: String,
    val Internal: String,
    val MaxSupply: Int,
    val Name: String,
    val NetHashesPerSecond: Int,
    val ProofType: String,
    val Rating: RatingX,
    val Type: Int,
    val Url: String
)