package com.example.ministockbit

import com.example.core.domain.usecase.CryptoInteractor
import com.example.core.domain.usecase.CryptoUseCase
import com.example.ministockbit.ui.watchlist.WatchlistViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created on : 28/04/21 | 09.15
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

val useCaseModule = module {
    factory<CryptoUseCase> { CryptoInteractor(get()) }
}

val viewModelModule = module {
    viewModel { WatchlistViewModel(get()) }
}