package com.example.ministockbit.ui.stream


import androidx.lifecycle.*
import com.example.core.domain.model.WebsocketResponse
import com.example.core.domain.usecase.CryptoUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Created on : 07/05/21 | 20.23
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class StreamViewModel(private val cryptoUseCase: CryptoUseCase) : ViewModel() {

    private val _stocks = MutableLiveData<WebsocketResponse>()
    val stocks = _stocks

    init {
        cryptoUseCase.getDataCoin(viewModelScope)
            .onEach {
                _stocks.postValue(it)
            }
            .launchIn(viewModelScope)
    }
}
