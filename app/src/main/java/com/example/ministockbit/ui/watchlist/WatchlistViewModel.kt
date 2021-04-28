package com.example.ministockbit.ui.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.domain.usecase.CryptoUseCase

class WatchlistViewModel(cryptoUseCase: CryptoUseCase) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Watchlist"
    }
    val text: LiveData<String> = _text
}