package com.example.ministockbit

import android.app.Application
import com.example.core.di.databaseModule
import com.example.core.di.networkModule
import com.example.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created on : 28/04/21 | 09.46
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class MyApp : Application(){
    override fun onCreate() {
        super.onCreate()
            startKoin{
                androidLogger(Level.NONE)
                androidContext(this@MyApp)
                modules(
                    listOf(
                        databaseModule,
                        networkModule,
                        repositoryModule,
                        useCaseModule,
                        viewModelModule
                    )
                )
            }
    }
}