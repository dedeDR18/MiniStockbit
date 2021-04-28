package com.example.core.utils

import com.example.core.data.source.local.entity.CryptoEntity
import com.example.core.data.source.remote.response.Data
import com.example.core.domain.model.Crypto

/**
 * Created on : 28/04/21 | 06.34
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
object DataMapper {
    fun mapEntitiesToDomain(input : List<CryptoEntity>) : List<Crypto> =
        input.map {
            Crypto(
                idCoin = it.idCoin,
                coinName = it.coinName,
                coinFullname = it.coinFullname,
                coinPrice = it.coinPrice,
                coinAlgorithm = it.coinAlgorithm,
                coinMarket = it.coinMarket
            )
        }

    fun mapResponsesToEntities(input: List<Data>) : List<CryptoEntity> {
        val cryptoList = ArrayList<CryptoEntity>()
        input.map { data ->
            val cryptoEntity = CryptoEntity(
                idCoin = data.CoinInfo.Id,
                coinName = data.CoinInfo.Name,
                coinFullname = data.CoinInfo.FullName,
                coinPrice = data.DISPLAY.USD.PRICE,
                coinAlgorithm = data.CoinInfo.Algorithm,
                coinMarket = data.DISPLAY.USD.MARKET
            )
            cryptoList.add(cryptoEntity)
        }
        return cryptoList
    }



}